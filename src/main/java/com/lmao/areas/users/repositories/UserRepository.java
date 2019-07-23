package com.lmao.areas.users.repositories;

import com.lmao.areas.users.entities.User;
import com.lmao.areas.users.models.viewModels.UserViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, String> {

    @Modifying
    @Query("UPDATE User AS u SET u.isAccountNonLocked = false WHERE u.id = :id")
    void lockUserById(@Param("id")String id);

    @Modifying
    @Query("UPDATE User AS u SET u.isAccountNonLocked = true WHERE u.id = :id")
    void unlockUserById(@Param("id")String id);

    User findOneByUsername(String username);

}
