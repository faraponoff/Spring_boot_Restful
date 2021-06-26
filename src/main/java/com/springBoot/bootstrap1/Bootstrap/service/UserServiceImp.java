package com.springBoot.bootstrap1.Bootstrap.service;

import com.springBoot.bootstrap1.Bootstrap.Repo.RoleRepository;
import com.springBoot.bootstrap1.Bootstrap.Repo.UserRepository;
import com.springBoot.bootstrap1.Bootstrap.model.Role;
import com.springBoot.bootstrap1.Bootstrap.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }


    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User show(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void update(long id, User updatedUser) {
        String oldPassword = userRepository.findById(updatedUser.getId()).orElse(null).getPassword();
        if (!oldPassword.equals(updatedUser.getPassword())) {
            updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
        userRepository.save(updatedUser);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<Role> getRoles() {
        return (List<Role>) roleRepository.findAll();
    }
}
