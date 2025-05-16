package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response;

import lombok.Data;

import java.util.List;

@Data
public class KeHoachDayHocResDto {
    public Long id;
    public boolean batBuoc;
    public int hocPhanId;
    public String maHocPhan;
    public String tenHocPhan;
    public String maHocPhanTruoc;
    public int soTinChi;
    public List<Integer> hocKi;
}
