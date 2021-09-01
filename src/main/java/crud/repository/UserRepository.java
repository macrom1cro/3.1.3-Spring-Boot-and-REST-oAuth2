package crud.repository;

import crud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.email = :email")
    User findByName(@Param("email") String name);

    @Query("select u.password from User u where u.id = :id")
    String getPasswordById(@Param("id") long id);
}
