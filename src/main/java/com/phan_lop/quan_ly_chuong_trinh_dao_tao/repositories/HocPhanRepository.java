package com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.HocPhan;

@Repository
public interface HocPhanRepository extends JpaRepository<HocPhan, Long> {

}
