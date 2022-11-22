package com.ajcompare.service;

import com.ajcompare.domain.Product;
import com.ajcompare.domain.ShoppingListProduct;
import com.ajcompare.repository.ProductRepository;
import com.ajcompare.repository.ShoppingListProductRepository;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ShoppingListProductService {

    @Inject
    ShoppingListProductRepository shoppingListProductRepository;

    public ShoppingListProductService() {
    }

    private Integer getLastShoppingListId(Integer userId) {
        List<ShoppingListProduct> tempShoppingListProducts = shoppingListProductRepository.list("userId", userId);
        Integer lastIndex = 0;
        Integer shoppingListId = 0;
        if (Math.toIntExact(tempShoppingListProducts.stream().count()) != 0) {
            lastIndex = Math.toIntExact(tempShoppingListProducts.stream().count())-1;
            shoppingListId = tempShoppingListProducts.get(lastIndex).getShoppingListId();
        }
        else {
            shoppingListId = 1;
        }

        return shoppingListId;
    }


    public List<ShoppingListProduct> allShoppingListProducts(Integer userId) {
        Integer shoppingListId = getLastShoppingListId(userId);

        return shoppingListProductRepository.list("from ShoppingListProduct where shoppingListId = ?1 and userId = ?2", shoppingListId, userId);
    }

    public List<ShoppingListProduct> getOldShoppingListProducts(Integer userId, Integer shoppingListId) {
        return shoppingListProductRepository.list("from ShoppingListProduct where shoppingListId = ?1 and userId = ?2", shoppingListId, userId);
    }

    public ShoppingListProduct getShoppingListProductById(Integer productId) {
        ShoppingListProduct shoppingListProduct = shoppingListProductRepository.find("id", productId).firstResult();
        if (shoppingListProduct == null)
        {
            throw new NotFoundException();
        }
        return shoppingListProduct;
    }

    @Transactional
    public Long deleteShoppingListProduct(Integer productId) {
        return shoppingListProductRepository.delete("id", productId);
    }

    @Transactional
    public ShoppingListProduct updateShoppingListProduct(ShoppingListProduct shoppingListProduct) {

        ShoppingListProduct shoppingListProductTemp = shoppingListProductRepository.find("id", shoppingListProduct.getId()).firstResult();
        if (shoppingListProduct.getSuperMarket() != ""){
            shoppingListProductTemp.setSuperMarket(shoppingListProduct.getSuperMarket());
        }
        if (shoppingListProduct.getContent() != null){
            shoppingListProductTemp.setContent(shoppingListProduct.getContent());
        }
        if (shoppingListProduct.getQuantity() != null){
            shoppingListProductTemp.setQuantity(shoppingListProduct.getQuantity());
        }
        if (shoppingListProduct.getName() != ""){
            shoppingListProductTemp.setName(shoppingListProduct.getName());
        }
        if (shoppingListProduct.getUrl() != ""){
            shoppingListProductTemp.setUrl(shoppingListProduct.getUrl());
        }
        if (shoppingListProduct.getDate() != null){
            shoppingListProductTemp.setDate(shoppingListProduct.getDate());
        }
        if (shoppingListProduct.getPrice() != null   ){
            shoppingListProductTemp.setPrice(shoppingListProduct.getPrice());
        }
        if (shoppingListProduct.getSuperMarket() != null   ){
            shoppingListProductTemp.setSuperMarket(shoppingListProduct.getSuperMarket());
        }
//        shoppingListProductRepository.update("update ShoppingListProduct set name = :name, quantity= :quantity, " +
//                        "content = :content,  url = :url, date = :date, " +
//                        "price = :price where id = :id",)
//
//                new Parameters().with("name", shoppingListProductTemp.getName()).and("quantity", shoppingListProductTemp.getQuantity()).and(
//                "content", shoppingListProductTemp.getContent()).and("url", shoppingListProductTemp.getUrl()).and(
//                        "date", shoppingListProductTemp.getDate()).and("price", shoppingListProductTemp.getPrice()).and(
//                                "id", shoppingListProductTemp.getId());


        shoppingListProductRepository.update("update ShoppingListProduct set name = :name, quantity= :quantity, content = :content,  url = :url, date = :date, price = :price where id = :id",
                new Parameters().with("name", shoppingListProductTemp.getName()).and("quantity", shoppingListProductTemp.getQuantity()).and("content", shoppingListProductTemp.getContent()).and("url", shoppingListProductTemp.getUrl()).and("date", shoppingListProductTemp.getDate()).and("price", shoppingListProductTemp.getPrice()).and("id", shoppingListProductTemp.getId()));

        return shoppingListProduct = shoppingListProductRepository.find("id",shoppingListProduct.getId()).firstResult();
    }

    @Transactional
    public ShoppingListProduct addShoppingListProduct(Integer userId, ShoppingListProduct shoppingListProduct) {
        if (shoppingListProduct == null)
        {
            throw new IllegalArgumentException();
        }

        Integer shoppingListId = getLastShoppingListId(userId);

        shoppingListProduct.setUserId(userId);
        shoppingListProduct.setShoppingListId(shoppingListId);

        shoppingListProductRepository.persist(shoppingListProduct);
        return shoppingListProduct;
    }
}
