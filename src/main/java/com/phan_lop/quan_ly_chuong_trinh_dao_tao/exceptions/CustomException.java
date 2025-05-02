package com.phan_lop.quan_ly_chuong_trinh_dao_tao.exceptions;

import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class CustomException extends RuntimeException {
    private int statusCode; // Mã lỗi HTTP (mặc định là 500 Internal Server Error)
    private String message; // Thông điệp lỗi
    private HttpStatusCode httpStatusCode;

    public CustomException(int statusCode, String message, HttpStatusCode httpStatusCode) {
        super(message); // 💥 Bổ sung dòng này
        this.statusCode = statusCode;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }
}
