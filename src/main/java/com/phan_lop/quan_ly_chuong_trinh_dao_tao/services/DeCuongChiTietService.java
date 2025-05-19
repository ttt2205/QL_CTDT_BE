package com.phan_lop.quan_ly_chuong_trinh_dao_tao.services;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.DeCuongChiTietDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.DeCuongChiTiet;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.HocPhan;

import java.util.List;

public interface DeCuongChiTietService {
    DeCuongChiTiet findById(Long id);
    List<DeCuongChiTiet> findByHocPhanId(Long id);
    boolean deleteByHocPhanId(Long id);
    boolean create(List<DeCuongChiTietDto> deCuongChiTietList);
    boolean update(Long hocPhanId,List<DeCuongChiTietDto> deCuongChiTietList);
}
