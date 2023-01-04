package com.ajcompare.service;

import com.ajcompare.domain.ShoppingListProduct;
import com.ajcompare.domain.User;
import com.ajcompare.repository.ShoppingListProductRepository;
import com.ajcompare.repository.UserRepository;
import io.quarkus.panache.common.Parameters;
import io.quarkus.security.UnauthorizedException;
import io.quarkus.security.identity.SecurityIdentity;
import org.h2.tools.Console;
import org.hibernate.result.Output;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.Date;
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

    private Integer getLastShoppingListId(String userName) {
        List<ShoppingListProduct> tempShoppingListProducts = shoppingListProductRepository.list("userName", userName);
        Integer lastIndex = 0;
        Integer shoppingListId = 0;
        if (Math.toIntExact(tempShoppingListProducts.stream().count()) != 0) {
            lastIndex = Math.toIntExact(tempShoppingListProducts.stream().count())-1;
            shoppingListId = tempShoppingListProducts.get(lastIndex).getShoppingListId();
        }
        else {
            shoppingListId = 1;
        }
        System.out.println(shoppingListId);
        return shoppingListId;
    }


    public List<ShoppingListProduct> allShoppingListProducts(String userName) {
        if (userName == null)
        {
            throw new IllegalArgumentException();
        }

        if (!securityIdentity.getPrincipal().getName().equals(userName)){
            throw new UnauthorizedException();
        }

        Integer shoppingListId = getLastShoppingListId(userName);

        return shoppingListProductRepository.list("from ShoppingListProduct where shoppingListId = ?1 and userName = ?2", shoppingListId, userName);
    }

    public List<ShoppingListProduct> getOldShoppingListProducts(String userName, Integer shoppingListId) {
        if (userName == null || shoppingListId == null)
        {
            throw new IllegalArgumentException();
        }
        ShoppingListProduct shoppingListProduct = shoppingListProductRepository.find("shoppingListId", shoppingListId).firstResult();

        if (!securityIdentity.getPrincipal().getName().equals(userName)){
            throw new UnauthorizedException();
        }

        return shoppingListProductRepository.list("from ShoppingListProduct where shoppingListId = ?1 and userName = ?2", shoppingListId, userName);
    }

    public ShoppingListProduct getShoppingListProductById(String userName, Integer productId) {
        if (productId == null)
        {
            throw new IllegalArgumentException();
        }
        ShoppingListProduct shoppingListProduct = shoppingListProductRepository.find("id", productId).firstResult();

        if (shoppingListProduct == null)
        {
            throw new NotFoundException();
        }

        if (!securityIdentity.getPrincipal().getName().equals(userName)){
            throw new UnauthorizedException();
        }
        return shoppingListProduct;
    }

    @Transactional
    public Long deleteShoppingListProduct(String userName, Integer productId) {
        if (productId == null)
        {
            throw new IllegalArgumentException();
        }
        if (!securityIdentity.getPrincipal().getName().equals(userName)){
            throw new UnauthorizedException();
        }
        return shoppingListProductRepository.delete("id", productId);
    }

    @Transactional
    public ShoppingListProduct updateShoppingListProduct(String userName, Integer productId, ShoppingListProduct shoppingListProduct) {
        if (!securityIdentity.getPrincipal().getName().equals(userName)){
            throw new UnauthorizedException();
        }

        shoppingListProductRepository.update("update ShoppingListProduct set name = :name, quantity= :quantity, content = :content,  url = :url, date = :date, price = :price where id = :id",
                new Parameters().with("name", shoppingListProduct.getName()).and("quantity", shoppingListProduct.getQuantity()).and("content", shoppingListProduct.getContent()).and("url", shoppingListProduct.getUrl()).and("date", shoppingListProduct.getDate()).and("price", shoppingListProduct.getPrice()).and("id", shoppingListProduct.getId()));

        return shoppingListProduct;
    }

    @Transactional
    public ShoppingListProduct addShoppingListProduct(String userName, ShoppingListProduct shoppingListProduct) {
        if (shoppingListProduct == null || userName == null)
        {
            throw new IllegalArgumentException();
        }

        if (!securityIdentity.getPrincipal().getName().equals(userName)){
            throw new UnauthorizedException();
        }

        Integer shoppingListId = getLastShoppingListId(userName);

        shoppingListProduct.setUserName(userName);
//        System.out.println(shoppingListId);
//        System.out.println(shoppingListProduct.getId() + " " + shoppingListProduct.getShoppingListId() + " " + shoppingListProduct.getQuantity() + " " + shoppingListProduct.getContent() + " " + shoppingListProduct.getUserName() + " " + shoppingListProduct.getName() + " " + shoppingListProduct.getSuperMarket() + " " + shoppingListProduct.getUrl() + " " + shoppingListProduct.getDate() + " " + shoppingListProduct.getPrice());
        shoppingListProduct.setShoppingListId(shoppingListId);

        shoppingListProductRepository.persist(shoppingListProduct);
        return shoppingListProduct;
    }
}
