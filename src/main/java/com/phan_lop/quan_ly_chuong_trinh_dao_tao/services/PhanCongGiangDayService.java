package com.phan_lop.quan_ly_chuong_trinh_dao_tao.services;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.PhanCongGiangDayReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.PhanCongGiangDay;

import java.util.List;

public interface PhanCongGiangDayService {
    PhanCongGiangDay findById(Long id);
    List<PhanCongGiangDay> getByKeHoachMoNhomId(Long keHoachMoNhomId);
    PhanCongGiangDay addPhanCongGiangDay(PhanCongGiangDayReqDto reqDto);
    void softDeleteById(Long id);
    void hardDeleteById(Long id);
    PhanCongGiangDay updateById(Long id, PhanCongGiangDayReqDto reqDto);
}
