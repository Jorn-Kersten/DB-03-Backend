package com.ajcompare.service;

import com.ajcompare.domain.User;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.util.Assert;

import javax.inject.Inject;
import javax.transaction.Transactional;

@QuarkusTest
public class UserServiceTest {
    @Inject
    UserService userService;

    @Test
    @TestSecurity(user = "admin", roles = "user")
    public void testGetUser() {
        //arrange
        User expectedUser = new User(1, "admin", "admin@gmail.com");

        //act
        User user = userService.getUser("admin");

        //assert
        Assert.equals(expectedUser.getId(), user.getId());
    }

    @Test
    @Transactional
    void testRegisterNewUser() {
        // Arrange
        User user = new User();

        user.setEmail("newUser");
        user.setName("test@gmail.com");

        // Act
        User registeredUser = userService.createUser(user);

        // Assert
        Assertions.assertEquals(registeredUser.getName(), user.getName());
    }
}
