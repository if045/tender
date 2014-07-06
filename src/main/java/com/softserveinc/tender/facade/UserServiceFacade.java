package com.softserveinc.tender.facade;

import com.softserveinc.tender.dto.CompanyDto;
import com.softserveinc.tender.dto.CustomerRegistrationDataDto;
import com.softserveinc.tender.dto.LoggedUserDto;
import com.softserveinc.tender.dto.PrivateCustomerRegistrationDataDto;
import com.softserveinc.tender.dto.PrivateSellerRegistrationDataDto;
import com.softserveinc.tender.dto.RoleDto;
import com.softserveinc.tender.dto.SellerRegistrationDataDto;
import com.softserveinc.tender.dto.UserPersonalDataDto;
import com.softserveinc.tender.dto.UsersProfileDataDto;
import com.softserveinc.tender.entity.Company;
import com.softserveinc.tender.entity.User;

import java.util.List;

public interface UserServiceFacade {

    List<RoleDto> findUsersRoles();
    User saveSeller(SellerRegistrationDataDto sellerData);
    User saveCustomer(CustomerRegistrationDataDto customerData);
    User savePrivateSeller(PrivateSellerRegistrationDataDto sellerData);
    User savePrivateCustomer(PrivateCustomerRegistrationDataDto customerData);
    UsersProfileDataDto findUsersProfileInfo();
    UserPersonalDataDto updateUserData(UserPersonalDataDto userPersonalData);
    String getHomePage();
    LoggedUserDto getLoggedUserInfo();
    CompanyDto updateUserCompanyData(CompanyDto companyDto);
}
