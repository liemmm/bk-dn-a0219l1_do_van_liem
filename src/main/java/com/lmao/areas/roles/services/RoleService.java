package com.lmao.areas.roles.services;


import com.lmao.areas.roles.entities.Role;

public interface RoleService {

    Role getDefaultRole();

    Role findOneByAuthority(String authority);
}
