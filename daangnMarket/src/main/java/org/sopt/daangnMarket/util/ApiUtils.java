package org.sopt.daangnMarket.util;

import org.sopt.daangnMarket.exception.ApiErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiUtils {

    public static <T> ResponseEntity<ApiResponse<T>> success(HttpStatus status, T result) {
        return ResponseEntity.status(status).body(ApiResponse.success(status, result));
    }

    public static ResponseEntity<ApiResponse<Void>> success(HttpStatus status) {
        return ResponseEntity.status(status).body(ApiResponse.success(status));
    }

    public static ResponseEntity<ApiResponse<String>> error(HttpStatus status, ApiErrorCode errorCode) {
        return ResponseEntity.status(status).body(ApiResponse.error(status, errorCode.getMessage()));
    }
}

