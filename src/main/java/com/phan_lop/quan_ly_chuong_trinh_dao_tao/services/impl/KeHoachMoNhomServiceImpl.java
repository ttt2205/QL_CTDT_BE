package com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.impl;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.KeHoachMoNhomReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.KeHoachMoNhomResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.HocPhan;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.KeHoachMoNhom;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.exception.BadRequestException;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.mappers.KeHoachMoNhomMapper;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories.KeHoachMoNhomRepository;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.HocPhanService;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.KeHoachMoNhomService;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class KeHoachMoNhomServiceImpl implements KeHoachMoNhomService {

    private KeHoachMoNhomRepository repository;
    @Qualifier("keHoachMoNhomMapper")
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
    public void deleteById(Long id) {
        findById(id);
        repository.deleteById(id);
    }

    @Override
    public KeHoachMoNhom update(Long id, KeHoachMoNhomReqDto reqDto) {
        KeHoachMoNhom keHoachMoNhom = findById(id);
        mapper.updateEntityFromDto(reqDto, keHoachMoNhom);

        HocPhan hocPhan = hocPhanService.findByMaHocPhan(reqDto.getMaHocPhan());
        keHoachMoNhom.setHocPhan(hocPhan);

        return repository.save(keHoachMoNhom);
    }

    @Override
    public KeHoachMoNhomResDto add(KeHoachMoNhomReqDto reqDto) {
        KeHoachMoNhom keHoachMoNhom = mapper.reqDtoToEntity(reqDto);
        HocPhan hp = hocPhanService.findByMaHocPhan(reqDto.getMaHocPhan());
        keHoachMoNhom.setHocPhan(hp);
        keHoachMoNhom.setPhanCongGiangDay(List.of());

        KeHoachMoNhom result = repository.save(keHoachMoNhom);
        return mapper.entityToResDto(result);
    }

}
