package com.phan_lop.quan_ly_chuong_trinh_dao_tao.services;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.GiangVien;

import java.util.List;

public interface GiangVienService {
    GiangVien findById(Long id);
    List<GiangVien> getAll();
}
