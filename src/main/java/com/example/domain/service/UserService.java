package com.example.domain.service;

import com.example.domain.model.User;

public interface UserService {

    User findById(Long id);

    User create(User userToCreate);
}
