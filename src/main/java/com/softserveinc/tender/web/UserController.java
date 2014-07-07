package com.softserveinc.tender.web;

import com.softserveinc.tender.dto.CompanyDto;
import com.softserveinc.tender.dto.CustomerRegistrationDataDto;
import com.softserveinc.tender.dto.LoggedUserDto;
import com.softserveinc.tender.dto.PrivateCustomerRegistrationDataDto;
import com.softserveinc.tender.dto.PrivateSellerRegistrationDataDto;
import com.softserveinc.tender.dto.SellerRegistrationDataDto;
import com.softserveinc.tender.dto.TradeSphereDto;
import com.softserveinc.tender.dto.UserPersonalDataDto;
import com.softserveinc.tender.dto.UsersProfileDataDto;
import com.softserveinc.tender.entity.User;
import com.softserveinc.tender.facade.UserServiceFacade;
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

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String showUserProfile() {
        return "profile";
    }

    @RequestMapping(value = "/profile/data", method = RequestMethod.GET)
    public @ResponseBody UsersProfileDataDto showUserProfileData() {
        return userServiceFacade.findUsersProfileInfo();
    }

    @RequestMapping(value = "/update/data", method = RequestMethod.PUT, consumes = "application/json")
    public @ResponseBody UserPersonalDataDto updateUserProfileData(@RequestBody UserPersonalDataDto userPersonalData) {
        userServiceFacade.findUsersProfileInfo();

        return userServiceFacade.updateUserData(userPersonalData);
    }

    @RequestMapping(value = "/loggedUserInfo", method = RequestMethod.GET)
    public @ResponseBody LoggedUserDto getLoggedUserInfo() {
        return userServiceFacade.getLoggedUserInfo();
    }

    @RequestMapping(value = "/update/companyData", method = RequestMethod.PUT, consumes = "application/json")
    public @ResponseBody CompanyDto updateUserCompanyData(@RequestBody CompanyDto companyDto) {
        return userServiceFacade.updateUserCompanyData(companyDto);
    }

    @RequestMapping(value = "/update/tradeSphere", method = RequestMethod.PUT, consumes = "application/json")
    public @ResponseBody TradeSphereDto updateUserTradeSphereData(@RequestBody TradeSphereDto tradeSphereDto) {
        return userServiceFacade.updateTradeSphereData(tradeSphereDto);
    }
}
