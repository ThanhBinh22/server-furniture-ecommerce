# Tài liệu mô tả chức năng Đăng nhập

## Mô tả
Chức năng đăng nhập cho phép người dùng xác thực tài khoản bằng cách cung cấp tên người dùng và mật khẩu. Nếu xác thực thành công, hệ thống sẽ trả về một JSON Web Token (JWT) và thời gian hết hạn của token.

## Phản hồi
Khi một người dùng đăng nhập thành công, hệ thống sẽ trả về một đối tượng `LoginResponse` chứa các thông tin sau:

### Đối tượng LoginResponse
| Trường      | Kiểu dữ liệu | Mô tả                                             |
|-------------|--------------|---------------------------------------------------|
| `token`     | `String`     | Mã token JWT được cấp cho người dùng.             |
| `expiresIn` | `long`       | Thời gian (tính bằng giây) mà token còn hiệu lực. |

### Ví dụ phản hồi
```json
{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "expiresIn": 3600
}
```
## Quy trình thực hiện

1. **Người dùng gửi yêu cầu đăng nhập:**
    - Người dùng cung cấp tên người dùng và mật khẩu thông qua một yêu cầu HTTP POST đến endpoint `api/auth/login`.

2. **Xác thực thông tin:**
    - Hệ thống xác thực thông tin đăng nhập bằng cách kiểm tra tên người dùng và mật khẩu trong cơ sở dữ liệu.

3. **Tạo token:**
    - Nếu thông tin đăng nhập hợp lệ, hệ thống sẽ tạo một token JWT và xác định thời gian hết hạn.

4. **Trả về phản hồi:**
    - Hệ thống trả về một đối tượng `LoginResponse` chứa token và thời gian hết hạn của nó.
