package com.phan_lop.quan_ly_chuong_trinh_dao_tao.mappers;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.HocPhanResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.HocPhan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface HocPhanMapper {

    @Mapping(target = "soTietTongCong", source = ".", qualifiedByName = "tinhSoTietTongCong")
    HocPhanResDto entityToDto(HocPhan entity);

    @Named("tinhSoTietTongCong")
    default int tinhSoTietTongCong(HocPhan entity) {
        return entity.getSoTietLyThuyet() + entity.getSoTietBaiTap() + entity.getSoTietThucHanh();
    }

    default List<HocPhanResDto> toListDto(List<HocPhan> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
