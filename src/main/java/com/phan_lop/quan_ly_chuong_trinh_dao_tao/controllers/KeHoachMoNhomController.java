package com.phan_lop.quan_ly_chuong_trinh_dao_tao.controllers;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.ApiResponse;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.KeHoachMoNhomReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.KeHoachMoNhomResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.mappers.KeHoachMoNhomMapper;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.KeHoachMoNhomService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ke-hoach-mo-nhom")
@AllArgsConstructor
public class KeHoachMoNhomController {

    private KeHoachMoNhomService keHoachMoNhomService;
//    private ModelMapper mapper;
    @Qualifier("keHoachMoNhomMapper")
    private KeHoachMoNhomMapper mapper;

    @GetMapping("/{keHoachMoNhomId}")
    public ResponseEntity<KeHoachMoNhomResDto> findById(@PathVariable Long keHoachMoNhomId) {
        KeHoachMoNhomResDto khDto = mapper.entityToResDto(keHoachMoNhomService.findById(keHoachMoNhomId));
        return ResponseEntity.ok(khDto);
    }

    @GetMapping
    public ResponseEntity<List<KeHoachMoNhomResDto>> getAll() {
        return ResponseEntity.ok(mapper.toListDto(keHoachMoNhomService.findAll()));
    }

    @PostMapping
    public ResponseEntity<KeHoachMoNhomResDto> addKeHoachMoNhom(@RequestBody KeHoachMoNhomReqDto reqDto) {
        return ResponseEntity.ok(keHoachMoNhomService.add(reqDto));
    }

    @PutMapping("/{keHoachMoNhomId}")
    public ResponseEntity<KeHoachMoNhomResDto> updateById(@PathVariable Long keHoachMoNhomId, @RequestBody KeHoachMoNhomReqDto reqDto) {
        KeHoachMoNhomResDto khDto = mapper.entityToResDto(keHoachMoNhomService.update(keHoachMoNhomId, reqDto));
        return ResponseEntity.ok(khDto);
    }

    @DeleteMapping("{keHoachMoNhomId}")
    public ResponseEntity<ApiResponse<?>> deleteById(@PathVariable Long keHoachMoNhomId) {
        keHoachMoNhomService.deleteById(keHoachMoNhomId);
        return ResponseEntity.ok(ApiResponse.builder()
                .statusCode(200)
                .message("Delete keHoachMoNhom successfully")
                .build());
    }
}