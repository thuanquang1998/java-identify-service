package com.ethan.identity_service.exception;

import com.ethan.identity_service.dto.request.ApiRespnose;
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
    ResponseEntity<ApiRespnose> handlingRuntimeException(RuntimeException e) {
        ApiRespnose apiRespnose = new ApiRespnose();
        apiRespnose.setCode(1001);
        apiRespnose.setMessage(e.getMessage());
        return ResponseEntity.badRequest().body(apiRespnose);
    }

    @ExceptionHandler(value=AppException.class)
    ResponseEntity<ApiRespnose> handlingAppException(AppException e) {
        ErrorCode errorCode = e.getErrorCode();
        ApiRespnose apiRespnose = new ApiRespnose();
        apiRespnose.setCode(errorCode.getCode());
        apiRespnose.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiRespnose);
    }

    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    ResponseEntity<ApiRespnose> handlingValidation(MethodArgumentNotValidException e) {
        System.out.println("MethodArgumentNotValidException");
        String enumKey = Objects.requireNonNull(e.getFieldError()).getDefaultMessage();

        ErrorCode errorCode = ErrorCode.INVALID_KEY;
        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException ex) {
            errorCode = ErrorCode.INVALID_KEY;
        }
        ApiRespnose apiRespnose = new ApiRespnose();
        apiRespnose.setCode(errorCode.getCode());
        apiRespnose.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiRespnose);
    }

    // fallback exception
    @ExceptionHandler(value=Exception.class)
    ResponseEntity<ApiRespnose> handlingAppException(Exception e) {
        ApiRespnose apiRespnose = new ApiRespnose();
        apiRespnose.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiRespnose.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
        return ResponseEntity.badRequest().body(apiRespnose);
    }






}
