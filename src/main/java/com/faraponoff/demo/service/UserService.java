package com.faraponoff.demo.service;

import com.faraponoff.demo.model.User;

import java.util.List;

public interface UserService {


    List<User> getAllUsers();

    User getUserById(Long id);

    void deleteUser(Long id);

    User saveUser(User user, String[] roleNames);

    User updateUser(User user, String[] roleNames);

    User getUserByUsername(String username);


}
