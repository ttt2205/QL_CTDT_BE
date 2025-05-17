package com.phan_lop.quan_ly_chuong_trinh_dao_tao.mappers;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.GiangVienResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.TongHopKeHoachMoNhom.TongHopGiangVienDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.GiangVien;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface GiangVienMapper {

    GiangVienResDto entityToDto(GiangVien entity);

    @Mapping(target = "maGiangVien", source = "id")
    @Mapping(target = "hoVaTen", source = "ten")
    TongHopGiangVienDto toTongHopGiangVienDto(GiangVien gvien);

    default List<GiangVienResDto> toListResDto(List<GiangVien> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }

}
