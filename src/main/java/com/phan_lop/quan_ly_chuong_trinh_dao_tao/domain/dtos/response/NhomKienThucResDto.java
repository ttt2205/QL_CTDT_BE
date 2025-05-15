package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response;

import lombok.Data;

@Data
public class NhomKienThucResDto {
    public String maNhom;
    public int id;
    public int soTinChiTuChonToiThieu;
    public String tenNhom;
    public boolean tichLuy;
    public int khoiKienThucId;
}
