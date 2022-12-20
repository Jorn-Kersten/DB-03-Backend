package com.ajcompare.service;

import com.ajcompare.domain.User;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.util.Assert;

import javax.inject.Inject;

@QuarkusTest
public class UserServiceTest {
    @Inject
    UserService userService;

    @Test
    @TestSecurity(user = "admin", roles = "admin")
    public void userTest() {
        //arrange
        User expectedUser = new User(1, "admin", "admin@gmail.com");

        //act
        User user = userService.getUser("admin");

        //assert
        Assert.equals(expectedUser.getId(), user.getId());
    }

}
