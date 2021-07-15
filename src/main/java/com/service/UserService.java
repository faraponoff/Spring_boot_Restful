package com.service;

import com.model.User;
import java.util.List;


public interface UserService {
    void addUser(User user, List<Integer> roles);
    void updateUser(int id, User newUser, List<Integer> roles);
    void deleteUser(int id);
    User getUserById(int id);
    User getUserByEmail(String email);
    List<User> getAllUsers();
}
