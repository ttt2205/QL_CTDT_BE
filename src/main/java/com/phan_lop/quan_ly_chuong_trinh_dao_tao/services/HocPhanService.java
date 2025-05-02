package com.phan_lop.quan_ly_chuong_trinh_dao_tao.services;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.HocPhan;

import java.util.List;
import java.util.Optional;

public interface HocPhanService {
    List<HocPhan> getAll();
    HocPhan findByMaHocPhan(String maHocPhan);
}
