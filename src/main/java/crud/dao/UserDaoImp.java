package crud.dao;

import crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @PersistenceContext
   private EntityManager entityManager;

   @Autowired
   BCryptPasswordEncoder bCryptPasswordEncoder;

   @Override
   public void saveUser(User user) {
      user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
      entityManager.persist(user);
   }

   @Override
   public void deleteUser(User user) {
      entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
   }

   @Override
   public void updateUser(User user) {
      user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
      entityManager.merge(user);
   }

   @Override
   public List<User> listUsers() {
      List<User> listUsers = entityManager.createQuery("from User",User.class).getResultList();
      return listUsers;
   }
   @Override
   public User getUserById(long id) {
      return entityManager.find(User.class, id);
   }

   @Override
   public User getUserByName(String name) {
      User user = entityManager.
              createQuery("SELECT u from User u WHERE u.name = :username", User.class).
              setParameter("username", name).getSingleResult();
      return user;
   }
}
