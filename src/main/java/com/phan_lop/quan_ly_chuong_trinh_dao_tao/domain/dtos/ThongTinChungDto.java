package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ThongTinChungDto {
    private Long id;

    private NganhDto nganh;

    private String gioiThieu;

    private String khoaQuanLy;

    private String loaiBang;

    private String loaiHinhDaoTao;

    private int tongTinChi;

    private String ngonNgu;

    private String website;

    private Double thoiGianDaoTao;

    private String banHanh;

    private boolean status;
}
