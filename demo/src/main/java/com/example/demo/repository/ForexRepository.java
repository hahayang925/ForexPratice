package com.example.demo.repository;

import java.util.List;
import java.time.LocalDate;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.model.ForexModel;

public interface ForexRepository extends MongoRepository<ForexModel, String> {
  @Query("{ 'date': { '$gte': ?0, '$lte': ?1 } }")
  List<ForexModel> findByDateRangeAndCurrency(LocalDate startDate, LocalDate endDate, String currency);

  List<ForexModel> findByDateBetween(LocalDate startDate, LocalDate endDate);
  
}