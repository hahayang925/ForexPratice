package com.example.demo.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.ForexRequestDto;
import com.example.demo.dto.ForexResponseDto;
import com.example.demo.model.ForexModel;
import com.example.demo.service.ForexService;

@RestController
@RequestMapping("/api")
public class ForexController {

    @Autowired
    private ForexService forexService;

    @GetMapping("/test")
    public ResponseEntity<String> getDocument() {
        return ResponseEntity.ok("hello world");
    }

    @PostMapping("/getForexRate")
    public ResponseEntity<ApiResponse<List<ForexResponseDto>>> getForexRate(@RequestBody @Valid ForexRequestDto request) throws ParseException {
        if (!request.isValidDateRule()) {
            ApiResponse<List<ForexResponseDto>> response = new ApiResponse<>("E001", "日期區間不符");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            List<ForexModel> dataList = forexService.findByDateRangeAndCurrency(request.getStartDate(), request.getEndDate(), request.getCurrency());
            DateFormat df = new SimpleDateFormat("yyyyMMdd");
            List<ForexResponseDto> resList = dataList.stream()
                .map(d -> new ForexResponseDto(df.format(d.getDate()), d.getUsd(), d.getRmb()))
                .collect(Collectors.toList());

            ApiResponse<List<ForexResponseDto>> response = new ApiResponse<>("0000", "成功", resList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}