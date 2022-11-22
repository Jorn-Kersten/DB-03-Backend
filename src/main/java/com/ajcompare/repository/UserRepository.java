package com.ajcompare.repository;

import com.ajcompare.domain.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository  implements PanacheRepository<User> {
}
