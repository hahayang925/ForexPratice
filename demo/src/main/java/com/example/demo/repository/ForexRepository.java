package com.example.demo.repository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ForexModel;

import org.springframework.data.mongodb.core.query.Criteria;

@Repository
public class ForexRepository {
  @Autowired
  private MongoTemplate mongoTemplate;

  public List<ForexModel> findForexByDateRangeAndCurrency(String startDate, String endDate, String currency)
      throws ParseException {
    Query query = new Query();
    TimeZone tz = TimeZone.getTimeZone("UTC");
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    df.setTimeZone(tz);
    query.addCriteria(Criteria.where("date").gte(df.parse(startDate)).lte(df.parse(endDate)));
    query.fields().include(currency);
    query.fields().include("date"); // 如果需要排除 _id 字段

    // 执行查询
    return mongoTemplate.find(query, ForexModel.class);
  }

  public void insertAll(List<ForexModel> forexModels) {
    mongoTemplate.insertAll(forexModels);
  }
}
