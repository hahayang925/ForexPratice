package com.example.demo.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import com.mongodb.lang.NonNull;

import lombok.Data;

@Data
@Document(collection = "forex")
public class ForexModel {


    @Id
    private String id;

    @NonNull
    @Field("date")
    @DateTimeFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    @Indexed(unique = true)
    private Date date;

    @NonNull
    @Field("USD/NTD")
    private String usd;

    @NonNull
    @Field("RMB/NTD")
    private String rmb;

    public ForexModel() {
    }

    public ForexModel(Date date, String usd, String rmb) {
        this.usd = usd;
        this.rmb = rmb;
        this.date = date;
    }
}
