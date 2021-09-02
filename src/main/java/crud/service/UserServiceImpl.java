package crud.service;

import crud.model.User;
import crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public User saveUser(User user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        System.out.println(userRepository.findPasswordById(user.getId()));
        if (user.getPassword()==""){
            user.setPassword(userRepository.findPasswordById(user.getId()));
        }
        if (!(user.getPassword().equals(userRepository.findPasswordById(user.getId())))){
//            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }

    @Transactional
    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    @Override
    public User getUserByName(String name) {
        return userRepository.findByEmail(name);
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

}
