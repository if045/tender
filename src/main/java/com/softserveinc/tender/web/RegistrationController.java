package com.softserveinc.tender.web;

import com.softserveinc.tender.dto.RoleDto;
import com.softserveinc.tender.facade.RegistrationServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationServiceFacade registrationServiceFacade;

    @RequestMapping(value = "/usersRoles", method = RequestMethod.GET)
    public @ResponseBody List<RoleDto> findUsersRoles() {
        return registrationServiceFacade.findUsersRoles();
    }
}
