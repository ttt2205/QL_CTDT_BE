package com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.GiangVien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GiangVienRepository extends JpaRepository<GiangVien, Long> {
}
