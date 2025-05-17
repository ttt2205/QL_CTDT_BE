package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.TongHopKeHoachMoNhom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class TongHopPhanCongDto {
    //lay tu ke hoach mo nhom
    public String tenHocPhan;
    public String maHocPhan;
    public int soTinChi;
    public int soTietHocPhan;
    //tong hop theo giang vien
    public int soLuongNhomLop;
    public List<Integer> hocKyDay;
    public int tongSoTietDay;

}
