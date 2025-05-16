package com.phan_lop.quan_ly_chuong_trinh_dao_tao.services;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.HocPhanDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.HocPhanReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.HocPhan;

import java.util.List;

public interface HocPhanService {
    List<HocPhan> getAll();
    HocPhan findByMaHocPhan(String maHocPhan);
    HocPhan findById(Long id);
    boolean deleteById(Long id);
    HocPhan create(HocPhanDto hpDto);
    HocPhan update(Long id,HocPhanDto hocPhanDto);

}
