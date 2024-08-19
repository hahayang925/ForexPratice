package com.example.demo.dto;

import java.time.temporal.ChronoUnit;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import com.example.demo.config.LocalDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ForexRequestDto {
  @NotNull
  // @JsonFormat(pattern = "yyyy/MM/dd")
  // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
  // @JsonFormat(pattern = "yyyy/MM/dd")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  // @JsonDeserialize(using = LocalDateDeserializer.class)
  // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
  // @JsonFormat(pattern = "yyyy/MM/dd")
  @JsonProperty("startDate")
  private LocalDate startDate;
  
  @NotNull
  // @JsonFormat(pattern = "yyyy/MM/dd")
  // @DateTimeFormat(pattern = "yyyy-MM-dd")]
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  // @JsonDeserialize(using = CustomDateDeserializer.class)
  // @JsonDeserialize(using = LocalDateDeserializer.class)
  // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
  // @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
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
