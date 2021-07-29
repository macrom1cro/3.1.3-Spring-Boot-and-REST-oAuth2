package crud.dao;

import crud.model.Role;

import java.util.Set;

public interface RoleDao {
    Set<Role> getRolesByName(String[] roles);
}
