package com.xpl.k314.services;

import com.xpl.k314.dao.UserDAO;
import com.xpl.k314.models.Role;
import com.xpl.k314.models.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    private final RoleService roleService;

    private final PasswordEncoder encoder;

    public UserServiceImpl(UserDAO userDAO, RoleService roleService, @Lazy PasswordEncoder encoder) {
        this.userDAO = userDAO;
        this.roleService = roleService;
        this.encoder = encoder;
    }

    @Override
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    @Override
    public List<User> getUserList() {
        return userDAO.getUserList();
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userDAO.saveUser(user);
    }

    @Override
    public void saveUserWithRoles(User user, List<Integer> roleIds) {
        user.setRoles(roleIds
                .stream()
                .map(roleService::getRoleById)
                .collect(Collectors.toSet()));
        saveUser(user);
    }

    @Override
    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = getUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + email);
        }
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(user.getAuthorities());
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getAuthorities());
    }
}
