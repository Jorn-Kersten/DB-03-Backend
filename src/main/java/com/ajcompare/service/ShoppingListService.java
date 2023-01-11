package com.ajcompare.service;

import com.ajcompare.domain.Product;
import com.ajcompare.domain.ShoppingList;
import com.ajcompare.domain.ShoppingListProduct;
import com.ajcompare.repository.ProductRepository;
import com.ajcompare.repository.ShoppingListRepository;
import io.quarkus.security.UnauthorizedException;
import io.quarkus.security.identity.SecurityIdentity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class ShoppingListService {

    @Inject
    ShoppingListRepository shoppingListRepository;

    @Inject
    SecurityIdentity securityIdentity;

    public ShoppingListService() {
    }

    public List<ShoppingList> allShoppingLists() {
        return shoppingListRepository.listAll();
    }

    public ShoppingList getShoppingListById(String userName, Integer shoppingListId) {
        if (!securityIdentity.getPrincipal().getName().equals(userName)){
            throw new UnauthorizedException();
        }
        if (shoppingListId == null)
        {
            throw new IllegalArgumentException();
        }
        ShoppingList shoppingList = shoppingListRepository.find("id", shoppingListId).firstResult();

        if (shoppingList == null)
        {
            throw new NotFoundException();
        }
        return shoppingList;
    }

    @Transactional
    public ShoppingList addShoppingList(String userName, ShoppingList shoppingList) {
        if (!securityIdentity.getPrincipal().getName().equals(userName)){
            throw new UnauthorizedException();
        }
        if (shoppingList == null)
        {
            throw new IllegalArgumentException();
        }
        shoppingListRepository.persist(shoppingList);
        return shoppingList;
    }
}
