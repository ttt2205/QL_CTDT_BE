package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response;

import lombok.Data;

import java.util.List;

@Data
public class ChiTietNhomKienThucResDto {
    public int id;
    public String tenNhom;
    public String maNhom;
    public int soTinChiTuChonToiThieu;
    public int khoiKienThucId;
    public boolean tichLuy;
    public List<KeHoachDayHocResDto> listKeHoachDayHoc;
}
