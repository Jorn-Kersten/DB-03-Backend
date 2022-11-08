package com.ajcompare.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ShoppingListProduct {
    @Id
    @GeneratedValue()
    private Integer id;
    private Integer shoppingListId;
    private String name;
    private String superMarket;
    private String url;
    private Date date;
    private Double price;

    public ShoppingListProduct() {

    }

    public ShoppingListProduct(Integer id, Integer shoppingListId, String name, String superMarket, String url, Date date, Double price) {
        this.id = id;
        this.shoppingListId = shoppingListId;
        this.name = name;
        this.superMarket = superMarket;
        this.url = url;
        this.date = date;
        this.price = price;
    }

    public Integer getId() { return id; }

    public Integer getShoppingListId() { return shoppingListId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getSuperMarket() { return superMarket; }

    public void setSuperMarket(String superMarket) { this.superMarket = superMarket; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }
}