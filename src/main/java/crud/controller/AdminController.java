package crud.controller;

import crud.model.User;
import crud.service.RoleServise;
import crud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    final RoleServise roleServise;
    final UserService userService;

    public AdminController(UserService userService, RoleServise roleServise) {
        this.userService = userService;
        this.roleServise = roleServise;
    }

    @GetMapping()
    public String listUsers(Model model) {
        model.addAttribute("listUsers", userService.listUsers());
        return "/admin/users";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "/admin/user";
    }

    @GetMapping("/new")
    public String newUsers(@ModelAttribute("user") User user) {
        return "admin/new";
    }

    @PostMapping()
    public String openCreate() {
        return "redirect:/admin/new";
    }

    @PostMapping("/new")
    public String Create(@ModelAttribute("user") User user,
                         @RequestParam(required = false, name = "listRoles") String[] input_roles) {
        user.setRoles(roleServise.getRolesByName(input_roles));
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam(required = false, name = "listRoles") String[] input_roles) {
        user.setRoles(roleServise.getRolesByName(input_roles));
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/{id}/edit")
    public String openEdit(@PathVariable("id") int id) {
        return String.format("redirect:/admin/%d/edit", id);
    }

    @PostMapping("/{id}")
    public String openProfile(@PathVariable("id") int id) {
        return String.format("redirect:/admin/%d", id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(userService.getUserById(id));
        return "redirect:/admin";
    }
}
