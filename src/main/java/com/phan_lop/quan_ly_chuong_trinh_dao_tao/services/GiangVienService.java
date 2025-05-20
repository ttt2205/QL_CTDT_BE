package com.phan_lop.quan_ly_chuong_trinh_dao_tao.services;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.GiangVienExportProjection;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.GiangVienResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.GiangVien;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.GiangVienReqDto;

import java.util.List;

public interface GiangVienService {
    List<GiangVienResDto> getAllActiveGiangViens();
    List<GiangVienResDto> searchByTen(String ten);
    List<GiangVienResDto> findByKhoa(String khoa);
    List<GiangVienResDto> findByBoMon(String boMon);
    void deleteSoft(Long id);
    void updateGiangVien(Long id, GiangVien updated);
    void addGiangVien(GiangVienReqDto dto);
    GiangVien findById(Long id);
    List<GiangVienExportProjection> getExportByBoMon(String boMon);

}
