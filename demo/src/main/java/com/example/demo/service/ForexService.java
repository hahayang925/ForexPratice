package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ForexModel;
import com.example.demo.repository.ForexRepository;

@Service
public class ForexService {

    @Autowired
    private ForexRepository repository;

    public List<ForexModel> insertForex(List<ForexModel> forexData) {
        return repository.saveAll(forexData);
    }

    public ForexModel findForexDataById(String id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteDocument(String id) {
        repository.deleteById(id);
    }

    public List<ForexModel> findByDateRange(LocalDate startDate, LocalDate endDate, String currency) {
        return repository.findByDateRangeAndCurrency(startDate, endDate, currency);
    }
}
