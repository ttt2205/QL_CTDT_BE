package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChuongTrinhKhungDto {

    private String tenKhoiKienThuc;

    private List<NhomKienThucChuongTrinhKhung> nhomKienThucChuongOfKhoiKienThuc;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class NhomKienThucChuongTrinhKhung {
        private String tenNhom;
        private boolean tichLuy;
        private int soTinChiBatBuoc;
        private int soTinChiTuChon;
    }
}
