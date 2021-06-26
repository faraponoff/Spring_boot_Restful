package repository.controler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import repository.model.User;
import repository.service.UserDetailsServiceImpl;


@Controller
    @RequestMapping("/user/{id}")
    public class UserController {

        private UserDetailsServiceImpl userService;
        @Autowired
        public UserController(UserDetailsServiceImpl userService) {
            this.userService = userService;
        }

        @GetMapping()
        public String showUser(Model model) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("/user", userService.show(user.getId()));
            return "/allUsers";
        }
    }


