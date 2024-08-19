package com.example.demo.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ForexResponseDto {
  // @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
  @JsonFormat(pattern = "yyyyMMdd")
  @JsonProperty("date")
  private Date date;

  @JsonProperty("usd")
  private String usd;

  public ForexResponseDto(Date date, String usd) {
    this.date = date;
    this.usd = usd;
  }
}
