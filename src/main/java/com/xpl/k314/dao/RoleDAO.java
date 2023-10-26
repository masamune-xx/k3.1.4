package com.xpl.k314.dao;

import com.xpl.k314.models.Role;

import java.util.List;

public interface RoleDAO {
    Role getRoleById(int id);
    Role getRoleByName(String name);
    List<Role> getRoleList();
    void saveRole(Role role);
}
