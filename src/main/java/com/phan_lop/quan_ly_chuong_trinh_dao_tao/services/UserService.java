package com.phan_lop.quan_ly_chuong_trinh_dao_tao.services;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.UserDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.User;

public interface UserService {
    public User createUser(UserDto user);
}
