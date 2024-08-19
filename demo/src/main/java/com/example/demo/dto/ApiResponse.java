package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
  private T currency;
  private ErrorDto error = new ErrorDto();

  public ApiResponse(String errorCode, String message) {
    this.error.setCode(errorCode);
    this.error.setMessage(message);
  }

  public ApiResponse(String errorCode, String message, T data) {
    this.error.setCode(errorCode);
    this.error.setMessage(message);
    this.currency = data;
  }
}