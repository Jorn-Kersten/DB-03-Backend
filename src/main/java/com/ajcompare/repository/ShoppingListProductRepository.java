package com.ajcompare.repository;

import com.ajcompare.domain.ShoppingListProduct;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ShoppingListProductRepository implements PanacheRepository<ShoppingListProduct> {
}
