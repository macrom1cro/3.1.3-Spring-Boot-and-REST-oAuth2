package crud.dao;

import crud.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Set;

@Repository
public class RoleSetDaoImpl implements RoleSetDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<Role> getRolesByName(String[] roles) {

        Set<Role> roleResult = new HashSet<>();

        for (String role : roles) {
            TypedQuery<Role> query = entityManager.createQuery("select role from Role role where role.role = :role", Role.class);
            query.setParameter("role", role);
            Role result = query.getSingleResult();
            roleResult.add(result);
        }
        return roleResult;
    }
}
