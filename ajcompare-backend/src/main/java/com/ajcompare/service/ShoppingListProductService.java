package com.ajcompare.service;

import com.ajcompare.domain.Product;
import com.ajcompare.domain.ShoppingListProduct;
import com.ajcompare.repository.ProductRepository;
import com.ajcompare.repository.ShoppingListProductRepository;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class ShoppingListProductService {

    @Inject
    ShoppingListProductRepository shoppingListProductRepository;

    public ShoppingListProductService() {
    }

    public List<ShoppingListProduct> allProducts() {
        return shoppingListProductRepository.listAll();
    }

    public ShoppingListProduct getProductById(Integer productId) {
        ShoppingListProduct shoppingListProduct = shoppingListProductRepository.find("id", productId).firstResult();
        if (shoppingListProduct == null)
        {
            throw new NotFoundException();
        }
        return shoppingListProduct;
    }

    @Transactional
    public Long deleteProduct(Integer productId) {
        return shoppingListProductRepository.delete("id", productId);
    }

    @Transactional
    public ShoppingListProduct updateProduct(ShoppingListProduct shoppingListProduct) {
        ShoppingListProduct shoppingListProductTemp = shoppingListProductRepository.find("id", shoppingListProduct.getId()).firstResult();
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

        shoppingListProductRepository.update("update Product p set p.name = :name, p.url = :url, p.date = :date, p.price = :price where p.id = :id",
                new Parameters().with("name", shoppingListProductTemp.getName()).and("url", shoppingListProductTemp.getUrl()).and("date", shoppingListProductTemp.getDate()).and("price", shoppingListProductTemp.getPrice()).and("id", shoppingListProductTemp.getId()));

        return shoppingListProduct = shoppingListProductRepository.find("id",shoppingListProduct.getId()).firstResult();
    }

    @Transactional
    public ShoppingListProduct addProduct(ShoppingListProduct shoppingListProduct) {
        if (shoppingListProduct == null)
        {
            throw new IllegalArgumentException();
        }
        shoppingListProductRepository.persist(shoppingListProduct);
        return shoppingListProduct;
    }
}
