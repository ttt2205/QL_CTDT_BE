package com.phan_lop.quan_ly_chuong_trinh_dao_tao.services;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.NganhDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.Nganh;

public interface NganhService {
    public List<Nganh> findAll();

    public Page<Nganh> findAll(int page, int size, MultiValueMap<String, String> params);

    public Nganh findNganhyById(Long id);

    public Nganh createNewNganh(NganhDto nganhDto);

    public Nganh updateNganh(Long id, NganhDto nganhDto);

    public Nganh deleteNganh(Long id);
}
