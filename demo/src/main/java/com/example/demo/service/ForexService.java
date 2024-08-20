package com.example.demo.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ForexModel;
import com.example.demo.repository.ForexRepository;

@Service
public class ForexService {

    @Autowired
    ForexRepository forexRepository;

    public void insertForex(List<ForexModel> forexData) {
        forexRepository.insertAll(forexData);
    }

    public List<ForexModel> findByDateRangeAndCurrency(String startDate, String endDate, String currency) throws ParseException {
        return forexRepository.findForexByDateRangeAndCurrency(startDate, endDate, currency);
    }
}
