package crud.service;

import crud.model.Role;

import java.util.Set;

public interface RoleServise {
    Set<Role> getRolesByName(String[] roles);
}
