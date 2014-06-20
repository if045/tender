package com.softserveinc.tender.web;

import com.softserveinc.tender.dto.CustomerRegistrationDataDto;
import com.softserveinc.tender.dto.PrivateCustomerRegistrationDataDto;
import com.softserveinc.tender.dto.PrivateSellerRegistrationDataDto;
import com.softserveinc.tender.dto.SellerRegistrationDataDto;
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

    @RequestMapping(value = "/registration/seller/legal", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody User saveUserRegistrationData(@RequestBody SellerRegistrationDataDto sellerData) {
        return registrationServiceFacade.saveSeller(sellerData);
    }

    @RequestMapping(value = "/registration/seller/private", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody User saveUserRegistrationData(@RequestBody PrivateSellerRegistrationDataDto sellerData) {
        return registrationServiceFacade.savePrivateSeller(sellerData);
    }

    @RequestMapping(value = "/registration/customer/legal", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody User saveUserRegistrationData(@RequestBody CustomerRegistrationDataDto customerData) {
        return registrationServiceFacade.saveCustomer(customerData);
    }

    @RequestMapping(value = "/registration/customer/private", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody User saveUserRegistrationData(@RequestBody PrivateCustomerRegistrationDataDto customerData) {
        return registrationServiceFacade.savePrivateCustomer(customerData);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String showUserProfile() {
        return "profile";
    }
}
