package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request;

import lombok.Data;

@Data
public class NhomKienThucReqDto {
    public String maNhom;
    public int soTinChiTuChonToiThieu;
    public String tenNhom;
    public boolean tichLuy;
    public int khoiKienThucId;
}
