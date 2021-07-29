package crud.dao;

import crud.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<Role> getRolesByName(String[] roles) {

//        Set<Role> roleResult = new HashSet<>();
//
////        TypedQuery<Role> query = entityManager.createQuery("select role from Role role where role.role IN (:role0, :role1)", Role.class);
//        @Query(value = "select field1 from table1 where field2 = :param1 and field3 in (:param2)", nativeQuery = true)
//        List<Object> getField1List(@Param("param1") Date reportDate, @Param("param2") List<Long> idList);
//        return roleResult = (Set<Role>) query.getResultList();
//        return new HashSet<Role>(entityManager.createQuery("SELECT r FROM Role r WHERE r.role in (:roleStringSet)")
//                .setParameter("roleStringSet", roles)
//                .getResultList());
//        return new HashSet<Role>(entityManager.createQuery("SELECT r FROM Role r WHERE r.role in (:role0)")
//                .setParameters("roleStringSet", roles)
//                .getResultList());
    }
}
//    @Query(value = "select field1 from table1 where field2 = :param1 and field3 in (:param2)", nativeQuery = true)
//    Optional<Long> getField1List(@Param("param1") Date reportDate, @Param("param2") Long[] idList);
//
//    @Query(value = "select field1 from table1 where field2 = :param1 and field3 in :param2", nativeQuery = true)
//    List<Object> getField1List(@Param("param1") Date reportDate, @Param("param2") List<Long> idList);
