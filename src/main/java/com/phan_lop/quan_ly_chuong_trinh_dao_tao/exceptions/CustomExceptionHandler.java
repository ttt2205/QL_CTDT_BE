package com.phan_lop.quan_ly_chuong_trinh_dao_tao.exceptions;

import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@ControllerAdvice
@Slf4j
@Order(1) // Ưu tiên cao hơn
public class CustomExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleErrorResponse(CustomException ex) {
        ErrorResponse error = ErrorResponse.builder()
                .statusCode(ex.getStatusCode())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, ex.getHttpStatusCode());
    }
}
