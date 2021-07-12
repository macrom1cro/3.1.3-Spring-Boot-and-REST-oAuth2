package crud.dao;

import crud.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @PersistenceContext
   private EntityManager entityManager;

   @Override
   public void saveUser(User user) {
      entityManager.persist(user);
   }

   @Override
   public void deleteUser(User user) {
      entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
   }

   @Override
   public void updateUser(User user) {
      entityManager.merge(user);
   }

   @Override
   public List<User> listUsers() {
      List<User> listUsers = entityManager.createQuery("from User",User.class).getResultList();
      return listUsers;
   }

}
