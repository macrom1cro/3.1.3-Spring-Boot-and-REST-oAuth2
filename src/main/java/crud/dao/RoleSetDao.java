package crud.dao;

import crud.model.Role;

import java.util.Set;

public interface RoleSetDao {
    Set<Role> getRolesByName(String[] roles);
}
