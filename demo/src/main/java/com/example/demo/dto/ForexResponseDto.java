package com.example.demo.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ForexResponseDto {
  @JsonProperty("date")
  private String date;

  @JsonProperty("usd")
  private String usd;

  @JsonProperty("rmb")
  private String rmb;

  public ForexResponseDto(String date, String usd, String rmb) {
    this.date = date;
    this.usd = usd;
    this.rmb = rmb;
  }
}
