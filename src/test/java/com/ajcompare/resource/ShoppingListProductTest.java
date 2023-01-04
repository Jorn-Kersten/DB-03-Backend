package com.ajcompare.resource;

import com.ajcompare.domain.ShoppingListProduct;
import com.ajcompare.resources.ShoppingListProductResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

import java.util.Date;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestHTTPEndpoint(ShoppingListProductResource.class)
public class ShoppingListProductTest {
    @Test
    @TestSecurity(user = "admin", roles = "user")
    public void testGetSingleShoppingListProduct() {
        given()
                .when().get("/admin/1")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "admin", roles = "user")
    public void testGetAllShoppingListProductsByUserName() {
        given()
                .when().get("/admin")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "admin", roles = "user")
    public void testGetOldShoppingListProductsByUserNameAndId() {
        given()
                .when().get("/admin/1")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "admin", roles = "user")
    public void testDeleteShoppingListProductById() {
        given()
                .when().delete("/admin/1")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "admin", roles = "user")
    public void testUpdateShoppingListProductById() {
        final ShoppingListProduct shoppingListProduct = new ShoppingListProduct(1, 1, 1, 0.0, "test","test","test","test", new Date(), 0.0);

        given()
                .body(shoppingListProduct)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .put("/admin/1")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "admin", roles = "user")
    public void testAddProductToShoppingList() {
        final ShoppingListProduct shoppingListProduct = new ShoppingListProduct();

        shoppingListProduct.setShoppingListId(1);
        shoppingListProduct.setQuantity(1);
        shoppingListProduct.setContent(0.0);
        shoppingListProduct.setUserName("test");
        shoppingListProduct.setName("test");
        shoppingListProduct.setSuperMarket("test");
        shoppingListProduct.setUrl("test");
        shoppingListProduct.setDate(new Date());
        shoppingListProduct.setPrice(0.0);

        given()
                .body(shoppingListProduct)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .post("/admin")
                .then()
                .statusCode(201);
    }
}
