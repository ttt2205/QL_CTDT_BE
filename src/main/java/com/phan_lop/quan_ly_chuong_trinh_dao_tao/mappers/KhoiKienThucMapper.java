package com.phan_lop.quan_ly_chuong_trinh_dao_tao.mappers;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.KhoiKienThucReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.ChiTietKhoiKienThucResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.KhoiKienThucResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.KhoiKienThuc;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {NhomKienThucMapper.class})
public interface KhoiKienThucMapper {

    ChiTietKhoiKienThucResDto toDtoChiTiet(KhoiKienThuc khoiKienThuc);

    KhoiKienThucResDto toDto(KhoiKienThuc khoiKienThuc);

    @Mapping(target = "listNhomKienThuc", ignore = true)
    @Mapping(target = "thongTinChung", ignore = true)
    @Mapping(target = "id", ignore = true)
    KhoiKienThuc toCreateEntity(KhoiKienThucReqDto khoiKienThucDto);

    void updateEntityFromDto(KhoiKienThucReqDto dto, @MappingTarget KhoiKienThuc entity);

    default List<KhoiKienThucResDto> toListDto(List<KhoiKienThuc> khoiKienThucList) {
        return khoiKienThucList.stream().map(this::toDto).collect(Collectors.toList());
    }

    default List<ChiTietKhoiKienThucResDto> toListDtoChiTiet(List<KhoiKienThuc> khoiKienThucList) {
        return khoiKienThucList.stream().map(this::toDtoChiTiet).collect(Collectors.toList());
    }
}
