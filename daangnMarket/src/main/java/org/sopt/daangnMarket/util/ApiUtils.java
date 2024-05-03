package org.sopt.daangnMarket.util;

import org.sopt.daangnMarket.util.dto.ErrorMessage;
import org.sopt.daangnMarket.util.dto.SuccessMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiUtils {

    public static <T> ResponseEntity<ApiResponse<T>> success(HttpStatus status, SuccessMessage successMessage, T data) {
        return ResponseEntity.status(status).body(ApiResponse.success(status, successMessage.getMessage(), data));
    }

    public static ResponseEntity<ApiResponse<Void>> success(HttpStatus status, SuccessMessage successMessage) {
        return ResponseEntity.status(status).body(ApiResponse.success(status, successMessage.getMessage()));
    }

    public static ResponseEntity<ApiResponse<String>> error(HttpStatus status, ErrorMessage errorMessage) {
        return ResponseEntity.status(status).body(ApiResponse.error(status, errorMessage.getMessage()));
    }

    public static ResponseEntity<ApiResponse<String>> validError(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(ApiResponse.error(status, message));
    }
}