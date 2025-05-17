package com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.GiangVienExportProjection;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.GiangVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiangVienExportRepository extends JpaRepository<GiangVien, Long> {

    @Query(value = """
        SELECT  
          hp.id AS MAHP, 
          hp.ten_hoc_phan AS TenHP, 
          hp.so_tin_chi AS SoTC,
          MAX(pcgd.so_tiet_thuc_hien) AS SoTiet,
          SUM(CASE WHEN pcgd.hoc_ky_day = 1 THEN 1 ELSE 0 END) AS hk1, 
          SUM(CASE WHEN pcgd.hoc_ky_day = 2 THEN 1 ELSE 0 END) AS hk2,
          SUM(CASE WHEN pcgd.hoc_ky_day = 3 THEN 1 ELSE 0 END) AS hk3,
          gv.id AS GiangVienID,
          gv.ten AS TenGV,
          gv.nam_sinh AS NamSinh,
          gv.chuc_danh AS ChucDanh,
          gv.khoa AS Khoa
        FROM 
          phan_cong_giang_day pcgd
        JOIN ke_hoach_mo_nhom khmn ON khmn.id = pcgd.ke_hoach_mo_nhom_id 
        JOIN hoc_phan hp ON hp.id = khmn.hoc_phan_id 
        JOIN giang_vien gv ON gv.id = pcgd.giang_vien_id
        WHERE   
          gv.khoa = :khoa AND pcgd.status = 1 AND gv.status = 1
        GROUP BY   
          hp.id, hp.ten_hoc_phan, hp.so_tin_chi, gv.id, gv.ten, gv.nam_sinh, gv.chuc_danh, gv.khoa
        """, nativeQuery = true)
    List<GiangVienExportProjection> getExportByKhoa(@Param("khoa") String khoa);
}
