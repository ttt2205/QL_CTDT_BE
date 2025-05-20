package com.phan_lop.quan_ly_chuong_trinh_dao_tao.exception;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Order(1)
public class GlobalExceptionHandler {

    @ExceptionHandler({BadRequestException.class, RuntimeException.class})
    public ResponseEntity<?> appException(BadRequestException e) {
        return new ResponseEntity<>(ErrorResponseV2.builder()
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .path(e.getLocalizedMessage())
                .build()
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleErrorResponse(CustomException ex) {
        
        ErrorResponse error = ErrorResponse.builder()
                .statusCode(ex.getStatusCode())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, ex.getHttpStatusCode());
    }
}
