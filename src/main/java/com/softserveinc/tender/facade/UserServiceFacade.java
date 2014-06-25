package com.softserveinc.tender.facade;

import com.softserveinc.tender.dto.CustomerRegistrationDataDto;
import com.softserveinc.tender.dto.PrivateCustomerRegistrationDataDto;
import com.softserveinc.tender.dto.PrivateSellerRegistrationDataDto;
import com.softserveinc.tender.dto.RoleDto;
import com.softserveinc.tender.dto.SellerRegistrationDataDto;
import com.softserveinc.tender.dto.UsersProfileDataDto;
import com.softserveinc.tender.entity.User;

import java.util.List;

public interface UserServiceFacade {

    List<RoleDto> findUsersRoles();
    User saveSeller(SellerRegistrationDataDto sellerData);
    User saveCustomer(CustomerRegistrationDataDto customerData);
    User savePrivateSeller(PrivateSellerRegistrationDataDto sellerData);
    User savePrivateCustomer(PrivateCustomerRegistrationDataDto customerData);
    UsersProfileDataDto findUsersProfileInfo();
}
