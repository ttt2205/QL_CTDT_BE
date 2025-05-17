package com.phan_lop.quan_ly_chuong_trinh_dao_tao.services;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.KeHoachMoNhomReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.KeHoachMoNhomResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.TongHopKeHoachMoNhom.TongHopKeHoachMoNhomDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.KeHoachMoNhom;

import java.util.List;

public interface KeHoachMoNhomService {
    List<KeHoachMoNhom> findByHocPhanId(Long hocPhanId);
    KeHoachMoNhom findById(Long id);
    List<KeHoachMoNhom> findAll();
    List<KeHoachMoNhom> search(String keyword, String namHoc);
    void deleteById(Long id);
    KeHoachMoNhom update(Long id, KeHoachMoNhomReqDto reqDto);
    KeHoachMoNhomResDto add(KeHoachMoNhomReqDto reqDto);
    List<TongHopKeHoachMoNhomDto> tongHopKeHoachMoNhom(String namHoc);
}
