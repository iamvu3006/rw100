package com.vti.exception;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BusinessException extends RuntimeException{
    private Integer status;
    private String message;
}