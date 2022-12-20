package com.ajcompare.service;

import com.ajcompare.domain.User;
import com.ajcompare.repository.UserRepository;
import io.quarkus.security.UnauthorizedException;
import io.quarkus.security.identity.SecurityIdentity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    @Inject
    SecurityIdentity securityIdentity;

    public UserService() {
    }

    public User getUser(String userName) {
        if (userName == null){
            throw new IllegalArgumentException();
        }

        if (!securityIdentity.getPrincipal().getName().equals(userName)){
            throw new UnauthorizedException();
        }
        return userRepository.find("name", userName).firstResult();
    }

    public User createUser(User user) {
        if (user.getName() == null || user.getEmail() == null)
        {
            throw new IllegalArgumentException();
        }
        userRepository.persist(user);
        return user;
    }
}
