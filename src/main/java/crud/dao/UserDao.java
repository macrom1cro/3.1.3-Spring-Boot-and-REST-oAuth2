package crud.dao;

import crud.model.User;

import java.util.List;

public interface UserDao {
   void saveUser(User user);
   void deleteUser(User user);
   void updateUser(User user);
   List<User> listUsers();
}
