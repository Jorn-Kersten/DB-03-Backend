package com.ajcompare.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@Entity
public class Product {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String name;
    private String url;
    private String date;
    private Double price;

    public Product() {

    }

    public Product(String name, String url, String date, Double price){
        this.name = name;
        this.url = url;
        this.date = date;
        this.price = price;
    }

    public UUID getId() { return id; }

    public String getName() { return name; }

    public void setName() { this.name = name; }

    public String getUrl() { return url; }

    public void setUrl() { this.url = url; }

    public String getDate() { return date; }

    public void setDate() { this.date = date; }

    public Double getPrice() { return price; }

    public void setPrice() { this.price = price; }
}
