package com.phan_lop.quan_ly_chuong_trinh_dao_tao.controllers;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.ApiResponse;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.PhanCongGiangDayReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.PhanCongGiangDayResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.PhanCongGiangDay;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.mappers.PhanCongGiangDayMapper;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.PhanCongGiangDayService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/v1/phan-cong-giang-day")
@AllArgsConstructor
public class PhanCongGiangDayController {

    private PhanCongGiangDayService phanCongGiangDayService;
    @Qualifier("phanCongGiangDayMapper")
    private PhanCongGiangDayMapper mapper;

    //getByKeHoachMoNhom
    @GetMapping
    public ResponseEntity<List<PhanCongGiangDayResDto>> getByKeHoachMoNhom(
            @RequestParam Long keHoachMoNhomId) {
        List<PhanCongGiangDayResDto> data = mapper.toListResDto(phanCongGiangDayService.getByKeHoachMoNhomId(keHoachMoNhomId));
        return ResponseEntity.ok(data);
    }

    @PostMapping
    public ResponseEntity<PhanCongGiangDayResDto> addPhanCongGiangDay(
            @RequestBody PhanCongGiangDayReqDto reqDto) {
        PhanCongGiangDay pc = phanCongGiangDayService.addPhanCongGiangDay(reqDto);
        return ResponseEntity.ok(mapper.entityToDto(pc));
    }

    @GetMapping("/{phanCongId}")
    public ResponseEntity<PhanCongGiangDayResDto> getPhanCongGiangDay(@PathVariable Long phanCongId) {
        return ResponseEntity.ok(mapper.entityToDto(phanCongGiangDayService.findById(phanCongId)));
    }

    @PutMapping("/{phanCongId}")
    public ResponseEntity<ApiResponse<?>> updateByPhanCongId(@PathVariable Long phanCongId, @RequestBody PhanCongGiangDayReqDto reqDto) {
        phanCongGiangDayService.updateById(phanCongId, reqDto);
        return ResponseEntity.ok(ApiResponse.builder()
                .statusCode(200)
                .message("Update phan cong giang day successfully")
                .build());
    }

    @DeleteMapping("/s/{phanCongId}")
    public ResponseEntity<ApiResponse<?>> softDeleteByPhanCongId(@PathVariable Long phanCongId) {
        phanCongGiangDayService.softDeleteById(phanCongId);
        return ResponseEntity.ok(ApiResponse.builder()
                .statusCode(200)
                .message("Soft delete phan cong giang day successfully")
                .build());
    }

    @DeleteMapping("/h/{phanCongId}")
    public ResponseEntity<ApiResponse<?>> deleteByPhanCongId(@PathVariable Long phanCongId) {
        phanCongGiangDayService.hardDeleteById(phanCongId);
        return ResponseEntity.ok(ApiResponse.builder()
                .statusCode(200)
                .message("Hard delete phan cong giang day successfully")
                .build());
    }
}
