package com.phan_lop.quan_ly_chuong_trinh_dao_tao.mappers;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.DeCuongChiTietDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.DeCuongChiTiet;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface DeCuongChiTietMapper {
    default List<DeCuongChiTietDto> toListDto(List<DeCuongChiTiet> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }
    DeCuongChiTiet toEntity(DeCuongChiTietDto deCuongChiTietDto);
    DeCuongChiTietDto toDto(DeCuongChiTiet deCuongChiTietEntity);
}
