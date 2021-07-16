package com.service;

import com.dao.UserDao;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private RoleService roleService;
    private UserDao userDao;

    @Autowired
    public UserServiceImpl(RoleService roleService, UserDao userDao) {
        this.roleService = roleService;
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void addUser(User user, List<Integer> roles) {
        if (roles.size() > 0) {

            roles.forEach(roleIndex -> user.getRoles().add(roleService.getRoleById(roleIndex)));
        }
        userDao.addUser(user, roles);
    }

    @Override
    @Transactional
    public void updateUser(int id, User newUser, List<Integer> roles) {
        if (roles.size() > 0) {
            roles.forEach(roleIndex -> newUser.getRoles().add(roleService.getRoleById(roleIndex)));
        }
        userDao.updateUser(id, newUser, roles);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return this.userDao.getUserById(id);
    }

    @Override
    @Transactional
    public User getUserByEmail(String email) {
        return this.userDao.getUserByEmail(email);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDao.getUserByEmail(email);
    }
}
