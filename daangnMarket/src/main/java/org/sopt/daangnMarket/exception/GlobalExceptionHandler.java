package org.sopt.daangnMarket.exception;

import org.sopt.daangnMarket.util.ApiResponse;
import org.sopt.daangnMarket.util.ApiUtils;
import org.sopt.daangnMarket.util.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ApiUtils.validError(HttpStatus.BAD_REQUEST, Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleNotFoundException(NotFoundException e) {
        ErrorMessage errorMessage = e.getErrorMessage();
        return ApiUtils.error(HttpStatus.valueOf(errorMessage.getStatus()), errorMessage);
    }

    @ExceptionHandler(DuplicateMemberException.class)
    public ResponseEntity<ApiResponse<String>> handleDuplicateMemberException(DuplicateMemberException e) {
        ErrorMessage errorMessage = e.getErrorMessage();
        return ApiUtils.error(HttpStatus.valueOf(errorMessage.getStatus()), errorMessage);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiResponse<String>> handleConflictException(ConflictException e) {
        ErrorMessage errorMessage = e.getErrorMessage();
        return ApiUtils.error(HttpStatus.valueOf(errorMessage.getStatus()), errorMessage);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse<String>> handleUnauthorizedException(UnauthorizedException e) {
        ErrorMessage errorMessage = e.getErrorMessage();
        return ApiUtils.error(HttpStatus.valueOf(errorMessage.getStatus()), errorMessage);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiResponse<String>> handleForbiddenException(ForbiddenException e) {
        ErrorMessage errorMessage = e.getErrorMessage();
        return ApiUtils.error(HttpStatus.valueOf(errorMessage.getStatus()), errorMessage);
    }
}