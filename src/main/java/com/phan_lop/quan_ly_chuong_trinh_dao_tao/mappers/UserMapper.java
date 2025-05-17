package com.phan_lop.quan_ly_chuong_trinh_dao_tao.mappers;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.UserReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.UserResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.User;

import java.time.LocalDateTime;

public class UserMapper {

    public static User toEntity(UserReqDto request) {
        return User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .username(request.getUsername())
                .role(request.getRole())
                .soDienThoai(request.getSoDienThoai())
                .status(request.getStatus())
                .isDeleted(false)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static UserResDto toResponse(User entity) {
        return UserResDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .username(entity.getUsername())
                .role(entity.getRole())
                .soDienThoai(entity.getSoDienThoai())
                .status(entity.isStatus())
                .build();
    }
}
