package crud.dao;

import crud.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
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
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      List<User> listUsers = entityManager.createQuery("from users",User.class).getResultList();
      return listUsers;
   }

}
