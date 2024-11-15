package com.example.domain.service.impl;

import com.example.domain.model.User;
import com.example.domain.repository.UserRepository;
import com.example.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User userToCreate) {

        if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
            throw new IllegalArgumentException("This account number already exists");
        }

        return userRepository.save(userToCreate);
    }
}
