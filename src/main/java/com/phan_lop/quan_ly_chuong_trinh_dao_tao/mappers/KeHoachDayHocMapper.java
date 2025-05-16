package com.phan_lop.quan_ly_chuong_trinh_dao_tao.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.KeHoachDayHocReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.KeHoachMoNhomReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.KeHoachDayHocResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.KeHoachDayHoc;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.KeHoachMoNhom;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.KhoiKienThuc;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.utils.JsonConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface KeHoachDayHocMapper {

    @Mapping(target = "maHocPhan", source = ".", qualifiedByName = "getMaHocPhan")
    @Mapping(target = "tenHocPhan", source = ".", qualifiedByName = "getTenHocPhan")
    @Mapping(target = "hocPhanId", source = ".", qualifiedByName = "getHocPhanId")
    @Mapping(target = "maHocPhanTruoc", source = ".", qualifiedByName = "getMaHocPhanTruoc")
//    @Mapping(target = "nhomKienThucId", source = ".", qualifiedByName = "getNhomKienThucId")
    @Mapping(target = "soTinChi", source = ".", qualifiedByName = "getSoTinChi")
    @Mapping(target = "hocKi", source = ".", qualifiedByName = "getListHocKiFromEntity")
    KeHoachDayHocResDto toDto(KeHoachDayHoc keHoachDayHoc);

    @Mapping(target = "hocPhan", ignore = true)
    @Mapping(target = "nhomKienThuc", ignore = true)
    @Mapping(target = "hocKy", source = ".", qualifiedByName = "getStringHocKiFromReqDto")
    KeHoachDayHoc toEntity(KeHoachDayHocReqDto reqDto);

    void updateEntityFromDto(KeHoachDayHocReqDto dto, @MappingTarget KeHoachDayHoc entity);

    @Named("getMaHocPhan")
    default String getMaHocPhan(KeHoachDayHoc keHoachDayHoc) {
        return keHoachDayHoc.getHocPhan().getMaHocPhan();
    }

    @Named("getMaHocPhanTruoc")
    default String getMaHocPhanTruoc(KeHoachDayHoc keHoachDayHoc) {
        return keHoachDayHoc.getHocPhan().getMaHocPhanTruoc();
    }


    @Named("getHocPhanId")
    default Long getHocPhanId(KeHoachDayHoc keHoachDayHoc) {
        return keHoachDayHoc.getHocPhan().getId();
    }

    @Named("getTenHocPhan")
    default String getTenHocPhan(KeHoachDayHoc keHoachDayHoc) {
        return keHoachDayHoc.getHocPhan().getTenHocPhan();
    }

    @Named("getSoTinChi")
    default int getSoTinChi(KeHoachDayHoc keHoachDayHoc) {
        return keHoachDayHoc.getHocPhan().getSoTinChi();
    }

    @Named("getListHocKiFromEntity")
    default List<Integer> getListHocKiFromEntity(KeHoachDayHoc keHoachDayHoc) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Integer> hocKi = mapper.readValue(
                    keHoachDayHoc.getHocKy(),
                    new TypeReference<>() {
                    }
            );
            return hocKi;
        } catch (Exception e) {
            throw new RuntimeException("Cannot parse hocKy JSON to List<Integer>: " + e.getMessage(), e);
        }
    }

    @Named("getStringHocKiFromReqDto")
    default String getStringHocKiFromReqDto(KeHoachDayHocReqDto reqDto) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(reqDto.getHocKi());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting List<Integer> to JSON string", e);
        }
    }

}
