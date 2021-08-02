package crud.service;

import crud.model.Role;

import java.util.Set;

public interface RoleService {
    Set<Role> getRolesByName(String[] roles);
}
