package com.thesis.serverfurnitureecommerce.internal.services.account;

import com.thesis.serverfurnitureecommerce.constant.RoleConstant;
import com.thesis.serverfurnitureecommerce.domain.request.RegisterRequest;
import com.thesis.serverfurnitureecommerce.internal.repositories.RoleRepository;
import com.thesis.serverfurnitureecommerce.internal.repositories.UserRepository;
import com.thesis.serverfurnitureecommerce.internal.services.email.EmailService;
import com.thesis.serverfurnitureecommerce.model.entity.RoleEntity;
import com.thesis.serverfurnitureecommerce.model.entity.UserEntity;
import com.thesis.serverfurnitureecommerce.pkg.exception.AppException;
import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
import com.thesis.serverfurnitureecommerce.pkg.mapper.UserMapper;
import com.thesis.serverfurnitureecommerce.pkg.utils.OtpGenerator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountServiceImpl implements AccountService {
    UserRepository userRepository;
    EmailService emailService;
    UserMapper userMapper;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    /**
     * Đăng ký tài khoản mới nếu người dùng không tồn tại
     *
     * @param registerRequest thông tin đăng ký tài khoản
     */
    @Override
    public void registerAccount(RegisterRequest registerRequest) {
        log.info("Invoke to service register");

        // Kiểm tra người dùng theo username, nếu tồn tại và chưa kích hoạt, cập nhật mật khẩu và gửi OTP
        UserEntity userByUsername = findUserByUsername(registerRequest.getUsername());
        if (userByUsername != null && userByUsername.getIsActive() == 1) {
            log.warn("User already exists with username: {}", registerRequest.getUsername());
            throw new AppException(ErrorCode.USERNAME_ALREADY_EXISTS);
        }
        if (userByUsername != null && userByUsername.getIsActive() == 0) {
            handleExistingUser(userByUsername, registerRequest.getPassword());
            return;
        }
        // Kiểm tra người dùng theo email, nếu tồn tại và chưa kích hoạt, cập nhật mật khẩu và gửi OTP
        UserEntity userByEmail = findUserByEmail(registerRequest.getEmail());
        if (userByEmail != null && userByEmail.getIsActive() == 1) {
            throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }
        // Nếu người dùng tồn tại nhưng chưa kích hoạt, xử lý tiếp tục
        if (userByEmail != null && userByEmail.getIsActive() == 0) {
            handleExistingUser(userByEmail, registerRequest.getPassword());

            return;
        }
        // Tạo mới người dùng nếu chưa tồn tại
        createNewUser(registerRequest);
    }

    /**
     * Tìm người dùng theo username
     *
     * @param username tên người dùng
     * @return UserEntity nếu tồn tại, ném AppException nếu đã tồn tại
     */
    private UserEntity findUserByUsername(String username) {
        log.info("Invoke to service find user by username");
        return userRepository.findByUsername(username)
//                .filter(user -> user.getIsActive() == 0)
                .orElse(null);
    }

    /**
     * Tìm người dùng theo email
     *
     * @param email địa chỉ email
     * @return UserEntity nếu tồn tại, ném AppException nếu đã tồn tại
     */
    private UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .filter(user -> user.getIsActive() == 0)
                .orElse(null);
    }

    /**
     * Xử lý người dùng đã tồn tại nhưng chưa kích hoạt
     *
     * @param user     người dùng đã tồn tại
     * @param password mật khẩu mới của người dùng
     */
    private void handleExistingUser(UserEntity user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        user.setOtp(OtpGenerator.generate6DigitOtp());
        user.setOtpExpired(LocalDateTime.now().plus(Duration.ofMinutes(3)));
        userRepository.save(user);
        log.info("Updated existing user: {}", user.getEmail());
        emailService.sendMailOTP(user.getEmail(), user.getOtp());
    }

    /**
     * Tạo tài khoản người dùng mới và gửi OTP qua email
     *
     * @param registerRequest thông tin đăng ký
     */
    private void createNewUser(RegisterRequest registerRequest) {
        UserEntity userEntity = userMapper.fromRequestToEntity(registerRequest);
        userEntity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userEntity.setOtp(OtpGenerator.generate6DigitOtp());
        userEntity.setIsActive((short) 0);
        userEntity.setOtpExpired(LocalDateTime.now().plus(Duration.ofMinutes(3)));
        RoleEntity role = roleRepository.findByName(RoleConstant.USER)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
        userEntity.setRole(role);
        userRepository.save(userEntity);
        emailService.sendMailOTP(userEntity.getEmail(), userEntity.getOtp());
    }

    /**
     * Xác thực tài khoản sau khi đăng ký bằng OTP
     *
     * @param otp mã OTP do người dùng cung cấp
     * @return true nếu xác thực thành công, false nếu thất bại
     */
    @Override
    public Boolean verifyAccountAfterRegister(String otp) {
        UserEntity user = userRepository.findByOtp(Integer.parseInt(otp))
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        // Kiểm tra nếu OTP đã hết hạn
        if (user.getOtpExpired().isBefore(LocalDateTime.now())) {
            throw new AppException(ErrorCode.OTP_EXPIRED);
        }
        // Kiểm tra nếu OTP hợp lệ
        if (otp.equals(user.getOtp().toString())) {
            activateUser(user);
            return true;
        }
        return false;
    }

    /**
     * Kích hoạt tài khoản người dùng
     *
     * @param user người dùng
     */
    private void activateUser(UserEntity user) {
        user.setIsActive((short) 1);
        user.setOtp(null);
        user.setOtpExpired(null);
        userRepository.save(user);
    }

    /**
     * Gửi lại OTP cho người dùng
     *
     * @param email địa chỉ email của người dùng
     */
    @Override
    public void resendOTP(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        user.setOtp(OtpGenerator.generate6DigitOtp());
        user.setOtpExpired(LocalDateTime.now().plus(Duration.ofMinutes(3)));
        emailService.sendMailOTP(user.getEmail(), user.getOtp()); // Gửi lại OTP
        userRepository.save(user);
    }

    @Override
    public boolean isAccountVerified(String email) {
        return userRepository.findByEmail(email)
                .map(user -> user.getIsActive() == 1)
                .orElse(false);
    }

}
