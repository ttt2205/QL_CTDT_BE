package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response;

import lombok.Data;

import java.util.List;

@Data
public class ChiTietKhoiKienThucResDto {
    public int id;
    public String name;
    public List<ChiTietNhomKienThucResDto> listNhomKienThuc;
}
