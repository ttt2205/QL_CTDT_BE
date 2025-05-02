package com.phan_lop.quan_ly_chuong_trinh_dao_tao.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.ApiResponse;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.ApiResponseListData;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.NganhDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.NganhDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.NganhDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.Nganh;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.Nganh;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.Nganh;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.mappers.NganhMapper;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.NganhService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/nganh")
@RequiredArgsConstructor
public class NganhController {

        private final NganhMapper nganhMapper;

        private final NganhService nganhService;

        @GetMapping
        public ResponseEntity<?> getMethodName(
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size,
                        @RequestParam MultiValueMap<String, String> params) {
                Page<Nganh> nganhPage = nganhService.findAll(page, size,
                                params);
                // Lay danh sach da loc
                List<Nganh> results = nganhPage.getContent();
                List<NganhDto> nganhDtos = results.stream()
                                .map(nganhMapper::toDto)
                                .toList();

                ApiResponseListData<NganhDto> apiRespone = ApiResponseListData.<NganhDto>builder()
                                .statusCode(HttpStatus.OK.value())
                                .message("Get Thong Tin Chung data success!")
                                .metadata(Map.of(
                                                "totalElements", nganhPage.getTotalElements(),
                                                "totalPage", nganhPage.getTotalPages(),
                                                "elementOfPage", nganhPage.getNumberOfElements(),
                                                "currentPage", nganhPage.getNumber()))
                                .data(nganhDtos)
                                .build();
                return ResponseEntity.ok(apiRespone);
        }

        @GetMapping("/get-list")
        public ResponseEntity<?> getList() {
                List<Nganh> listEntity = nganhService.findAll();
                List<NganhDto> listDtos = listEntity.stream().map(nganhMapper::toDto).toList();
                ApiResponseListData<NganhDto> apiRespone = ApiResponseListData.<NganhDto>builder()
                                .statusCode(HttpStatus.OK.value())
                                .message("Get list nganh data success!")
                                .data(listDtos)
                                .build();
                return ResponseEntity.status(HttpStatus.OK).body(apiRespone);
        }

        @PostMapping
        public ResponseEntity<?> postMethodName(@RequestBody NganhDto nganhDto) {
                Nganh nganhEntity = nganhService.createNewNganh(nganhDto);
                NganhDto nganhDtoResponse = nganhMapper.toDto(nganhEntity);
                ApiResponse<NganhDto> apiRespone = ApiResponse.<NganhDto>builder()
                                .statusCode(HttpStatus.CREATED.value())
                                .message("Create nganh data success!")
                                .data(nganhDtoResponse)
                                .build();
                return ResponseEntity.status(HttpStatus.CREATED).body(apiRespone);
        }

        @GetMapping("{id}")
        public ResponseEntity<?> getByIdMethodName(@PathVariable Long id) {
                Nganh nganhEntity = nganhService.findNganhyById(id);
                NganhDto nganhDto = nganhMapper.toDto(nganhEntity);
                ApiResponse<NganhDto> apiRespone = ApiResponse.<NganhDto>builder()
                                .statusCode(HttpStatus.OK
                                                .value())
                                .message("Get thong tin chung data success!")
                                .data(nganhDto)
                                .build();
                return ResponseEntity.status(HttpStatus.OK).body(apiRespone);
        }

        @PutMapping("{id}")
        public ResponseEntity<?> putMethodName(@PathVariable Long id, @RequestBody NganhDto nganhDto) {
                Nganh nganhUpdated = nganhService.updateNganh(id, nganhDto);
                NganhDto nganhDtoResponse = nganhMapper.toDto(nganhUpdated);
                ApiResponse<NganhDto> apiRespone = ApiResponse.<NganhDto>builder()
                                .statusCode(HttpStatus.OK.value())
                                .message("Update thong tin chung data success!")
                                .data(nganhDtoResponse)
                                .build();
                return ResponseEntity.status(HttpStatus.OK)
                                .body(apiRespone);
        }

        @DeleteMapping("{id}")
        public ResponseEntity<?> deleteMethodName(@PathVariable Long id) {
                Nganh deleteNganh = nganhService.deleteNganh(id);
                NganhDto deleteNganhDto = nganhMapper.toDto(deleteNganh);

                ApiResponse<NganhDto> apiRespone = ApiResponse.<NganhDto>builder()
                                .statusCode(HttpStatus.OK.value())
                                .message("Delete thong tin chung data success!")
                                .data(deleteNganhDto)
                                .build();
                return ResponseEntity.status(HttpStatus.OK)
                                .body(apiRespone);
        }
}
