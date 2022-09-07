package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.JoinRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public User findById(String id) {
        return userRepository.findById(id);
    }

    public void addUser(JoinRequest request) {
        User user = new User();

        user.setId(request.getId());
        user.setPw(request.getPw());
        user.setRole("ROLE_USER");

        userRepository.add(user);
    }
}
