package com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.KeHoachDayHoc;

@Repository
public interface KeHoachDayHocRepository extends JpaRepository<KeHoachDayHoc, Long> {
}
