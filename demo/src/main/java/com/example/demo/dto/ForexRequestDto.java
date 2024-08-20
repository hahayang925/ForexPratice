package com.example.demo.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ForexRequestDto {
  @NotNull
  @JsonProperty("startDate")
  private String startDate;
  
  @NotNull
  @JsonProperty("endDate")
  private String endDate;

  @NotNull
  @JsonProperty("currency")
  private String currency;

  public boolean isValidDateRule() {
    
    LocalDate today = LocalDate.now();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDate str = LocalDate.parse(startDate, formatter);
    LocalDate end = LocalDate.parse(endDate, formatter);

    boolean isWithOneYear = !str.isBefore(today.minus(1, ChronoUnit.YEARS)) && !str.isAfter(today);
    boolean isEndDateAfterStartDate = str.isBefore(end);
    boolean isEndDateBeforeToday = end.isBefore(today);

    return isWithOneYear && isEndDateAfterStartDate && isEndDateBeforeToday;
  }

  public ForexRequestDto() {
  }

  public ForexRequestDto(String startDate, String endDate, String currency) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.currency = currency;
  }
}
