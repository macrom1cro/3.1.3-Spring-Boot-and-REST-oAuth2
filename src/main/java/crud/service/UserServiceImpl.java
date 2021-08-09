package crud.service;

import crud.dao.UserDao;
import crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    final
    UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @Override
    public void saveUser(User user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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
//        if (!(user.getPassword().equals(userDao.getUserPassword(user)))){
//            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        }
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

    @Transactional
    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

}
