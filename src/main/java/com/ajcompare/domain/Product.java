package com.ajcompare.domain;

import java.util.UUID;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String supermarket;
    private String url;
    private Date date;
    private Double price;

    public Product() {

    }

    public Product(Integer id, String name, String supermarket, String url, Date date, Double price) {
        this.id = id;
        this.name = name;
        this.supermarket = supermarket;
        this.url = url;
        this.date = date;
        this.price = price;
    }

    public Integer getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getSupermarket() { return supermarket; }

    public void setSupermarket(String supermarket) { this.supermarket = supermarket; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }
}
