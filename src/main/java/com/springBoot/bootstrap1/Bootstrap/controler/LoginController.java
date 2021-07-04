package com.springBoot.bootstrap1.Bootstrap.controler;
import com.springBoot.bootstrap1.Bootstrap.model.Role;
import com.springBoot.bootstrap1.Bootstrap.model.User;
import com.springBoot.bootstrap1.Bootstrap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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