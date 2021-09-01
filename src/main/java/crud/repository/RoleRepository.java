package crud.repository;

import crud.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r WHERE r.role in (:roleStringSet)")
    Set<Role> getRolesByName(@Param("roleStringSet") String[] list);

}
