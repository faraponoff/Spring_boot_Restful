package com.springBoot.bootstrap1.Bootstrap.service;

import com.springBoot.bootstrap1.Bootstrap.model.Role;
import com.springBoot.bootstrap1.Bootstrap.model.User;

import java.util.List;

public interface UserService  {
    List<User> getAllUsers();
    void save(User user);
    User show(long id);
    void update(long id, User updatedUser);
    void delete(long id);
    List<Role> getRoles();
}
