package com.softserveinc.tender.facade;

import com.softserveinc.tender.dto.RoleDto;

import java.util.List;

public interface RegistrationServiceFacade {

    List<RoleDto> findUsersRoles();
}
