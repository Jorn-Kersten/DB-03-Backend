package com.ajcompare.service;

import com.ajcompare.domain.Product;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.util.Assert;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Date;

@QuarkusTest
public class ProductServiceTest {
    @Inject
    ProductService productService;

    @Test
    public void testUpdateProduct() {
        //arrange
        Product expectedProduct = new Product(1, "test", "Albert Heijn", "https://www.ah.nl/producten/product/wi2800/coca-cola-regular", new Date(2022-10-12), 2.49, 0.5);

        //act
        Product product = productService.updateProduct(expectedProduct);

        //assert
        Assert.equals(expectedProduct, product);
    }

    @Test
    @Transactional
    public void testCreateProduct() {
        //arrange
        Product newProduct = new Product();

        newProduct.setName("test");
        newProduct.setSupermarket("Albert Heijn");
        newProduct.setUrl("https://www.ah.nl/producten/product/wi2800/coca-cola-regular");
        newProduct.setDate(new Date(2022-10-12));
        newProduct.setPrice(2.49);
        newProduct.setContent(0.5);

        //act
        Product product = productService.addProduct(newProduct);

        //assert
        Assert.equals(newProduct, product);
    }
}
