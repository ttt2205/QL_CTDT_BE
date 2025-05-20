package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response;

import lombok.Data;

@Data
public class GiangVienDetailsResDto {
    private Long maHP;
    private String tenHP;
    private int soTinChi;
    private int soTiet;
    private int hk1;
    private int hk2;
    private int hk3;
    private Long giangVienId;
    private String tenGV;
    private Long namSinh;
    private String chucDanh;
    private String khoa;
    private String trinhDo;

    public GiangVienDetailsResDto(Long maHP, String tenHP, int soTinChi, int soTiet,
                               int hk1, int hk2, int hk3, Long giangVienId,
                               String tenGV, Long namSinh, String chucDanh, String khoa, String trinhDo) {
        this.maHP = maHP;
        this.tenHP = tenHP;
        this.soTinChi = soTinChi;
        this.soTiet = soTiet;
        this.hk1 = hk1;
        this.hk2 = hk2;
        this.hk3 = hk3;
        this.giangVienId = giangVienId;
        this.tenGV = tenGV;
        this.namSinh = namSinh;
        this.chucDanh = chucDanh;
        this.khoa = khoa;
        this.trinhDo = trinhDo;
    }
}
