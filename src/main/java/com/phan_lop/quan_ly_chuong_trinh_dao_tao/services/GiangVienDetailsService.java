package com.phan_lop.quan_ly_chuong_trinh_dao_tao.services;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.GiangVienDetailsResDto;
import java.util.List;

public interface GiangVienDetailsService {
    List<GiangVienDetailsResDto> getPhanCongByGiangVienId(Long idGV);
}
