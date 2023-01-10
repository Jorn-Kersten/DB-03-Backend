package com.ajcompare.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.Date;

@Entity
public class ShoppingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String supermarket;
    private Date date;
    private Double totalPrice;

    public ShoppingList() {

    }

    public ShoppingList(String name, String supermarket, Date date, Double totalPrice) {
        this.name = name;
        this.supermarket = supermarket;
        this.date = date;
        this.totalPrice = totalPrice;
    }

    public Integer getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getSuperMarket() { return supermarket; }

    public void setSuperMarket(String supermarket) { this.supermarket = supermarket; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public Double getTotalPrice() { return totalPrice; }

    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }
}
