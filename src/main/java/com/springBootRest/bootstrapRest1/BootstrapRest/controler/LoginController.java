package com.springBootRest.bootstrapRest1.BootstrapRest.controler;
import antlr.collections.List;
import com.springBootRest.bootstrapRest1.BootstrapRest.model.Role;
import com.springBootRest.bootstrapRest1.BootstrapRest.model.User;
import com.springBootRest.bootstrapRest1.BootstrapRest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Principal principal, Model model) {
        User admin = userService.getUserByUserName(principal.getName());
        model.addAttribute("admin", admin);
        model.addAttribute("users", userService.getAllUsers());
        List<Role> allRoles = userService.getRoles();
        model.addAttribute("allRoles", allRoles);
        return "admin";
    }

}