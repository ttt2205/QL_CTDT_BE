package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request;

import lombok.Data;

import java.util.List;

@Data
public class KeHoachDayHocReqDto {
    public int hocPhanId;
    public int nhomKienThucId;
    public boolean batBuoc;
    public List<Integer> hocKi;
    public String namHoc;
}
