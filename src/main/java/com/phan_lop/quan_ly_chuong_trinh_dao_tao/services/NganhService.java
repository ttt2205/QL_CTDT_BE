package com.phan_lop.quan_ly_chuong_trinh_dao_tao.services;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.NganhDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.Nganh;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.exception.BadRequestException;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories.NganhRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NganhService {

    private final NganhRepo nganhRepo;

    public NganhDto getByMaNganh(String maNganh) {
        Nganh nganh = nganhRepo.getByMaNganh(maNganh).orElseThrow(() -> new BadRequestException("Nganh is not exitst"));
        return NganhDto.builder().maNganh(nganh.getMaNganh()).tenNganh(nganh.getTenNganh()).build();
    }

}
