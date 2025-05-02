package com.phan_lop.quan_ly_chuong_trinh_dao_tao.services;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.ChuongTrinhKhungDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.ThongTinChung;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.requests.ThongTinChungRequest;

public interface ThongTinChungService {
    public List<ThongTinChung> findAll();

    public Page<ThongTinChung> findAll(int page, int size, MultiValueMap<String, String> params);

    public ThongTinChung findThongTinChungyById(Long id);

    public ThongTinChung createNewThongTinChung(ThongTinChungRequest thongTinChungRequest);

    public ThongTinChung updateThongTinChung(Long id, ThongTinChungRequest thongTinChungRequest);

    public ThongTinChung deleteThongTinChung(Long id);

    public List<ChuongTrinhKhungDto> getChuongTrinhKhungByThongTinChungId(Long id);
}
