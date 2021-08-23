package crud.service;

import crud.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    List<User> listUsers();
    void saveUser(User user);
    void updateUser(User user, long id);
    User getUserById(long id);
    User getUserByName(String name);
    void deleteUser(long id);
}
