package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.TongHopKeHoachMoNhom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class TongHopKeHoachMoNhomDto {
    public TongHopGiangVienDto giangVien;
    public List<TongHopPhanCongDto> phanCongGiangDay;
}
