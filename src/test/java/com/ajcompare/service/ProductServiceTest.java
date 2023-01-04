package com.ajcompare.service;

import com.ajcompare.domain.Product;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.util.Assert;

import javax.inject.Inject;
import java.util.Date;

@QuarkusTest
public class ProductServiceTest {
    @Inject
    ProductService productService;

//    @Test
//    public void GetProductById_EqualsExpectedProduct_True() {
//        //arrange
//        Product expectedProduct = new Product(1, "Coca-Cola", "Albert Heijn", "https://www.ah.nl/producten/product/wi2800/coca-cola-regular", new Date(2022-10-12), 2.49, 0.5);
//
//        //act
//        Product product = productService.getProductById(1);
//
//        //assert
//        Assert.equals(expectedProduct.getId(), product.getId());
//    }
//
//    @Test
//    public void UpdateProduct_EqualsExpectedProduct_() {
//        //arrange
//        Product expectedProduct = new Product(1, "Coca-Cola", "Albert Heijn", "https://www.ah.nl/producten/product/wi2800/coca-cola-regular", new Date(2022-10-12), 2.49, 0.5);
//
//        //act
//        Product product = productService.getProductById(1);
//
//        //assert
//        Assert.equals(expectedProduct.getId(), product.getId());
//    }
}
