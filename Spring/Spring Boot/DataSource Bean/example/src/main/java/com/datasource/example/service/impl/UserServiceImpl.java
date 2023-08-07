package com.datasource.example.service.impl;

import com.datasource.example.repository.UserRepository;
import com.datasource.example.model.entity.User;
import com.datasource.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

}
