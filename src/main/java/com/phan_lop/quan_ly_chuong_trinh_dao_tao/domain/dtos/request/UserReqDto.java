package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserReqDto {

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String email;

    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min = 6, message = "Mật khẩu phải có ít nhất 6 ký tự")
    private String password;

    @NotBlank(message = "Username không được để trống")
    private String username;

    @NotBlank(message = "Vai trò không được để trống")
    private String role;

    private String soDienThoai;

    @NotNull(message = "Trạng thái không được để trống")
    private Boolean status;
}
