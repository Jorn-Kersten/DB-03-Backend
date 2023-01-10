package com.ajcompare.resource;

import com.ajcompare.domain.Product;
import com.ajcompare.resources.ProductResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import java.util.Date;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestHTTPEndpoint(ProductResource.class)
public class ProductResourceTest {
    @Test
    public void testGetSingleProduct() {
        given()
                .when().get("/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetAllProducts() {
        given()
                .when().get("")
                .then()
                .statusCode(200);
    }

    @Test
    public void testDeleteProduct() {
        given()
                .when().delete("/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void testUpdateProduct() {
        final Product product = new Product(1,"test","test","www.test.com",new Date(), 2.5, 0.5);

        given()
                .body(product)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .put("/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void testAddProduct() {
        final Product product = new Product();

        product.setName("test");
        product.setSupermarket("Albert Heijn");
        product.setUrl("www.test.com");
        product.setPrice(2.5);
        product.setDate(new Date());

        given()
                .body(product)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .post("")
                .then()
                .statusCode(201);
    }
}
