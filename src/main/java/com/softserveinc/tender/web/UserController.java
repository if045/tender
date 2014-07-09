package com.softserveinc.tender.web;

import com.softserveinc.tender.dto.*;
import com.softserveinc.tender.entity.User;
import com.softserveinc.tender.facade.UserServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceFacade userServiceFacade;

    @RequestMapping(value = "/registration/seller/legal", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody User saveUserRegistrationData(@RequestBody SellerRegistrationDataDto sellerData) {
        return userServiceFacade.saveSeller(sellerData);
    }

    @RequestMapping(value = "/registration/seller/private", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody User saveUserRegistrationData(@RequestBody PrivateSellerRegistrationDataDto sellerData) {
        return userServiceFacade.savePrivateSeller(sellerData);
    }

    @RequestMapping(value = "/registration/customer/legal", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody User saveUserRegistrationData(@RequestBody CustomerRegistrationDataDto customerData) {
        return userServiceFacade.saveCustomer(customerData);
    }

    @RequestMapping(value = "/registration/customer/private", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody User saveUserRegistrationData(@RequestBody PrivateCustomerRegistrationDataDto customerData) {
        return userServiceFacade.savePrivateCustomer(customerData);
    }

    @RequestMapping(value = "/registration/user", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody User saveUserRegistrationData(@RequestBody UserDto userData) {
        return userServiceFacade.saveUser(userData);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String showUserProfile() {
        return "profile";
    }

    @RequestMapping(value = "/profile/data", method = RequestMethod.GET)
    public @ResponseBody UsersProfileDataDto showUserProfileData() {
        return userServiceFacade.findUsersProfileInfo();
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @RequestMapping(value = "/profile/userdata", method = RequestMethod.GET)
    public @ResponseBody UsersProfileDataDto showUserProfileData(@RequestParam(value = "userLogin",required = false) String userLogin) {
        return userServiceFacade.findUsersProfileInfoByLogin(userLogin);
    }

    @RequestMapping(value = "/loggedUserInfo", method = RequestMethod.GET)
    public @ResponseBody LoggedUserDto getLoggedUserInfo() {
        return userServiceFacade.getLoggedUserInfo();
    }
}
