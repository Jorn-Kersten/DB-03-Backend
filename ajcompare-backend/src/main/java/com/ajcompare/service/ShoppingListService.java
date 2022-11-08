package com.ajcompare.service;

import com.ajcompare.domain.Product;
import com.ajcompare.domain.ShoppingList;
import com.ajcompare.repository.ProductRepository;
import com.ajcompare.repository.ShoppingListRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ShoppingListService {

    @Inject
    ShoppingListRepository shoppingListRepository;

    public ShoppingListService() {
    }

    public List<ShoppingList> allShoppingLists() {
        return shoppingListRepository.listAll();
    }

    public ShoppingList getShoppingListById(long shoppingListId) {
        return shoppingListRepository.findById(shoppingListId);
    }

    @Transactional
    public ShoppingList addShoppingList(ShoppingList shoppingList) {
        if (shoppingList == null)
        {
            throw new IllegalArgumentException();
        }
        shoppingListRepository.persist(shoppingList);
        return shoppingList;
    }
}
