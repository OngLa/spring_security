package com.example.demo.repository;

import com.example.demo.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public User findById(String id) {
        return em.find(User.class, id);
    }

    public void add(User user) {
        em.persist(user);
    }
}
