package crud.service;

import crud.dao.UserDao;
import crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

   private final UserDao userDao;

   public UserServiceImpl(UserDao userDao) {
      this.userDao = userDao;
   }

   @Transactional
   @Override
   public void saveUser(User user) {
      userDao.saveUser(user);
   }

   @Transactional
   @Override
   public void deleteUser(User user) {
      userDao.deleteUser(user);
   }

   @Transactional
   @Override
   public void updateUser(User user) {
      userDao.updateUser(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Transactional
   @Override
   public User getUserById(long id) {
      return userDao.getUserById(id);
   }

}
