package com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.DeCuongChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface DeCuongChiTietRepo extends JpaRepository<DeCuongChiTiet, Long>, JpaSpecificationExecutor<DeCuongChiTiet> {
    @Query("SELECT dc FROM DeCuongChiTiet dc WHERE dc.hocPhan.id = :maHocPhan")
    List<DeCuongChiTiet> findByHocPhanId(@Param("maHocPhan") Long maHocPhan);


    @Transactional
    @Modifying
    @Query("DELETE FROM DeCuongChiTiet dc WHERE dc.hocPhan.id = :hocPhanId")
    void deleteByHocPhanId(@Param("hocPhanId") Long hocPhanId);
}
