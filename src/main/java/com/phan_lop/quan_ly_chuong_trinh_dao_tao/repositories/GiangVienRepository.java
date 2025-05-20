package com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories;


import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.GiangVien;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiangVienRepository extends JpaRepository<GiangVien, Long> {
    List<GiangVien> findByStatusTrue();
    List<GiangVien> findByStatusTrueAndBoMon(String boMon);
    List<GiangVien> findByStatusTrueAndTenContainingIgnoreCase(String ten);
    boolean existsByUserId(Long userId);

}
