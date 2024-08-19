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

  public ForexOpenApiDto() {
  }

  public ForexOpenApiDto(Date date, String usd) {
    this.date = date;
    this.usd = usd;
  }
}
