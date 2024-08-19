package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.ForexOpenApiDto;

@Service
public class ApiService {

    @Autowired
    private RestTemplate restTemplate;

    public ForexOpenApiDto[] fetchDailyFreRateApi() {
        String url = "https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates";
        return restTemplate.getForObject(url, ForexOpenApiDto[].class);
    }
}