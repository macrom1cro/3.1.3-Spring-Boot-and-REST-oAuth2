package crud.controller;

import crud.model.User;
import crud.service.RoleService;
import crud.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestAdminController {
    final RoleService roleService;
    final UserService userService;

    public RestAdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public ResponseEntity <List<User>> listUsers() {
        return new ResponseEntity<>(userService.listUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    @ResponseBody
    public User getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @PostMapping(value = "/users")
    public ResponseEntity<User> create(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(userService.getUserByName(user.getEmail()), HttpStatus.OK);
//        return new ResponseEntity<>(HttpStatus.CREATED);
    }
//userService.getUserByName(user.getEmail()),
    @PutMapping(value ="/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody User user, @PathVariable long id) {
        userService.updateUser(user, id);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        userService.deleteUser(id);
    }

}
