package com.phan_lop.quan_ly_chuong_trinh_dao_tao.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.ApiResponse;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.ApiResponseListData;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.ChuongTrinhKhungDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.ErrorResponse;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.ThongTinChungDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.ThongTinChung;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.requests.ThongTinChungRequest;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.mappers.ThongTinChungMapper;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.ThongTinChungService;

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
@RequestMapping("/api/v1/thong-tin-chung")
@RequiredArgsConstructor
public class ThongTinChungController {
        private final ThongTinChungMapper thongTinChungMapper;

        private final ThongTinChungService thongTinChungService;

        @GetMapping
        public ResponseEntity<?> getMethodName(
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size,
                        @RequestParam MultiValueMap<String, String> params) {
                Page<ThongTinChung> thongTinChungPage = thongTinChungService.findAll(page, size,
                                params);
                // Lay danh sach da loc
                List<ThongTinChung> results = thongTinChungPage.getContent();
                List<ThongTinChungDto> thongTinChungDtos = results.stream()
                                .map(thongTinChungMapper::toDto)
                                .toList();

                ApiResponseListData<ThongTinChungDto> apiRespone = ApiResponseListData.<ThongTinChungDto>builder()
                                .statusCode(HttpStatus.OK.value())
                                .message("Get Thong Tin Chung data success!")
                                .metadata(Map.of(
                                                "totalElements", thongTinChungPage.getTotalElements(),
                                                "totalPage", thongTinChungPage.getTotalPages(),
                                                "elementOfPage", thongTinChungPage.getNumberOfElements(),
                                                "currentPage", thongTinChungPage.getNumber()))
                                .data(thongTinChungDtos)
                                .build();
                return ResponseEntity.ok(apiRespone);
        }

        @PostMapping
        public ResponseEntity<?> postMethodName(@RequestBody ThongTinChungRequest thongTinChungRequest) {
                ThongTinChung thongTinChungEntity = thongTinChungService.createNewThongTinChung(thongTinChungRequest);
                ThongTinChungDto thongTinChungDtoResponse = thongTinChungMapper.toDto(thongTinChungEntity);
                ApiResponse<ThongTinChungDto> apiRespone = ApiResponse.<ThongTinChungDto>builder()
                                .statusCode(HttpStatus.CREATED.value())
                                .message("Create thongTinChung data success!")
                                .data(thongTinChungDtoResponse)
                                .build();
                return ResponseEntity.status(HttpStatus.CREATED).body(apiRespone);
        }

        @GetMapping("{id}")
        public ResponseEntity<?> getByIdMethodName(@PathVariable Long id) {
                ThongTinChung thongTinChungEntity = thongTinChungService.findThongTinChungyById(id);
                ThongTinChungDto thongTinChungDto = thongTinChungMapper.toDto(thongTinChungEntity);
                ApiResponse<ThongTinChungDto> apiRespone = ApiResponse.<ThongTinChungDto>builder()
                                .statusCode(HttpStatus.OK
                                                .value())
                                .message("Get thong tin chung data success!")
                                .data(thongTinChungDto)
                                .build();
                return ResponseEntity.status(HttpStatus.OK).body(apiRespone);
        }

        @PutMapping("{id}")
        public ResponseEntity<?> putMethodName(@PathVariable Long id,
                        @RequestBody ThongTinChungRequest thongTinChungRequest) {
                ThongTinChung thongTinChungUpdated = thongTinChungService.updateThongTinChung(id, thongTinChungRequest);
                ThongTinChungDto thongTinChungDtoResponse = thongTinChungMapper.toDto(thongTinChungUpdated);
                ApiResponse<ThongTinChungDto> apiRespone = ApiResponse.<ThongTinChungDto>builder()
                                .statusCode(HttpStatus.OK.value())
                                .message("Update thong tin chung data success!")
                                .data(thongTinChungDtoResponse)
                                .build();
                return ResponseEntity.status(HttpStatus.OK)
                                .body(apiRespone);
        }

        @DeleteMapping("{id}")
        public ResponseEntity<?> deleteMethodName(@PathVariable Long id) {
                ThongTinChung deleteThongTinChung = thongTinChungService.deleteThongTinChung(id);
                ThongTinChungDto deleteThongTinChungDto = thongTinChungMapper.toDto(deleteThongTinChung);

                ApiResponse<ThongTinChungDto> apiRespone = ApiResponse.<ThongTinChungDto>builder()
                                .statusCode(HttpStatus.OK.value())
                                .message("Delete thong tin chung data success!")
                                .data(deleteThongTinChungDto)
                                .build();
                return ResponseEntity.status(HttpStatus.OK)
                                .body(apiRespone);
        }

        @GetMapping("/get-chuong-trinh-khung/{id}")
        public ResponseEntity<?> getMethodName(@PathVariable Long id) {
                List<ChuongTrinhKhungDto> response = thongTinChungService.getChuongTrinhKhungByThongTinChungId(id);
                ApiResponseListData<ChuongTrinhKhungDto> apiRespone = ApiResponseListData.<ChuongTrinhKhungDto>builder()
                                .statusCode(HttpStatus.OK.value())
                                .message("Get Thong Tin Chung data success!")
                                .metadata(null)
                                .data(response)
                                .build();
                return ResponseEntity.ok(apiRespone);
        }

}
