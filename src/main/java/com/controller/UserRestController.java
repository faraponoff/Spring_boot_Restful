package com.controller;

import com.model.User;
import com.service.RoleService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value="/api")
public class UserRestController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @GetMapping("/admin")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/user")
    public ResponseEntity<User> getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(user);

    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {userService.getUserById(id);
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/admin")
    public ResponseEntity<List<User>> saveUser(User user, @RequestParam(name = "rolesNewUser", required = false) List<Integer> roles) {
        userService.addUser(user, roles);
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PatchMapping("/admin/updateUser/{id}")
    public ResponseEntity<?> updateUser(User user,
                                        @PathVariable int id,
                                        @RequestParam(name = "rolesEditUser", required = false) List<Integer> roles) {
        userService.updateUser(id, user, roles);
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<List<User>> delete(@PathVariable int id){
        userService.deleteUser(id);
        return ResponseEntity.ok(userService.getAllUsers());
    }

}

