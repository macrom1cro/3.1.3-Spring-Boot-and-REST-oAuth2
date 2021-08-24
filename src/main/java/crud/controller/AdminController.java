package crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/")
public class AdminController {

    @GetMapping("/admin")
    public String listUsers() {
        return "/admin/users";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "/login";
    }

    @GetMapping("/user")
    public String showUserPage() {
        return "/user/user";
    }

}
