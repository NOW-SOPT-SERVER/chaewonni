package org.sopt.daangnMarket.exception;

import org.sopt.daangnMarket.util.ApiResponse;
import org.sopt.daangnMarket.util.ApiUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ApiResponse<String>> handleCustomException(RuntimeException e) {
        ApiErrorCode errorCode;
        if (e instanceof NotFoundException) {
            errorCode = ((NotFoundException) e).getErrorCode();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error"));
        }
        return ApiUtils.error(HttpStatus.valueOf(errorCode.getCode()), errorCode);
    }
}
