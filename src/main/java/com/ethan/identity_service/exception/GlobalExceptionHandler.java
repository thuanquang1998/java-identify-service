package com.ethan.identity_service.exception;

import com.ethan.identity_service.dto.request.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Old version (non normalized)
     * */
//    @ExceptionHandler(value=RuntimeException.class)
//    ResponseEntity<String> handlingRuntimeException(RuntimeException e) {
//        return ResponseEntity.badRequest().body(e.getMessage());
//    }

//    @ExceptionHandler(value= MethodArgumentNotValidException.class)
//    ResponseEntity<String> handlingValidation(MethodArgumentNotValidException e) {
//        return ResponseEntity.badRequest().body(e.getFieldError().getDefaultMessage());
//    }

    /**
     * New version (normalized)
     * */
    @ExceptionHandler(value=RuntimeException.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException e) {
        ApiResponse ApiResponse = new ApiResponse();
        ApiResponse.setCode(1001);
        ApiResponse.setMessage(e.getMessage());
        return ResponseEntity.badRequest().body(ApiResponse);
    }

    @ExceptionHandler(value=AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException e) {
        ErrorCode errorCode = e.getErrorCode();
        ApiResponse ApiResponse = new ApiResponse();
        ApiResponse.setCode(errorCode.getCode());
        ApiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(ApiResponse);
    }

    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException e) {
        System.out.println("MethodArgumentNotValidException");
        String enumKey = Objects.requireNonNull(e.getFieldError()).getDefaultMessage();

        ErrorCode errorCode = ErrorCode.INVALID_KEY;
        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException ex) {
            errorCode = ErrorCode.INVALID_KEY;
        }
        ApiResponse ApiResponse = new ApiResponse();
        ApiResponse.setCode(errorCode.getCode());
        ApiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(ApiResponse);
    }

    // fallback exception
    @ExceptionHandler(value=Exception.class)
    ResponseEntity<ApiResponse> handlingAppException(Exception e) {
        ApiResponse ApiResponse = new ApiResponse();
        ApiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        ApiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
        return ResponseEntity.badRequest().body(ApiResponse);
    }






}
