package crud.service;

import crud.model.Role;
import crud.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    @Override
    public Set<Role> getRolesByName(String[] roles) {
        return roleRepository.getRolesByName(roles);
    }
}
