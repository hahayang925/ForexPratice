package com.example.demo.dto;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ForexRequestDto {
  @NotNull
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @JsonProperty("startDate")
  private LocalDate startDate;
  
  @NotNull
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @JsonProperty("endDate")
  private LocalDate endDate;

  @NotNull
  @JsonProperty("currency")
  private String currency;

  public boolean isValidDateRule() {
    
    LocalDate today = LocalDate.now();

    boolean isWithOneYear = !startDate.isBefore(today.minus(1, ChronoUnit.YEARS)) && !startDate.isAfter(today);
    boolean isEndDateAfterStartDate = startDate.isBefore(endDate);
    boolean isEndDateBeforeToday = endDate.isBefore(today);

    return isWithOneYear && isEndDateAfterStartDate && isEndDateBeforeToday;
  }

  public ForexRequestDto() {
  }

  public ForexRequestDto(LocalDate startDate, LocalDate endDate, String currency) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.currency = currency;
  }
}
