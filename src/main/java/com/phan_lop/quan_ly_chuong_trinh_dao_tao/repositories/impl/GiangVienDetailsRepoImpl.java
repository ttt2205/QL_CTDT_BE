package com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories.impl;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.GiangVienDetailsResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories.GiangVienDetailsRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.Arrays;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GiangVienDetailsRepoImpl implements GiangVienDetailsRepo {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<GiangVienDetailsResDto> findPhanCongByGiangVienId(Long idGV) {
        String sql = """
            SELECT 
              hp.id, hp.ten_hoc_phan, hp.so_tin_chi,  
              MAX(pcgd.so_tiet_thuc_hien),
              SUM(CASE WHEN pcgd.hoc_ky_day = 1 THEN 1 ELSE 0 END), 
              SUM(CASE WHEN pcgd.hoc_ky_day = 2 THEN 1 ELSE 0 END), 
              SUM(CASE WHEN pcgd.hoc_ky_day = 3 THEN 1 ELSE 0 END), 
              gv.id, gv.ten, gv.nam_sinh, gv.chuc_danh, gv.khoa 
            FROM phan_cong_giang_day pcgd 
            JOIN ke_hoach_mo_nhom khmn ON khmn.id = pcgd.ke_hoach_mo_nhom_id 
            JOIN hoc_phan hp ON hp.id = khmn.hoc_phan_id 
            JOIN giang_vien gv ON gv.id = pcgd.giang_vien_id
            WHERE pcgd.giang_vien_id = :idGV AND pcgd.status = 1
            GROUP BY hp.id, hp.ten_hoc_phan, hp.so_tin_chi, gv.id, gv.ten, gv.nam_sinh, gv.chuc_danh, gv.khoa
        """;

  try {
            Query query = em.createNativeQuery(sql);
            query.setParameter("idGV", idGV);

            List<Object[]> resultList = query.getResultList();

            return resultList.stream().map(row -> {
                try {
                    return new GiangVienDetailsResDto(
                        ((Number) row[0]).longValue(),
                        (String) row[1],
                        ((Number) row[2]).intValue(),
                        row[3] != null ? ((Number) row[3]).intValue() : 0,
                        ((Number) row[4]).intValue(),
                        ((Number) row[5]).intValue(),
                        ((Number) row[6]).intValue(),
                        ((Number) row[7]).longValue(),
                        (String) row[8],
                        ((Number) row[9]).longValue(),
                        (String) row[10],
                        (String) row[11]
                    );
                } catch (Exception innerEx) {
                    System.err.println("❌ Error mapping row: " + Arrays.toString(row));
                    innerEx.printStackTrace();
                    throw new RuntimeException("Lỗi ép kiểu trong DTO", innerEx);
                }
            }).toList();

        } catch (Exception e) {
            System.err.println("❌ Lỗi khi truy vấn hoặc xử lý kết quả cho giảng viên ID = " + idGV);
            e.printStackTrace();
            throw e;
        }
    }
}
