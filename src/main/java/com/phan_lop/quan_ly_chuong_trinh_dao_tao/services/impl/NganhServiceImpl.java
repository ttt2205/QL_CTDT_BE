package com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.NganhDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.Nganh;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.exceptions.CustomException;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories.NganhRepository;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories.specification.NganhSpecification;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.NganhService;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.utils.UrlParamUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NganhServiceImpl implements NganhService {

    private final NganhRepository nganhRepository;

    private final UrlParamUtil urlParamUtil;

    @Override
    public List<Nganh> findAll() {
        return nganhRepository.findAll();
    }

    @Override
    public Page<Nganh> findAll(int page, int size, MultiValueMap<String, String> params) {
        if (params.get("page") != null)
            params.remove("page");
        if (params.get("size") != null)
            params.remove("size");

        // Lấy danh sách tham số `sort` co dang [[id,asc],[name,desc]]
        List<String> sortParams = params.get("sort");
        Sort sorting = urlParamUtil.getSort(sortParams);
        Pageable pageable = PageRequest.of(page, size, sorting);
        Specification<Nganh> spec = NganhSpecification.byFilters(params);
        return nganhRepository.findAll(spec, pageable);
    }

    @Override
    public Nganh findNganhyById(Long id) {
        return nganhRepository.findNganhById(id).orElseThrow(() -> {
            throw CustomException.builder()
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .message("Nganh not found!")
                    .build();
        });
    }

    @Override
    public Nganh createNewNganh(NganhDto nganhDto) {
        try {
            // Kiem tra ma nganh
            Nganh isExistWithMaNganh = nganhRepository.findNganhByMaNganh(nganhDto.getMaNganh());

            if (isExistWithMaNganh != null) {
                throw CustomException.builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .message("Mã Ngành đã tồn tại!")
                        .build();
            }

            Nganh nganhEntity = Nganh.builder()
                    .maNganh(nganhDto.getMaNganh())
                    .tenNganh(nganhDto.getTenNganh())
                    .status(nganhDto.isStatus())
                    .build();
            return nganhRepository.save(nganhEntity);
        } catch (Exception e) {
            System.out.println("createNewNganh error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Nganh updateNganh(Long id, NganhDto nganhDto) {
        try {
            // Kiem tra ma nganh
            Nganh isExistWithMaNganh = nganhRepository.findNganhById(id).orElseThrow(() -> {
                throw CustomException.builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .message("Ngành not found!")
                        .build();
            });

            isExistWithMaNganh.setMaNganh(nganhDto.getMaNganh());
            isExistWithMaNganh.setTenNganh(nganhDto.getTenNganh());
            isExistWithMaNganh.setStatus(nganhDto.isStatus());
            return nganhRepository.save(isExistWithMaNganh);
        } catch (Exception e) {
            System.out.println("updateNganh error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Nganh deleteNganh(Long id) {
        try {
            // Kiem tra ma nganh
            Nganh isExistWithMaNganh = nganhRepository.findNganhById(id).orElseThrow(() -> {
                throw CustomException.builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .message("Ngành not found!")
                        .build();
            });
            isExistWithMaNganh.setDeleted(true);
            return nganhRepository.save(isExistWithMaNganh);
        } catch (Exception e) {
            System.out.println("deleteNganh error: " + e.getMessage());
            throw e;
        }
    }

}
