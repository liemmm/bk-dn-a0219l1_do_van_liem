package com.lmao.areas.users.services;

import com.lmao.areas.users.entities.User;
import com.lmao.areas.users.models.bindingModels.UserBindingModel;
import com.lmao.areas.users.models.viewModels.UserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    boolean register(UserBindingModel userBindingModel);

    boolean edit(UserBindingModel userBindingModel);

    List<UserViewModel> getAllUsers();

    boolean isUsernameExistent(String username);

    User findOneByUsername(String username);

    void lockUserById(String id);

    void unlockUserById(String id);

    void addRoleAdmin(String id);

    void addRoleUser(String id);
}
