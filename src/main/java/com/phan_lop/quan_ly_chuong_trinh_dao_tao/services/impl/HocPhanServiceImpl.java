package com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.impl;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.HocPhan;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.exception.BadRequestException;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories.HocPhanRepo;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.HocPhanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HocPhanServiceImpl implements HocPhanService {

    private HocPhanRepo hocPhanRepo;

    @Override
    public List<HocPhan> getAll() {
        return hocPhanRepo.findAll();
    }

    @Override
    public HocPhan findByMaHocPhan(String maHocPhan) {
        return hocPhanRepo.findByMaHocPhan(maHocPhan).orElseThrow(() -> new BadRequestException("Ma hoc phan not existed"));
    }
}
