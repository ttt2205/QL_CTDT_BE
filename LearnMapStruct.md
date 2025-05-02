# MapStruct basics

## @Mapper
- **componentModel**: cấu hình kiểu bean, thường dùng `"spring"` để autowire.
    - *Default*: `"default"` (không tích hợp với framework, phải gọi bằng `Mappers.getMapper(...)`).
- **uses**: khai báo các mapper khác dùng chung (dùng cho nested mapping).
    - *Default*: {} (không dùng mapper phụ).
- **unmappedTargetPolicy**: quy định xử lý khi có trường trong target không được map.
    - Các giá trị: `IGNORE`, `WARN`, `ERROR`.
    - *Default*: `ReportingPolicy.WARN`.

## @Mapping
- **source**: tên trường trong entity (bắt buộc nếu cần custom).
- **target**: tên trường trong DTO (bắt buộc nếu cần custom).
- **ignore**: bỏ qua, không cố map trường này của DTO.
    - *Default*: `false`.
- **dateFormat**: định dạng ngày nếu là kiểu `Date` hoặc `LocalDate`.
    - *Default*: rỗng (dùng định dạng mặc định của Java).
- **defaultValue**: giá trị mặc định nếu `source` là `null`.
    - *Default*: rỗng (không có giá trị mặc định).
- **qualifiedByName**: dùng hàm mapping theo tên (đi chung với `@Named`).
    - *Default*: không sử dụng.
- **expression**: dùng biểu thức Java để gán giá trị.
    - VD: `"java(user.getId())"`.
    - *Default*: không dùng expression.

## @Mappings
- Gom nhiều `@Mapping` lại trong một annotation.
- *Default*: ít dùng vì không cần thiết trong nhiều trường hợp (có thể dùng nhiều `@Mapping` riêng lẻ thay thế).
