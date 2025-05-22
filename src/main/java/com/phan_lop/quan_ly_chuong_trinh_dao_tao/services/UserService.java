package com.phan_lop.quan_ly_chuong_trinh_dao_tao.services;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.UserReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.UserResDto;

import java.util.List;

public interface UserService {
    UserResDto createUser(UserReqDto request);
    List<UserResDto> getAllUsers();
    UserResDto updateUser(Long id, UserReqDto request);
    void deleteUser(Long id);
    UserResDto getUserById(Long id);
    List<UserResDto> findByKeyword(String keyword);
}
