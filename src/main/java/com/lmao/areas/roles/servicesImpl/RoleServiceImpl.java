package com.lmao.areas.roles.servicesImpl;

import com.lmao.areas.roles.entities.Role;
import com.lmao.areas.roles.repositories.RoleRepository;
import com.lmao.areas.roles.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private static final String USER_ROLE = "ROLE_USER";
    private static final String ADMIN_ROLE = "ROLE_ADMIN";

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getDefaultRole() {
        Role defaultRole = this.roleRepository.findOneByAuthority(USER_ROLE);
        if (defaultRole == null) {
            defaultRole = new Role(USER_ROLE);
            this.roleRepository.save(defaultRole);
            Role adminRole = new Role(ADMIN_ROLE);
            this.roleRepository.save(adminRole);
        }

        return defaultRole;
    }

    @Override
    public Role findOneByAuthority(String authority) {
        return this.roleRepository.findOneByAuthority(authority);
    }
}
