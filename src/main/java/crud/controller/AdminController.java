package crud.controller;

import crud.model.User;
import crud.service.RoleService;
import crud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
    final RoleService roleServise;
    final UserService userService;

    public AdminController(UserService userService, RoleService roleServise) {
        this.userService = userService;
        this.roleServise = roleServise;
    }

    @GetMapping()
    public String listUsers(Model model) {
        model.addAttribute("listUsers", userService.listUsers());
        return "/admin/users";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "/admin/user";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult,
                         @RequestParam(required = false, name = "listRoles") String[] input_roles) {
        if (bindingResult.hasErrors()) {
            return "admin/new";
        }
        user.setRoles(roleServise.getRolesByName(input_roles));
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/new")
    public String newUsers(@ModelAttribute("user") User user) {
        return "admin/new";
    }

    @PostMapping("/new")
    public String Create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult,
                         @RequestParam(required = false, name = "listRoles") String[] input_roles) {
        if (bindingResult.hasErrors()) {
            return "admin/new";
        }
        user.setRoles(roleServise.getRolesByName(input_roles));
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/edit";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteUser(userService.getUserById(id));
        return "redirect:/admin";
    }
}
