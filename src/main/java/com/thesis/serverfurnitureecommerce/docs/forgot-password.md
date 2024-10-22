# Tài liệu mô tả chức năng quên mật khẩu
## 1. Giới thiệu
Chức năng "Quên mật khẩu" cho phép người dùng lấy lại quyền truy cập vào tài khoản của họ khi quên mật khẩu. Quy trình này sử dụng mã OTP (One Time Password) để xác thực rằng yêu cầu thay đổi mật khẩu được thực hiện bởi người sở hữu tài khoản.
## 2. Quy trình chức năng

1. **Yêu cầu quên mật khẩu**:
    - Người dùng truy cập trang "Quên mật khẩu" và nhập địa chỉ email đã đăng ký tài khoản.
    - Hệ thống gửi yêu cầu đến API `POST /api/user/forgot-password` với địa chỉ email trong request body.

2. **Tạo OTP và gửi email**:
    - Service xử lý yêu cầu, tìm kiếm tài khoản tương ứng với email đã nhập.
    - Nếu tài khoản không tồn tại, hệ thống trả về mã lỗi `INVALID_EMAIL`.
    - Nếu tài khoản tồn tại:
        - Hệ thống tạo một mã OTP gồm 6 chữ số.
        - OTP có thời hạn là 3 phút (180 giây) kể từ thời điểm tạo.
        - OTP được gửi tới email của người dùng qua hệ thống email của ứng dụng.
    - Hệ thống sẽ trả về thông báo rằng OTP đã được gửi thành công qua email.

3. **Xác thực OTP**:
    - Người dùng nhập mã OTP đã nhận được qua email vào form xác thực trên giao diện client.
    - Hệ thống gửi yêu cầu đến API `POST /api/user/verify-forget-password` với email và OTP trong request body.
    - Service sẽ kiểm tra:
        - Nếu OTP đã hết hạn (sau 3 phút), trả về lỗi `OTP_EXPIRED`.
        - Nếu OTP không khớp với mã OTP đã gửi cho người dùng, trả về lỗi `INVALID_OTP`.
        - Nếu OTP hợp lệ, hệ thống xóa OTP và thời gian hết hạn OTP trong tài khoản người dùng.
    - Sau khi OTP được xác thực thành công, người dùng sẽ được chuyển sang form thay đổi mật khẩu.

4. **Thay đổi mật khẩu**:
    - Người dùng nhập mật khẩu mới vào form.
    - Hệ thống gửi yêu cầu đến API `POST /api/user/change-password` với request body chứa email và mật khẩu mới (đối tượng `NewPasswordRequest`).
    - Service kiểm tra email và tìm kiếm tài khoản người dùng:
        - Nếu tài khoản không tồn tại, trả về lỗi `USER_NOT_FOUND`.
        - Nếu tài khoản tồn tại, hệ thống mã hóa mật khẩu mới và lưu vào cơ sở dữ liệu.
    - Trả về phản hồi thành công với thông báo "Mật khẩu đã được thay đổi thành công."

## 3. Chi tiết các API

### API 1: Quên mật khẩu
- **URL**: `POST /api/user/forgot-password`
- **Request Parameter**:
    - `email`: Địa chỉ email của người dùng.
- **Response**:
    - Thành công: OTP được gửi qua email.
    - Lỗi: `INVALID_EMAIL` nếu không tìm thấy tài khoản.

### API 2: Xác thực OTP
- **URL**: `POST /api/user/verify-forget-password`
- **Request Body**:
    - `email`: Địa chỉ email của người dùng.
    - `otp`: Mã OTP được gửi qua email.
- **Response**:
    - Thành công: OTP hợp lệ, chuyển sang bước nhập mật khẩu mới.
    - Lỗi: `OTP_EXPIRED` nếu OTP đã hết hạn, `INVALID_OTP` nếu OTP không hợp lệ.

### API 3: Thay đổi mật khẩu
- **URL**: `POST /api/user/change-password`
- **Request Body**:
    - `email`: Địa chỉ email của người dùng.
    - `newPassword`: Mật khẩu mới.
- **Response**:
    - Thành công: "Mật khẩu đã được thay đổi thành công."
    - Lỗi: `USER_NOT_FOUND` nếu không tìm thấy tài khoản.

## 4. Luồng hoạt động

1. Người dùng chọn chức năng "Quên mật khẩu" và nhập email.
2. Hệ thống gửi OTP qua email.
3. Người dùng nhập OTP vào form để xác thực.
4. Sau khi xác thực OTP thành công, người dùng nhập mật khẩu mới.
5. Hệ thống cập nhật mật khẩu mới và cho phép người dùng đăng nhập với mật khẩu mới.

## 5. Bảo mật

- OTP chỉ có hiệu lực trong vòng 3 phút kể từ khi được tạo.
- Mật khẩu mới sẽ được mã hóa trước khi lưu vào cơ sở dữ liệu để bảo vệ dữ liệu của người dùng.
