package com.example.demo.config;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomDateDeserializer extends JsonDeserializer<LocalDate> {

  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

  @Override
  public LocalDate deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException {
    String date = p.getText();
    return LocalDate.parse(date, formatter);
  }
}