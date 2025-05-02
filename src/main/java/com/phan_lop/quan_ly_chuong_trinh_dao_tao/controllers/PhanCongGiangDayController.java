package com.phan_lop.quan_ly_chuong_trinh_dao_tao.controllers;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.PhanCongGiangDayReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.PhanCongGiangDayResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.PhanCongGiangDay;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.mapper.PhanCongGiangDayMapper;
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
//    private GiangVienService giangVienService;
    @Qualifier("phanCongGiangDayMapper")
    private PhanCongGiangDayMapper mapper;

    @GetMapping
    public ResponseEntity<List<PhanCongGiangDayResDto>> getByKeHoachMoNhom(
            @RequestParam Long keHoachMoNhomId) {
        return ResponseEntity.ok(null);
    }

    @PostMapping
    public ResponseEntity<PhanCongGiangDayResDto> addPhanCongGiangDay(
            @RequestBody PhanCongGiangDayReqDto reqDto) {
        PhanCongGiangDay pc = phanCongGiangDayService.addPhanCongGiangDay(reqDto);
        return ResponseEntity.ok(mapper.entityToDto(pc));
    }
}
