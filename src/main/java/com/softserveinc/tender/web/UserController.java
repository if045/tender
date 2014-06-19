package com.softserveinc.tender.web;

import com.softserveinc.tender.dto.UserRegistrationDataDto;
import com.softserveinc.tender.entity.User;
import com.softserveinc.tender.facade.RegistrationServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RegistrationServiceFacade registrationServiceFacade;

    @RequestMapping(value = "/registration", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody User saveUserRegistrationData(@RequestBody UserRegistrationDataDto userData) {
        return registrationServiceFacade.saveUser(userData);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String showUserProfile() {
        return "profile";
    }
}
