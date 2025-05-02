package com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.impl;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.KeHoachMoNhomReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.KeHoachMoNhomResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.KeHoachMoNhom;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.exception.BadRequestException;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.mapper.KeHoachMoNhomMapper;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories.KeHoachMoNhomRepository;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.HocPhanService;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.KeHoachMoNhomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class KeHoachMoNhomServiceImpl implements KeHoachMoNhomService {

    private KeHoachMoNhomRepository repository;
    private KeHoachMoNhomMapper mapper;
    private HocPhanService hocPhanService;

    public List<KeHoachMoNhom> findByHocPhanId(Long hocPhanId) {
        return repository.findByHocPhanId(hocPhanId);
    }

    public KeHoachMoNhom findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new BadRequestException("Ke hoach mo nhom not found"));
    }

    @Override
    public List<KeHoachMoNhom> findAll() {
        return repository.findAll();
    }

    @Override
    public KeHoachMoNhomResDto add(KeHoachMoNhomReqDto reqDto) {
        KeHoachMoNhom keHoachMoNhom = KeHoachMoNhom.builder()
                .hocPhan(hocPhanService.findByMaHocPhan(reqDto.getMaHocPhan()))
                .khoa(reqDto.getKhoa())
                .heSo(reqDto.getHeSo())
                .tongSoNhom(reqDto.getTongSoNhom())
                .tongSoSinhVien(reqDto.getTongSoSinhVien())
                .namHoc(reqDto.getNamHoc())
                .build();
        KeHoachMoNhom result = repository.save(keHoachMoNhom);
        return mapper.entityToDto(result);
    }
}
