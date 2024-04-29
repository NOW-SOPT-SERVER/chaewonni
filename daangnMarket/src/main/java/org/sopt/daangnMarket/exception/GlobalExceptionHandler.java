package org.sopt.daangnMarket.exception;

import org.sopt.daangnMarket.util.ApiResponse;
import org.sopt.daangnMarket.util.ApiUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleNotFoundException(NotFoundException e) {
        ApiErrorCode errorCode = e.getErrorCode();
        return ApiUtils.error(HttpStatus.valueOf(errorCode.getStatus()), errorCode);
    }

    @ExceptionHandler(DuplicateMemberException.class)
    public ResponseEntity<ApiResponse<String>> handleDuplicateMemberException(DuplicateMemberException e) {
        ApiErrorCode errorCode = e.getErrorCode();
        return ApiUtils.error(HttpStatus.valueOf(errorCode.getStatus()), errorCode);
    }
}
