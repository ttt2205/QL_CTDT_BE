package com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    @Query("""
        SELECT u
        FROM User u
        WHERE u.email LIKE %:keyword% OR u.soDienThoai LIKE %:keyword% OR u.username LIKE %:keyword
    """)
    List<User> findByKeyword(@Param("keyword") String keyword);
}
