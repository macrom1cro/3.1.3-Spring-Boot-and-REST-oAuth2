package crud.service;

import crud.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    void saveUser(User user);
    void deleteUser(User user);
    void updateUser(User user);
    List<User> listUsers();
    User getUserById(long id);
    User getUserByName(String name);

}
