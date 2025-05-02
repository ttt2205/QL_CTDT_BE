package com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.KeHoachMoNhom;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface KeHoachMoNhomRepository extends JpaRepository<KeHoachMoNhom, Long> {
    List<KeHoachMoNhom> findByHocPhanId(Long hocPhanId);
}
