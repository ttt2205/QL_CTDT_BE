package com.phan_lop.quan_ly_chuong_trinh_dao_tao.controllers;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.ApiResponse;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.DeCuongChiTietDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.HocPhanDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.mappers.DeCuongChiTietMapper;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.DeCuongChiTietService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api/v1/de-cuong-chi-tiet")
@AllArgsConstructor
public class DeCuongChiTietController {
    @Qualifier("DeCuongChiTietMapper")
    private DeCuongChiTietMapper mapper;
    private DeCuongChiTietService deCuongChiTietService;


    @GetMapping("/{hocPhanId}")
    public ResponseEntity<List<DeCuongChiTietDto>> getById(@PathVariable Long hocPhanId) {
        return ResponseEntity.ok(mapper.toListDto(deCuongChiTietService.findByHocPhanId(hocPhanId)));
    }
    @PostMapping
    public ResponseEntity<?> postMethodName(@RequestBody List<DeCuongChiTietDto> deCuongChiTietDtoList) {
        deCuongChiTietService.create(deCuongChiTietDtoList);
        ApiResponse<HocPhanDto> apiRespone = ApiResponse.<HocPhanDto>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Create hoc phan data success!")
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiRespone);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> putMethodName(@PathVariable Long id, @RequestBody List<DeCuongChiTietDto> deCuongChiTietDtoList) {
        deCuongChiTietService.update(id,deCuongChiTietDtoList);
        ApiResponse<HocPhanDto> apiRespone = ApiResponse.<HocPhanDto>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Update hoc phan data success!")
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiRespone);
    }

    @DeleteMapping("{hocPhanId}")
    public ResponseEntity<?> deleteMethodName(@PathVariable Long hocPhanId) {
        //xoa tat ca de cuong chi tiet theo hoc phan Id
        deCuongChiTietService.deleteByHocPhanId(hocPhanId);
        ApiResponse<HocPhanDto> apiRespone = ApiResponse.<HocPhanDto>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Update hoc phan data success!")
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiRespone);
    }
}