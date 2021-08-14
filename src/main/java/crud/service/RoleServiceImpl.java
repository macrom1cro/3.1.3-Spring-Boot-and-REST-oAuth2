package crud.service;

import crud.dao.RoleDao;
import crud.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.TreeSet;

@Service
public class RoleServiceImpl implements RoleService {

    final
    RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    @Override
    public Set<Role> getRolesByName(String[] roles) {
        return roleDao.getRolesByName(roles);
    }
}
