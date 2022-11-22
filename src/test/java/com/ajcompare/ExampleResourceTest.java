package com.ajcompare;

import com.ajcompare.resources.ProductResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestHTTPEndpoint(ProductResource.class)
public class ExampleResourceTest {

    @Test
    public void testets() {
        given()
                .when().get("/1")
                .then()
                .statusCode(200);
    }

}