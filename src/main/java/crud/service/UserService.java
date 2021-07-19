package crud.service;

import crud.model.Role;
import crud.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface UserService {
    void saveUser(User user);
    void deleteUser(User user);
    void updateUser(User user);
    List<User> listUsers();
    User getUserById(long id);
    User getUserByName(String name);
    Set<Role> getRolesByName(String[] roles);
}
