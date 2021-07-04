package com.springBoot.bootstrap1.Bootstrap.service;
import com.springBoot.bootstrap1.Bootstrap.repository.RoleRepository;
import com.springBoot.bootstrap1.Bootstrap.repository.UserRepository;
import com.springBoot.bootstrap1.Bootstrap.model.Role;
import com.springBoot.bootstrap1.Bootstrap.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class UserServiceImp implements UserService {
    private final    UserRepository userRepository;
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
        if (user == null)  {
            throw  new NullPointerException("User is no detected");
        }
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
        if (updatedUser == null) {
            throw  new NullPointerException("updatedUser is no detected");
        }
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

    @Override
    public User getUserByUserName(String name) {
        return userRepository.findUserByUsername(name);
    }
}
