package com.controller;

import com.model.User;
import com.service.RoleService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value="/api")
public class UserRestController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @GetMapping("/admin")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>(userService.getUserByEmail(auth.getName()), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {userService.getUserById(id);
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping("/admin")
    public ResponseEntity<User> saveUser(User user, @RequestParam(name = "rolesNewUser", required = false) List<Integer> roles) {
        if (roles.size() > 0) {
            roles.forEach(roleIndex -> user.getRoles().add(roleService.getRoleById(roleIndex)));
        }
        userService.addUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/admin/updateUser/{id}")
    public ResponseEntity<?> updateUser(User user,
                                        @PathVariable int id,
                                        @RequestParam(name = "rolesEditUser", required = false) List<Integer> roles) {
        try {
            if (roles.size() > 0) {
                roles.forEach(roleIndex -> user.getRoles().add(roleService.getRoleById(roleIndex)));
            }
            userService.updateUser(id, user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

