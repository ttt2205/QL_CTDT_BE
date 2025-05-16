package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response;


import lombok.Data;

import java.util.List;

@Data
public class ChiTietThongTinChungResDto {
    public Long id;
    public String maNganh;
    public String tenNganh;
    public List<ChiTietKhoiKienThucResDto> listKhoiKienThuc;
}
