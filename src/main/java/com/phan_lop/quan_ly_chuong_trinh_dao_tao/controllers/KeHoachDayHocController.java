package com.phan_lop.quan_ly_chuong_trinh_dao_tao.controllers;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.ApiResponse;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.KeHoachDayHocReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.ChiTietThongTinChungResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.KeHoachDayHocResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.ThongTinChung;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.mappers.ChiTietThongTinChungMapper;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.KeHoachDayHocService;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.ThongTinChungService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ke-hoach-day-hoc")
@AllArgsConstructor
public class KeHoachDayHocController {

    private ThongTinChungService thongTinChungService;
    @Qualifier("chiTietThongTinChungMapper")
    private ChiTietThongTinChungMapper chiTietMapper;
    private KeHoachDayHocService keHoachDayHocService;

    @GetMapping("/{thongTinChungId}")
    public ResponseEntity<ChiTietThongTinChungResDto> getKeHoachDayHoc(@PathVariable Long thongTinChungId) {
        ThongTinChung thongTinChung = thongTinChungService.findThongTinChungyById(thongTinChungId);
        return ResponseEntity.ok(chiTietMapper.toDto(thongTinChung));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createKeHoachDayHoc(@RequestBody KeHoachDayHocReqDto reqDto) {
        keHoachDayHocService.createKeHoachDayHoc(reqDto);
        return ResponseEntity.ok(ApiResponse.builder()
                .statusCode(200)
                .message("Tao ke hoach day hoc thanh cong")
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateKeHoachDayHoc(@PathVariable Long id, @RequestBody KeHoachDayHocReqDto reqDto) {
        keHoachDayHocService.updateKeHoachDayHoc(id, reqDto);
        return ResponseEntity.ok(ApiResponse.builder()
                .statusCode(200)
                .message("Cap nhat ke hoach day hoc thanh cong")
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteKeHoachDayHoc(@PathVariable Long id) {
        keHoachDayHocService.deleteKeHoachDayHoc(id);
        return ResponseEntity.ok(ApiResponse.builder()
                .statusCode(200)
                .message("Xoa ke hoach day hoc thanh cong")
                .build());
    }
    
}