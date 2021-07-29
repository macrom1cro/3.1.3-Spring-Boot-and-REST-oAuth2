package crud.service;

import crud.dao.RoleDao;
import crud.model.Role;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleServise {

    final
    RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Set<Role> getRolesByName(String[] roles) {
        return roleDao.getRolesByName(roles);
    }
}
