package org.sopt.daangnMarket.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse<T> {
    private final boolean success;
    private final int code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T result;

    @Builder
    public ApiResponse(int code, boolean success, T result) {
        this.success = success;
        this.code = code;
        this.result = result;
    }

    // 성공 응답
    public static <T> ApiResponse<T> success(HttpStatus status, T result) {
        return ApiResponse.<T>builder()
                .success(true)
                .code(status.value())
                .result(result)
                .build();
    }

    // 결과가 없는 성공 응답
    public static ApiResponse<Void> success(HttpStatus status) {
        return ApiResponse.<Void>builder()
                .success(true)
                .code(status.value())
                .build();
    }

    // 오류 응답
    public static ApiResponse<String> error(HttpStatus status, String message) {
        return ApiResponse.<String>builder()
                .success(false)
                .code(status.value())
                .result(message)
                .build();
    }
}

