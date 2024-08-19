package com.example.demo.scheduler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.dto.ForexOpenApiDto;
import com.example.demo.model.ForexModel;
import com.example.demo.service.ApiService;
import com.example.demo.service.ForexService;

@Component
public class ForexScheduledTask {

    private static final Logger logger = LoggerFactory.getLogger(ForexScheduledTask.class);

    @Autowired
    private ApiService apiService;

    @Autowired
    private ForexService forexService;
    
    @Scheduled(cron = "0 0 18 * * ?")  // 每日晚上六點執行一次
    public void getOpenApiForexData() {
        try {
            ForexOpenApiDto[] response = apiService.fetchDailyFreRateApi();
            
            List<ForexModel> listData = Arrays.asList(response)
                .stream()
                .map(d -> new ForexModel(d.getDate(), d.getUsd()))
                .collect(Collectors.toList());
    
            forexService.insertForex(listData);
            
        } catch (Exception e) {
            // TODO: what to do when error occurs
            logger.error(e.getMessage(), e);
        }
    }
}