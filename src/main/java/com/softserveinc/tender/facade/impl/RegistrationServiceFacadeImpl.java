package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.RoleDto;
import com.softserveinc.tender.facade.RegistrationServiceFacade;
import com.softserveinc.tender.service.RoleService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Service("registrationServiceFacade")
@Transactional
public class RegistrationServiceFacadeImpl implements RegistrationServiceFacade{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleService roleService;

    @Override
    public List<RoleDto> findUsersRoles() {
        Type targetListType = new TypeToken<List<RoleDto>>(){}.getType();
        return modelMapper.map(roleService.findUsersRoles(), targetListType);
    }
}
