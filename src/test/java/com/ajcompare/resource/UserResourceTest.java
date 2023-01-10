package com.ajcompare.resource;

import com.ajcompare.domain.User;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestHTTPEndpoint(com.ajcompare.resources.UserResource.class)
public class UserResourceTest {
    @Test
    @TestSecurity(user = "admin", roles = "user")
    public void testGetUserByName() {
        given()
                .when().get("/admin")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "jorn", roles = "user")
    public void testRegisterUser() {
        final User user = new User();

        user.setName("jorn");
        user.setEmail("jorn@gmail.com");

        given()
                .body(user)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .post("/register")
                .then()
                .statusCode(201);
    }

    @Test
    @TestSecurity(user = "jorn", roles = "user")
    public void testGetUserByIncorrectName() {
        given()
                .when().get("/admin")
                .then()
                .statusCode(401);
    }
}
