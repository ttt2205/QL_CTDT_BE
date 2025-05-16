package com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.impl;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.GiangVienDetailsResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories.GiangVienDetailsRepo;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.GiangVienDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiangVienDetailsServiceImpl implements GiangVienDetailsService {

    @Autowired
    private GiangVienDetailsRepo repo;

    @Override
    public List<GiangVienDetailsResDto> getPhanCongByGiangVienId(Long idGV) {
        return repo.findPhanCongByGiangVienId(idGV);
    }
}
