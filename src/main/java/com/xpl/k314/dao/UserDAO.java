package com.xpl.k314.dao;

import com.xpl.k314.models.User;

import java.util.List;

public interface UserDAO {
    User getUserById(int id);
    User getUserByEmail(String email);
    List<User> getUserList();
    void saveUser(User user);
    void deleteUser(int id);
}
