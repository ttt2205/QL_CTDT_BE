package com.phan_lop.quan_ly_chuong_trinh_dao_tao.controllers;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.KeHoachMoNhomReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.KeHoachMoNhomResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.mapper.KeHoachMoNhomMapper;
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

    @GetMapping("/{id}")
    public ResponseEntity<KeHoachMoNhomResDto> findById(@PathVariable Long id) {
        KeHoachMoNhomResDto khDto = mapper.entityToDto(keHoachMoNhomService.findById(id));
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
//
//    @GetMapping("{id}")
//    public ResponseEntity<?> getByIdMethodName(@PathVariable Long id) {
//        return null;
//    }
//
//    @PutMapping("{id}")
//    public ResponseEntity<?> putMethodName(@PathVariable Long id, @RequestBody String entity) {
//        return null;
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<?> deleteMethodName(@PathVariable Long id) {
//        return null;
//    }
}