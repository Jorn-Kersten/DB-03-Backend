package com.ajcompare.service;

import com.ajcompare.domain.User;
import com.ajcompare.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    public UserService() {
    }

    public User getUser(Integer id) {
        return userRepository.find("id", id).firstResult();
    }

    @Transactional
    public User createUser(User user) {
        if (user == null)
        {
            throw new IllegalArgumentException();
        }
        userRepository.persist(user);
        return user;
    }
}
