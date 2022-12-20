package com.ajcompare.resource;

import com.ajcompare.resources.ProductResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestHTTPEndpoint(ProductResource.class)
public class ProductResourceTest {
    @Test
    public void getProduct() {
        given()
                .when().get("/1")
                .then()
                .statusCode(200);
    }
}
