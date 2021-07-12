package web.controller;

import hiber.config.AppConfig;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.service.WebServiceUsers;

import java.util.List;

@Controller
public class UsersController {
    @Autowired
    WebServiceUsers webServiceUsers;

    @GetMapping("/users")
    public String listUsers(Model model){
        webServiceUsers.addUserInList();
        model.addAttribute("listUsers",webServiceUsers.getUserList());
        return "users";
    }
}
