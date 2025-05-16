package com.phan_lop.quan_ly_chuong_trinh_dao_tao.mappers;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.NhomKienThucReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.ChiTietNhomKienThucResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.NhomKienThucResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.NhomKienThuc;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {KeHoachDayHocMapper.class})
public interface NhomKienThucMapper {

    @Mapping(target = "khoiKienThucId", source = ".", qualifiedByName = "getKhoiKienThucId")
    ChiTietNhomKienThucResDto toDtoChiTiet(NhomKienThuc nhomKienThuc);

    @Mapping(target = "khoiKienThucId", source = ".", qualifiedByName = "getKhoiKienThucId")
    NhomKienThucResDto toDto(NhomKienThuc nhomKienThuc);

    @Mapping(target = "khoiKienThuc", ignore = true)
    @Mapping(target = "listKeHoachDayHoc", ignore = true)
    @Mapping(target = "id", ignore = true)
    NhomKienThuc toCreateEntity(NhomKienThucReqDto nhomKienThucDto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(NhomKienThucReqDto dto, @MappingTarget NhomKienThuc entity);

    @Named("getKhoiKienThucId")
    default Long getKhoiKienThucId(NhomKienThuc nhomKienThuc) {
        return nhomKienThuc.getKhoiKienThuc().getId();
    }

    default List<ChiTietNhomKienThucResDto> toListDtoChiTiet(List<NhomKienThuc> nhomKienThucList) {
        return nhomKienThucList.stream().map(this::toDtoChiTiet).collect(Collectors.toList());
    }

    default List<NhomKienThucResDto> toListDto(List<NhomKienThuc> nhomKienThucList) {
        return nhomKienThucList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
