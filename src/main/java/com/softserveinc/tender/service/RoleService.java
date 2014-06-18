package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Role;
import java.util.List;

public interface RoleService {

    List<Role> findAll();
    Role findRoleById(Integer id);
    void saveRole(Role role);
    List<Role> findUsersRoles();
}
