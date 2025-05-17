package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos;

import org.apache.commons.lang3.time.DateFormatUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;

    private java.time.LocalDateTime created_at;
    private java.time.LocalDateTime deleted_at;
    private String email;
    private Boolean is_deleted; 
    private String password;
    private String role;
    private String so_dien_thoai;
    private Boolean status; 
    private java.time.LocalDateTime updated_at;
    private String username;

    
}
