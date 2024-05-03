package org.sopt.daangnMarket.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@Builder
public class ApiResponse<T> {

    private final boolean success;
    private final int code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;

    // 성공 응답
    public static <T> ApiResponse<T> success(HttpStatus status, String message, T result) {
        return ApiResponse.<T>builder()
                .success(true)
                .code(status.value())
                .message(message)
                .data(result)
                .build();
    }

    // 결과가 없는 성공 응답
    public static ApiResponse<Void> success(HttpStatus status, String message) {
        return ApiResponse.<Void>builder()
                .success(true)
                .code(status.value())
                .message(message)
                .build();
    }

    // 오류 응답
    public static ApiResponse<String> error(HttpStatus status, String message) {
        return ApiResponse.<String>builder()
                .success(false)
                .code(status.value())
                .message(message)
                .build();
    }
}

