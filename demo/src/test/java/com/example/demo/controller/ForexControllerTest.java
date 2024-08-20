package com.example.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.when;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.model.ForexModel;
import com.example.demo.service.ForexService;

@WebMvcTest(ForexController.class)
public class ForexControllerTest {

    @InjectMocks
    private ForexController forexController;

    @MockBean
    private ForexService forexService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(forexController).build();
    }

    @Test
    @DisplayName("對美元匯率查詢正確")
    public void testGetForexRate() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ForexModel mockForexModel = new ForexModel(sdf.parse("2024-08-18"), "31.35", "4.2");
        List<ForexModel> respList= new ArrayList<>();
        respList.add(mockForexModel);
        when(forexService.findByDateRangeAndCurrency("2024/07/01", "2024/08/01", "usd")).thenReturn(respList);

        Map<String, Object> requestBodyMap = new HashMap<>();
        requestBodyMap.put("startDate", "2024/07/01");
        requestBodyMap.put("endDate", "2024/08/01");
        requestBodyMap.put("currency", "usd");

        String requestBody = objectMapper.writeValueAsString(requestBodyMap);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/getForexRate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error.code").value("0000"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error.message").value("成功"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency.size()", CoreMatchers.is(respList.size())));
    }

    @Test
    @DisplayName("對美元匯率查詢參數錯誤-起始日期小於一年前")
    public void testGetForexRateStartDateBeforeOneYear() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ForexModel mockForexModel = new ForexModel(sdf.parse("2024-08-18"), "31.35", "4.2");
        List<ForexModel> respList= new ArrayList<>();
        respList.add(mockForexModel);
        when(forexService.findByDateRangeAndCurrency("2024/07/01", "2024/08/01", "usd")).thenReturn(respList);

        Map<String, Object> requestBodyMap = new HashMap<>();
        requestBodyMap.put("startDate", "2023/07/01");
        requestBodyMap.put("endDate", "2024/08/01");
        requestBodyMap.put("currency", "usd");

        String requestBody = objectMapper.writeValueAsString(requestBodyMap);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/getForexRate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error.code").value("E001"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error.message").value("日期區間不符"));
    }
}
