package com.phan_lop.quan_ly_chuong_trinh_dao_tao.controllers;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.ApiResponse;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.KhoiKienThucReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.KhoiKienThucResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.KeHoachDayHocService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/khoi-kien-thuc")
@AllArgsConstructor
public class KhoiKienThucController {

    private KeHoachDayHocService keHoachDayHocService;

    @GetMapping
    public ResponseEntity<List<KhoiKienThucResDto>> findAll() {
        return ResponseEntity.ok(keHoachDayHocService.getAllKhoiKienThuc());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createKhoiKienThuc(@RequestBody KhoiKienThucReqDto reqDto) {
        keHoachDayHocService.createKhoiKienThuc(reqDto);
        return ResponseEntity.ok(ApiResponse.builder()
                                .statusCode(200)
                                .message("Tao khoi kien thuc thanh cong")
                                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateKhoiKienThuc(@PathVariable Long id, @RequestBody KhoiKienThucReqDto reqDto) {
        keHoachDayHocService.updateKhoiKienThuc(id, reqDto);
        return ResponseEntity.ok(ApiResponse.builder()
                .statusCode(200)
                .message("Cap nhat khoi kien thuc thanh cong")
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteKhoiKienThuc(@PathVariable Long id) {
        keHoachDayHocService.deleteKhoiKienThuc(id);
        return ResponseEntity.ok(ApiResponse.builder()
                .statusCode(200)
                .message("Xoa khoi kien thuc thanh cong")
                .build());
    }

}
