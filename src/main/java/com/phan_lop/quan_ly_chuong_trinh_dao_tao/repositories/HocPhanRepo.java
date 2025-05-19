package com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.HocPhan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HocPhanRepo extends JpaRepository<HocPhan, Long> {
    Optional<HocPhan> findByMaHocPhan(String maHocPhan);

    @Query("SELECT DISTINCT dct.hocPhan FROM DeCuongChiTiet dct")
    List<HocPhan> findHocPhanWithDeCuongChiTiet();
}
