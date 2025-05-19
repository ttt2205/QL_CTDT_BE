package com.phan_lop.quan_ly_chuong_trinh_dao_tao.controllers;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.ApiResponse;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.HocPhanDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.NganhDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.ThongTinChungDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.HocPhanReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.HocPhanResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.HocPhan;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.Nganh;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.ThongTinChung;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.requests.ThongTinChungRequest;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.mappers.HocPhanMapper;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.HocPhanService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/v1/hoc-phan")
@AllArgsConstructor
public class HocPhanController {

    @Qualifier("hocPhanMapperImpl")
    private HocPhanMapper mapper;
    private HocPhanService hocPhanService;

    @GetMapping
    public ResponseEntity<List<HocPhanResDto>> getAll() {
        return ResponseEntity.ok(mapper.toListDto(hocPhanService.getAll()));
    }
    @GetMapping("/hocphan-exist-decuongchitiet")
    public ResponseEntity<List<HocPhanResDto>> getdecuongchitiet() {
        return ResponseEntity.ok(mapper.toListDto(hocPhanService.getHocPhanWithDeCuongChiTiet()));
    }
    @GetMapping("/{hocPhanId}")
    public ResponseEntity<HocPhanResDto> getById(@PathVariable Long hocPhanId) {
        return ResponseEntity.ok(mapper.entityToDto(hocPhanService.findById(hocPhanId)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteMethodName(@PathVariable Long id) {
        hocPhanService.deleteById(id);
        return ResponseEntity.ok(ApiResponse.builder()
                        .message("delete succes")
                .build());
    }
    @PostMapping
    public ResponseEntity<?> postMethodName(@RequestBody HocPhanDto hocPhanDto) {
        HocPhan hocPhanEntity = hocPhanService.create(hocPhanDto);
        HocPhanDto hocPhanResDto = mapper.toDto(hocPhanEntity);

        ApiResponse<HocPhanDto> apiRespone = ApiResponse.<HocPhanDto>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Create hoc phan data success!")
                .data(hocPhanResDto)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiRespone);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> putMethodName(@PathVariable Long id, @RequestBody HocPhanDto hocPhanDto) {
        HocPhan hocPhanEntity = hocPhanService.update(id, hocPhanDto);
        HocPhanDto hocphanResDto = mapper.toDto(hocPhanEntity);
        ApiResponse<HocPhanDto> apiRespone = ApiResponse.<HocPhanDto>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Update thong tin chung data success!")
                .data(hocphanResDto)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(apiRespone);
    }
}
