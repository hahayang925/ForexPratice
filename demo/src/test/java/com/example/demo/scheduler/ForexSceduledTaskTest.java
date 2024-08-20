package com.example.demo.scheduler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.DemoApplication;
import com.example.demo.dto.ForexOpenApiDto;
import com.example.demo.model.ForexModel;
import com.example.demo.service.ApiService;
import com.example.demo.service.ForexService;

@SpringBootTest(classes = { DemoApplication.class })
public class ForexSceduledTaskTest {
  @Mock
  private ApiService apiService;

  @Mock
  private ForexService forexService;

  @InjectMocks
  private ForexScheduledTask forexSceduledTask;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testScheduledTask() throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    ForexOpenApiDto[] forexOpenApiDtoList = new ForexOpenApiDto[1];
    forexOpenApiDtoList[0] = new ForexOpenApiDto(sdf.parse("2024-08-18"), "32.51", "4.2");
    when(apiService.fetchDailyFreRateApi()).thenReturn(forexOpenApiDtoList);

    List<ForexModel> dataList = new ArrayList<>();
    ForexModel forexModel = new ForexModel();
    forexModel.setDate(forexOpenApiDtoList[0].getDate());
    forexModel.setUsd(forexOpenApiDtoList[0].getUsd());
    forexModel.setRmb(forexOpenApiDtoList[0].getRmb());
    dataList.add(forexModel);

    forexSceduledTask.getOpenApiForexData();

    verify(forexService).insertForex(dataList);
  }
}
