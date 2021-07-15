package crud.controller;

import crud.model.User;
import crud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UsersController {
    final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String listUsers(Model model) {
        model.addAttribute("listUsers", userService.listUsers());
        return "user/users";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user/user";
    }

    @GetMapping("/new")
    public String newUsers(@ModelAttribute("user") User user) {
        return "user/new";
    }
    @PostMapping()
    public String openCreate() {
        return "redirect:/user/new";
    }

    @PostMapping("/new")
    public String Create(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/user";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "user/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/user";
    }

    @PostMapping("/{id}/edit")
    public String openEdit(@PathVariable("id") int id) {
        return String.format("redirect:/user/%d/edit", id);
    }

    @PostMapping("/{id}")
    public String openProfile(@PathVariable("id") int id) {
        return String.format("redirect:/user/%d", id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(userService.getUserById(id));
        return "redirect:/user";
    }
}
