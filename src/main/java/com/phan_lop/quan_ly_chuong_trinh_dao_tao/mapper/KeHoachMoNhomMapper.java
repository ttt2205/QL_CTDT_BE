package com.phan_lop.quan_ly_chuong_trinh_dao_tao.mapper;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.KeHoachMoNhomResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.KeHoachMoNhom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {PhanCongGiangDayMapper.class, HocPhanMapper.class})
public interface KeHoachMoNhomMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    KeHoachMoNhomResDto entityToDto(KeHoachMoNhom khDto);

    default List<KeHoachMoNhomResDto> toListDto(List<KeHoachMoNhom> keHoachMoNhomList) {
        return keHoachMoNhomList.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
