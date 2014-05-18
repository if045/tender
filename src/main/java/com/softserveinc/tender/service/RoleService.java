package com.softserveinc.tender.service;


import com.softserveinc.tender.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    Role findRoleById(int id);
    void saveRole(Role role);
}
