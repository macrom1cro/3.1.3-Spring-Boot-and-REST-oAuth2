package crud.dao;

import crud.model.User;

import java.util.List;

public interface UserDao {
    void saveUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

    List<User> listUsers();

    User getUserById(long id);

    User getUserByName(String name);

    String getUserPassword(User user);
}
