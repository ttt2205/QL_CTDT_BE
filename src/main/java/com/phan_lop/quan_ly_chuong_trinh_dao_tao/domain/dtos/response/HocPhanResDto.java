package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HocPhanResDto {
    private Long id;
    private String maHocPhan;
    private String tenHocPhan;
    private int soTinChi;
    private int soTietLyThuyet;
    private int soTietBaiTap;
    private int soTietThucHanh;
    private String maHocPhanTruoc;
    private boolean status;
    private double heSo;

    private int soTietTongCong;
}