package com.example.demo.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ForexOpenApiDto {
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
  @JsonProperty("Date")
  private Date date;

  @JsonProperty("USD/NTD")
  private String usd;

  @JsonProperty("RMB/NTD")
  private String rmb;

  public ForexOpenApiDto() {
  }

  public ForexOpenApiDto(Date date, String usd, String rmb) {
    this.date = date;
    this.usd = usd;
    this.rmb = rmb;
  }
}
