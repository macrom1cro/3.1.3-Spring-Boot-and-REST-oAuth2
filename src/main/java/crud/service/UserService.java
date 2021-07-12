package crud.service;

import crud.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);
    void deleteUser(User user);
    void updateUser(User user);
    List<User> listUsers();
}
