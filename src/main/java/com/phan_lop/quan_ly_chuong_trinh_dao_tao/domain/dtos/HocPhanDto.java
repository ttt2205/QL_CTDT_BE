package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HocPhanDto {
    private Long id;
    private String maHocPhan;
    private String tenHocPhan;
    private int soTinChi;
    private int soTietLyThuyet;
    private int soTietBaiTap;
    private int soTietThucHanh;
    private String maHocPhanTruoc;
    private boolean status;
    private boolean isDeleted;
}
