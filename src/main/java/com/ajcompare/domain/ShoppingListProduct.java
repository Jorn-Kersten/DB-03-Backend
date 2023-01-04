package com.ajcompare.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ShoppingListProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer shoppingListId;
    private Integer quantity;
    private Double content;
    private String userName;
    private String name;
    private String superMarket;
    private String url;
    private Date date;
    private Double price;

    public ShoppingListProduct() {

    }

    public ShoppingListProduct(Integer id, Integer shoppingListId, Integer quantity, Double content, String userName, String name, String superMarket, String url, Date date, Double price) {
        this.id = id;
        this.shoppingListId = shoppingListId;
        this.quantity = quantity;
        this.content = content;
        this.userName = userName;
        this.name = name;
        this.superMarket = superMarket;
        this.url = url;
        this.date = date;
        this.price = price;
    }

    public Integer getId() { return id; }

    public Integer getShoppingListId() { return shoppingListId; }

    public void setShoppingListId(Integer shoppingListId) { this.shoppingListId = shoppingListId; }

    public Integer getQuantity() { return quantity; }

    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getContent() { return content; }

    public void setContent(Double content) { this.content = content; }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

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