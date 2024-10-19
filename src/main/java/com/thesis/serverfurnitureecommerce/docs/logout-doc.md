# Tài liệu mô tả chức năng Đăng xuất

## Mô tả
Chức năng đăng xuất cho phép người dùng thoát khỏi phiên làm việc hiện tại. Khi đăng xuất, token xác thực của người dùng sẽ được đánh dấu là không hợp lệ để ngăn chặn việc sử dụng trong tương lai.

## Đầu vào

### Đối tượng `LogoutRequest`
Yêu cầu đầu vào cho chức năng đăng xuất bao gồm:

- **token**: chuỗi ký tự không trống, đại diện cho mã thông báo xác thực của người dùng.
    - **Yêu cầu**: Bắt buộc
    - **Kiểm tra**: `@NotBlank(message = "PARAMETER_MISSING")`

```java
public class LogoutRequest {
    @NotBlank(message = "PARAMETER_MISSING")
    private String token;
}
```
## Đầu ra

### Phản hồi
Phản hồi từ chức năng đăng xuất có thể là:

- **200 OK**: Nếu đăng xuất thành công.
- **401 UNAUTHORIZED**: Nếu người dùng không được xác thực.
- **400 BAD REQUEST**: Nếu có lỗi liên quan đến tham số đầu vào (ví dụ: token bị thiếu hoặc không hợp lệ).

## Quy trình thực hiện

1. **Xác thực Token**:
    - Nhận yêu cầu đăng xuất từ người dùng, bao gồm token.
    - Kiểm tra xem người dùng có đang được xác thực hay không.

2. **Lưu trữ Token Không hợp lệ**:
    - Tạo một đối tượng `InvalidatedTokenEntity` mới với token từ yêu cầu.
    - Đặt thời gian hết hạn cho token không hợp lệ (ví dụ: 30 phút).
    - Lưu đối tượng token không hợp lệ vào cơ sở dữ liệu.

3. **Xóa Bối cảnh**:
    - Xóa bối cảnh xác thực hiện tại để đảm bảo rằng người dùng đã thoát khỏi phiên.

## Ví dụ yêu cầu

### Yêu cầu Đăng xuất
```http
POST /api/auth/logout HTTP/1.1
Host: yourapi.com
Content-Type: application/json

{
    "token": "your_jwt_token_here"
}
```
## Ví dụ phản hồi 
### Phản hồi Thành công
```json
{
  "code": 200,
  "message": "Success"
}
```

### Phản hồi Lỗi
```json
{
  "status": "error",
  "message": "User not authenticated."
}
```

