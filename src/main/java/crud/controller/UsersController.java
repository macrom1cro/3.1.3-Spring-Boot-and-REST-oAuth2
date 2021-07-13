package crud.controller;

import crud.model.User;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UsersController {
    final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String listUsers(Model model){
        model.addAttribute("listUsers",userService.listUsers());
        return "user/users";
    }
    @GetMapping("/id")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "user/show";
    }
    @GetMapping("/new")
    public String newUsers(@ModelAttribute("user") User user){
        return "user/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/user";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("user", userService.getUserById(id));
        return "user/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user){
        userService.updateUser(user);
        return "redirect:/user";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        userService.deleteUser(userService.getUserById(id));
        return "redirect:/user";
    }
}
