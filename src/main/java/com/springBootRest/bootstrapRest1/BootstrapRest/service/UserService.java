package com.springBootRest.bootstrapRest1.BootstrapRest.service;
import antlr.collections.List;
import com.springBootRest.bootstrapRest1.BootstrapRest.model.Role;
import com.springBootRest.bootstrapRest1.BootstrapRest.model.User;



public interface UserService  {
    List<User> getAllUsers();
    void save(User user);
    User show(long id);
    void update(long id, User updatedUser);
    void delete(long id);
    List<Role> getRoles();
}
