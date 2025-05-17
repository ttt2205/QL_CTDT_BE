package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request;

import lombok.Data;

@Data
public class GiangVienReqDto {
    private Long userId;
    private String ten;
    private String chucDanh;
    private String boMon;
    private String khoa;
    private String trinhDo;
    private String chuyenMon;
    private Long namSinh;
}
