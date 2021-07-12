package crud.controller;

import crud.model.User;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public String listUsers(ModelMap model){
        userService.saveUser(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.saveUser(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.saveUser(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.saveUser(new User("User4", "Lastname4", "user4@mail.ru"));
        userService.saveUser(new User("User5", "Lastname5", "user5@mail.ru"));
        model.addAttribute("listUsers",userService.listUsers());
        return "users";
    }
}
