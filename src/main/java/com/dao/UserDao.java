package com.dao;

import com.model.User;

import java.util.List;
public interface UserDao {
    void addUser(User user, List<Integer> roles);
    void updateUser(int id, User user, List<Integer> roles);
    void deleteUser(int id);
    User getUserById(int id);
    List<User> getAllUsers();
    User getUserByEmail(String email);
}
