package com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query methods can be defined here if needed

}
