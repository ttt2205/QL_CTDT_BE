package com.phan_lop.quan_ly_chuong_trinh_dao_tao.services;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.KeHoachDayHocReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.KhoiKienThucReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.NhomKienThucReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.KhoiKienThucResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.NhomKienThucResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.KeHoachDayHoc;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.KhoiKienThuc;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.NhomKienThuc;

import java.util.List;

public interface KeHoachDayHocService {

    KeHoachDayHoc createKeHoachDayHoc(KeHoachDayHocReqDto reqDto);
    boolean updateKeHoachDayHoc(Long id, KeHoachDayHocReqDto reqDto);
    boolean deleteKeHoachDayHoc(Long id);



    KhoiKienThuc createKhoiKienThuc(KhoiKienThucReqDto khoiKienThucDto);
    boolean updateKhoiKienThuc(Long id, KhoiKienThucReqDto khoiKienThucDto);
    boolean deleteKhoiKienThuc(Long khoiKienThucId);

    NhomKienThuc createNhomKienThuc(NhomKienThucReqDto nhomKienThucDto);
    boolean updateNhomKienThuc(Long id, NhomKienThucReqDto nhomKienThucDto);
    boolean deleteNhomKienThuc(Long nhomKienThucId);


    List<KhoiKienThucResDto> getAllKhoiKienThuc();
    List<NhomKienThucResDto> getAllNhomKienThuc();
}
