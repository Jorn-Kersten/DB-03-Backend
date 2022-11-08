package com.ajcompare.service;

import com.ajcompare.domain.Product;
import com.ajcompare.repository.ProductRepository;
import io.quarkus.logging.Log;
import io.quarkus.panache.common.Parameters;
import org.hibernate.sql.Delete;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.NotFoundException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    public ProductService() {
    }

    public List<Product> allProducts() {
        return productRepository.listAll();
    }

    public Product getProductById(Integer productId) {
        Product product = productRepository.find("id", productId).firstResult();
        if (product == null)
        {
            throw new NotFoundException();
        }
        return product;
    }

    @Transactional
    public Long deleteProduct(Integer productId) {
        return productRepository.delete("id", productId);
    }

    @Transactional
    public Product updateProduct(Product product) {
        Product productTemp = productRepository.find("id",product.getId()).firstResult();
        if (product.getName() != ""){
            productTemp.setName(product.getName());
        }
        if (product.getUrl() != ""){
            productTemp.setUrl(product.getUrl());
        }
        if (product.getDate() != null){
            productTemp.setDate(product.getDate());
        }
        if (product.getPrice() != null   ){
            productTemp.setPrice(product.getPrice());
        }

        productRepository.update("update Product p set p.name = :name, p.url = :url, p.date = :date, p.price = :price where p.id = :id",
                new Parameters().with("name", productTemp.getName()).and("url", productTemp.getUrl()).and("date", productTemp.getDate()).and("price", productTemp.getPrice()).and("id", productTemp.getId()));

        return product = productRepository.find("id",product.getId()).firstResult();
    }

    @Transactional
    public Product addProduct(Product product) {
        if (product == null)
        {
            throw new IllegalArgumentException();
        }
        productRepository.persist(product);
        return product;
    }
}
