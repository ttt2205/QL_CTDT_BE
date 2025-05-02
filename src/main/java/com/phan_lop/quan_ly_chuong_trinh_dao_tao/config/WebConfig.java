package com.phan_lop.quan_ly_chuong_trinh_dao_tao.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Áp dụng cho tất cả các endpoint
                .allowedOriginPatterns("*") // Cho phép tất cả origin, có thể thay bằng cụ thể: "http://localhost:3000"
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Các method được phép
                .allowedHeaders("*") // Cho phép tất cả headers
                .allowCredentials(true) // Cho phép gửi cookie nếu cần
                .maxAge(3600);
    }
}

