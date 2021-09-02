package crud.controller;

import crud.model.User;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestAdminController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity <List<User>> listUsers() {
        return new ResponseEntity<>(userService.listUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }

    @PostMapping(value = "/users")
    public ResponseEntity<User> create(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PutMapping(value ="/users")
    public ResponseEntity<Void> update(@RequestBody User user) {
        userService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
