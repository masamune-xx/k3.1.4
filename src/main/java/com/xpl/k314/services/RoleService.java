package com.xpl.k314.services;

import com.xpl.k314.models.Role;

import java.util.List;

public interface RoleService {
    Role getRoleById(int id);
    Role getRoleByName(String name);
    List<Role> getRoleList();
    void saveRole(Role role);
}
