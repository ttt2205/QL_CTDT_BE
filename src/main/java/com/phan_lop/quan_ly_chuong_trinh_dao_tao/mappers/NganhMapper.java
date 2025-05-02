package com.phan_lop.quan_ly_chuong_trinh_dao_tao.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.NganhDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.Nganh;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface NganhMapper {
    NganhDto toDto(Nganh nganh);

    Nganh toEntity(NganhDto nganhDto);
}
