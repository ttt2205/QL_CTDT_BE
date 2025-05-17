package com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.KeHoachMoNhom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KeHoachMoNhomRepository extends JpaRepository<KeHoachMoNhom, Long> {
    List<KeHoachMoNhom> findByHocPhanId(Long hocPhanId);
    List<KeHoachMoNhom> findByNamHoc(String namHoc);

    @Query("""
        SELECT k FROM KeHoachMoNhom k
        JOIN k.hocPhan hp
        WHERE k.isDeleted = false
          AND (:keyword IS NULL OR LOWER(hp.maHocPhan) LIKE LOWER(CONCAT('%', :keyword, '%')) 
                               OR LOWER(hp.tenHocPhan) LIKE LOWER(CONCAT('%', :keyword, '%')))
          AND (:namHoc IS NULL OR k.namHoc = :namHoc)
    """)
    List<KeHoachMoNhom> searchKeHoachMoNhom(
                    @Param("keyword") String keyword,
                    @Param("namHoc") String namHoc
            );

}
