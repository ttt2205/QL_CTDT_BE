package com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.impl;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.GiangVien;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.exception.BadRequestException;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories.GiangVienRepository;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.GiangVienService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GiangVienServiceImpl implements GiangVienService {

    private GiangVienRepository giangVienRepository;

    @Override
    public GiangVien findById(Long id) {
        return giangVienRepository.findById(id).orElseThrow(() -> new BadRequestException("Giang Vien Not Found"));
    }

    @Override
    public List<GiangVien> getAll() {
        return giangVienRepository.findAll();
    }
}
