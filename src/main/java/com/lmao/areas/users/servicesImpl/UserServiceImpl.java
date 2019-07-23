package com.lmao.areas.users.servicesImpl;


import com.lmao.areas.images.services.ImageService;
import com.lmao.areas.roles.entities.Role;
import com.lmao.areas.roles.services.RoleService;
import com.lmao.areas.users.entities.User;
import com.lmao.areas.users.models.bindingModels.UserBindingModel;
import com.lmao.areas.users.models.viewModels.UserViewModel;
import com.lmao.areas.users.repositories.UserRepository;
import com.lmao.areas.users.services.UserService;
import com.lmao.errors.Errors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final String USER_ROLE = "ROLE_USER";
    private static final String ADMIN_ROLE = "ROLE_ADMIN";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean register(UserBindingModel userBindingModel) {
        if (this.isUsernameExistent(userBindingModel.getUsername())) {
            return false;
        }

        if (!userBindingModel.getPassword().equals(userBindingModel.getConfirmPassword())) {
            return false;
        }

        User user = this.modelMapper.map(userBindingModel, User.class);
        String encryptedPassword = this.bCryptPasswordEncoder.encode(userBindingModel.getPassword());
        user.setPassword(encryptedPassword);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.addRole(this.roleService.getDefaultRole());
        this.userRepository.save(user);

        return true;
    }

    @Override
    public boolean edit(UserBindingModel userBindingModel) {
        if (this.isUsernameExistent(userBindingModel.getUsername())) {
            return false;
        }

        if (!userBindingModel.getPassword().equals(userBindingModel.getConfirmPassword())) {
            return false;
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = null;
        user = this.userRepository.findOneByUsername(username);

        user.setPassword(this.bCryptPasswordEncoder.encode(userBindingModel.getPassword()));
        user.setUsername(userBindingModel.getUsername());
        user.setEmail(userBindingModel.getEmail());

        this.userRepository.save(user);
        return true;
    }

    @Override
    public List<UserViewModel> getAllUsers() {
        List<UserViewModel> userViewModels = this.userRepository
                .findAll()
                .stream()
                .map(x -> this.modelMapper.map(x, UserViewModel.class))
                .collect(Collectors.toList());

        return userViewModels;
    }

    @Override
    public boolean isUsernameExistent(String username) {
        User user = this.userRepository.findOneByUsername(username);

        return user != null;
    }

    @Override
    public User findOneByUsername(String username) {
        return this.userRepository.findOneByUsername(username);
    }

    @Override
    public void lockUserById(String id) {
        this.userRepository.lockUserById(id);
    }

    @Override
    public void unlockUserById(String id) {
        this.userRepository.unlockUserById(id);
    }

    @Override
    public void addRoleAdmin(String id) {
        User user = this.userRepository.findById(id).orElse(null);
        Role userRole = this.roleService.findOneByAuthority(USER_ROLE);
        Role adminRole = this.roleService.findOneByAuthority(ADMIN_ROLE);
        user.getAuthorities().remove(userRole);
        user.addRole(adminRole);
        this.userRepository.save(user);
    }

    @Override
    public void addRoleUser(String id) {
        User user = this.userRepository.findById(id).orElse(null);
        Role userRole = this.roleService.findOneByAuthority(USER_ROLE);
        Role adminRole = this.roleService.findOneByAuthority(ADMIN_ROLE);
        user.getAuthorities().remove(adminRole);
        user.addRole(userRole);
        this.userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(Errors.INVALID_CREDENTIALS);
        }

        return user;
    }
}
