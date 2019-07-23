package com.lmao.areas.roles.repositories;

import com.lmao.areas.roles.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findOneByAuthority(String authority);
}
