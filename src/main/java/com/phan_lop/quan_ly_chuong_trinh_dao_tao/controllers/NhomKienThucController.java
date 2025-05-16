package com.phan_lop.quan_ly_chuong_trinh_dao_tao.controllers;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.ApiResponse;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.NhomKienThucReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.NhomKienThucResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.KeHoachDayHocService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/v1/nhom-kien-thuc")
@AllArgsConstructor
public class NhomKienThucController {

    private KeHoachDayHocService keHoachDayHocService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createNhomKienThuc(@RequestBody NhomKienThucReqDto reqDto) {
        keHoachDayHocService.createNhomKienThuc(reqDto);
        return ResponseEntity.ok(ApiResponse.builder()
                .statusCode(200)
                .message("Tao khoi kien thuc thanh cong")
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateNhomKienThuc(@PathVariable Long id, @RequestBody NhomKienThucReqDto reqDto) {
        keHoachDayHocService.updateNhomKienThuc(id, reqDto);
        return ResponseEntity.ok(ApiResponse.builder()
                .statusCode(200)
                .message("Cap nhat khoi kien thuc thanh cong")
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteNhomKienThuc(@PathVariable Long id) {
        keHoachDayHocService.deleteNhomKienThuc(id);
        return ResponseEntity.ok(ApiResponse.builder()
                .statusCode(200)
                .message("Xoa khoi kien thuc thanh cong")
                .build());
    }
    
    @GetMapping
    public ResponseEntity<List<NhomKienThucResDto>> findAll() {
        return ResponseEntity.ok(keHoachDayHocService.getAllNhomKienThuc());
    }

}
