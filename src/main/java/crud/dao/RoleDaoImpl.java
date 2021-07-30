package crud.dao;

import crud.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<Role> getRolesByName(String[] roles) {

        List<String> list = Arrays.asList(roles);
        return new HashSet<Role>(entityManager.createQuery("SELECT r FROM Role r WHERE r.role in (:roleStringSet)")
                .setParameter("roleStringSet", list)
                .getResultList());
    }
}

