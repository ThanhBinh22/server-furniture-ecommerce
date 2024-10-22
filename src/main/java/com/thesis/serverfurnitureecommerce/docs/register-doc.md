# Tài liệu Mô tả Chức năng Đăng ký

## Mục đích
Chức năng đăng ký cho phép người dùng tạo tài khoản mới trong hệ thống. Người dùng sẽ cần cung cấp thông tin như tên người dùng, email, mật khẩu và họ tên. Hệ thống sẽ xác thực thông tin đầu vào và gửi mã OTP đến email của người dùng để xác thực tài khoản.

## Thông tin Đầu vào
### 1. Đầu vào
Khi người dùng gửi yêu cầu đăng ký, cần cung cấp các thông tin sau:

- **username**: Tên người dùng (3-20 ký tự, không được trống).
- **email**: Địa chỉ email (phải hợp lệ, không được trống).
- **password**: Mật khẩu (6-20 ký tự, ít nhất một chữ cái viết hoa, một chữ cái viết thường, một số và một ký tự đặc biệt).
- **fullName**: Họ tên đầy đủ (không được trống).

### 2. Định dạng JSON
```json
{
  "username": "your_username",
  "email": "your_email@example.com",
  "password": "YourP@ssw0rd",
  "fullName": "Your Full Name"
}
```

## Quy trình Thực hiện

### 1. Gửi yêu cầu đăng ký
- Người dùng gửi yêu cầu đăng ký đến endpoint `/sign-up` với thông tin đầu vào đã được xác định.

### 2. Xác thực thông tin đầu vào
- Hệ thống sẽ kiểm tra:
    - Tên người dùng đã tồn tại trong hệ thống chưa.
    - Email đã được sử dụng chưa.
    - Mật khẩu có đủ tiêu chuẩn an toàn không.

### 3. Xử lý thông tin
- Nếu thông tin không hợp lệ, hệ thống sẽ trả về lỗi tương ứng.
- Nếu thông tin hợp lệ:
    - Mật khẩu sẽ được mã hóa trước khi lưu trữ.
    - Tạo một mã OTP ngẫu nhiên 6 chữ số.
    - Lưu trữ thông tin người dùng vào cơ sở dữ liệu với trạng thái không hoạt động.
    - Gửi mã OTP đến địa chỉ email của người dùng để xác thực.

### 4. Gửi phản hồi
- Hệ thống sẽ trả về phản hồi với thông tin về tài khoản đã được tạo (trừ mật khẩu).
- Nếu đăng ký thành công, phản hồi sẽ có mã trạng thái thành công.

## Phản hồi
- **Thành công (201 Created)**:
    - Nếu việc đăng ký thành công, hệ thống sẽ trả về phản hồi với mã trạng thái `201 Created` cùng với thông tin của người dùng đã được tạo.

- **Lỗi (4xx)**:
    - **400 Bad Request**: Nếu tên người dùng hoặc email đã tồn tại, hoặc nếu mật khẩu không đủ tiêu chuẩn an toàn.
    - **409 Conflict**: Nếu thông tin đăng ký bị xung đột với dữ liệu hiện có.
    - **422 Unprocessable Entity**: Nếu thông tin đầu vào không hợp lệ.

## Ghi chú
- Hệ thống cần đảm bảo rằng mã OTP được gửi đến email của người dùng.
- Mã OTP sẽ hết hạn sau 3 phút. Người dùng sẽ cần yêu cầu gửi lại mã OTP nếu hết hạn.
- Tên người dùng và email phải là duy nhất trong hệ thống.

