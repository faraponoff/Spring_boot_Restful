package repository.controler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.model.Role;
import repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import repository.service.RoleService;
import repository.service.UserDetailsServiceImpl;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static Logger logger = LoggerFactory.getLogger(AdminController.class);

    private UserDetailsServiceImpl userService;
    private RoleService roleService;
    @Autowired
    public AdminController(UserDetailsServiceImpl userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    public AdminController() {
    }

    @GetMapping()
    public String allUsers(Model model, Principal principal) {
        //юзер по логину

        User user = (User) userService.loadUserByUsername(principal.getName());
        model.addAttribute("userNav", user); //сюда приходят данные текущего юзера админа
        //передаем лист юзеров в форму
//        List<User> users = userService.getAllUsers();
        model.addAttribute("users", userService.getAllUsers());
        //передаем лист ролей в форму
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("roleS", roles);
//        //новый юзер

        model.addAttribute("newuser", new User());
        return "/user";
    }

    @PostMapping("/newUser")
    public String saveUser(@ModelAttribute("newuser") User user,
                           @RequestParam(required = false, name = "role_id") Integer[] role_id) {
        logger.warn("МЕТОД POST newUser получены id ролей из формы и данные user" + role_id.toString()+user.toString());
        user.setRoles((Set<Role>) roleService.getRoleById(role_id));
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("{id}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") Long id,
                         @RequestParam(required = false, name = "role_id") Integer[] role_id){
        user.setRoles((Set<Role>) roleService.getRoleById(role_id));
        userService.updateUser(user, id);
        return "redirect:/admin";
    }

    @DeleteMapping("{id}")
    public String delete(@ModelAttribute("user") User user,
                         @PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
