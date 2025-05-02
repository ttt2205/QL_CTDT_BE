package com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories;


import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.GiangVien;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.KeHoachMoNhom;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.PhanCongGiangDay;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PhanCongGiangDayRepository extends JpaRepository<PhanCongGiangDay, Long> {
    Optional<List<PhanCongGiangDay>> findByKeHoachMoNhomId(Long keHoachMoNhomId);
    boolean existsByGiangVienAndKeHoachMoNhomAndHocKyDay(GiangVien giangVien, KeHoachMoNhom keHoachMoNhom, int hocKyDay);

}
