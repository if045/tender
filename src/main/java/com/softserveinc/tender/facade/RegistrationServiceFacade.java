package com.softserveinc.tender.facade;

import com.softserveinc.tender.dto.RoleDto;
import com.softserveinc.tender.dto.UserRegistrationDataDto;
import com.softserveinc.tender.entity.User;

import java.util.List;

public interface RegistrationServiceFacade {

    List<RoleDto> findUsersRoles();
    public User saveUser(UserRegistrationDataDto userData);
}
