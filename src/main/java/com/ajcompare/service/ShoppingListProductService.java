package com.ajcompare.service;

import com.ajcompare.domain.ShoppingListProduct;
import com.ajcompare.domain.User;
import com.ajcompare.repository.ShoppingListProductRepository;
import com.ajcompare.repository.UserRepository;
import io.quarkus.panache.common.Parameters;
import io.quarkus.security.UnauthorizedException;
import io.quarkus.security.identity.SecurityIdentity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class ShoppingListProductService {

    @Inject
    ShoppingListProductRepository shoppingListProductRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    SecurityIdentity securityIdentity;

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
        if (userId == null)
        {
            throw new IllegalArgumentException();
        }
        User user = userRepository.find("id", userId).firstResult();

        if (!securityIdentity.getPrincipal().getName().equals(user.getName())){
            throw new UnauthorizedException();
        }

        Integer shoppingListId = getLastShoppingListId(userId);

        return shoppingListProductRepository.list("from ShoppingListProduct where shoppingListId = ?1 and userId = ?2", shoppingListId, userId);
    }

    public List<ShoppingListProduct> getOldShoppingListProducts(Integer userId, Integer shoppingListId) {
        if (userId == null || shoppingListId == null)
        {
            throw new IllegalArgumentException();
        }
        ShoppingListProduct shoppingListProduct = shoppingListProductRepository.find("shoppingListId", shoppingListId).firstResult();
        User user = userRepository.find("id", shoppingListProduct.getUserId()).firstResult();

        if (!securityIdentity.getPrincipal().getName().equals(user.getName())){
            throw new UnauthorizedException();
        }

        return shoppingListProductRepository.list("from ShoppingListProduct where shoppingListId = ?1 and userId = ?2", shoppingListId, userId);
    }

    public ShoppingListProduct getShoppingListProductById(Integer productId) {
        if (productId == null)
        {
            throw new IllegalArgumentException();
        }
        ShoppingListProduct shoppingListProduct = shoppingListProductRepository.find("id", productId).firstResult();
        User user = userRepository.find("id", shoppingListProduct.getUserId()).firstResult();

        if (!securityIdentity.getPrincipal().getName().equals(user.getName())){
            throw new UnauthorizedException();
        }

        if (shoppingListProduct == null)
        {
            throw new NotFoundException();
        }
        return shoppingListProduct;
    }

    @Transactional
    public Long deleteShoppingListProduct(Integer productId) {
        if (productId == null)
        {
            throw new IllegalArgumentException();
        }
        ShoppingListProduct shoppingListProduct = shoppingListProductRepository.find("id", productId).firstResult();
        User user = userRepository.find("id", shoppingListProduct.getUserId()).firstResult();

        if (!securityIdentity.getPrincipal().getName().equals(user.getName())){
            throw new UnauthorizedException();
        }
        return shoppingListProductRepository.delete("id", productId);
    }

    @Transactional
    public ShoppingListProduct updateShoppingListProduct(ShoppingListProduct shoppingListProduct) {
        User user = userRepository.find("id", shoppingListProduct.getUserId()).firstResult();

        if (!securityIdentity.getPrincipal().getName().equals(user.getName())){
            throw new UnauthorizedException();
        }

        ShoppingListProduct shoppingListProductTemp = shoppingListProductRepository.find("id", shoppingListProduct.getId()).firstResult();
        if (shoppingListProduct.getSuperMarket() != null){
            shoppingListProductTemp.setSuperMarket(shoppingListProduct.getSuperMarket());
        }
        if (shoppingListProduct.getContent() != null){
            shoppingListProductTemp.setContent(shoppingListProduct.getContent());
        }
        if (shoppingListProduct.getQuantity() != null){
            shoppingListProductTemp.setQuantity(shoppingListProduct.getQuantity());
        }
        if (shoppingListProduct.getName() != null){
            shoppingListProductTemp.setName(shoppingListProduct.getName());
        }
        if (shoppingListProduct.getUrl() != null){
            shoppingListProductTemp.setUrl(shoppingListProduct.getUrl());
        }
        if (shoppingListProduct.getDate() != null){
            shoppingListProductTemp.setDate(shoppingListProduct.getDate());
        }
        if (shoppingListProduct.getPrice() != null){
            shoppingListProductTemp.setPrice(shoppingListProduct.getPrice());
        }
        if (shoppingListProduct.getSuperMarket() != null){
            shoppingListProductTemp.setSuperMarket(shoppingListProduct.getSuperMarket());
        }

        shoppingListProductRepository.update("update ShoppingListProduct set name = :name, quantity= :quantity, content = :content,  url = :url, date = :date, price = :price where id = :id",
                new Parameters().with("name", shoppingListProductTemp.getName()).and("quantity", shoppingListProductTemp.getQuantity()).and("content", shoppingListProductTemp.getContent()).and("url", shoppingListProductTemp.getUrl()).and("date", shoppingListProductTemp.getDate()).and("price", shoppingListProductTemp.getPrice()).and("id", shoppingListProductTemp.getId()));

        return shoppingListProduct = shoppingListProductRepository.find("id",shoppingListProduct.getId()).firstResult();
    }

    @Transactional
    public ShoppingListProduct addShoppingListProduct(Integer userId, ShoppingListProduct shoppingListProduct) {
        if (shoppingListProduct == null || userId == null)
        {
            throw new IllegalArgumentException();
        }

        User user = userRepository.find("id", userId).firstResult();

        if (!securityIdentity.getPrincipal().getName().equals(user.getName())){
            throw new UnauthorizedException();
        }

        Integer shoppingListId = getLastShoppingListId(userId);

        shoppingListProduct.setUserId(userId);
        shoppingListProduct.setShoppingListId(shoppingListId);

        shoppingListProductRepository.persist(shoppingListProduct);
        return shoppingListProduct;
    }
}
