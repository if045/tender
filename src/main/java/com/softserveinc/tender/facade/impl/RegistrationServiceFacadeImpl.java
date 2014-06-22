package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.AddressDto;
import com.softserveinc.tender.dto.CompanyDto;
import com.softserveinc.tender.dto.CustomerRegistrationDataDto;
import com.softserveinc.tender.dto.PrivateCustomerRegistrationDataDto;
import com.softserveinc.tender.dto.PrivateSellerRegistrationDataDto;
import com.softserveinc.tender.dto.ProfileDto;
import com.softserveinc.tender.dto.RoleDto;
import com.softserveinc.tender.dto.SellerRegistrationDataDto;
import com.softserveinc.tender.dto.TradeSphereDto;
import com.softserveinc.tender.dto.UserDto;
import com.softserveinc.tender.entity.Address;
import com.softserveinc.tender.entity.Category;
import com.softserveinc.tender.entity.Company;
import com.softserveinc.tender.entity.Location;
import com.softserveinc.tender.entity.Profile;
import com.softserveinc.tender.entity.Role;
import com.softserveinc.tender.entity.User;
import com.softserveinc.tender.facade.RegistrationServiceFacade;
import com.softserveinc.tender.service.AddressService;
import com.softserveinc.tender.service.CategoryService;
import com.softserveinc.tender.service.CompanyService;
import com.softserveinc.tender.service.LocationService;
import com.softserveinc.tender.service.ProfileService;
import com.softserveinc.tender.service.RoleService;
import com.softserveinc.tender.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.soap.SOAPBinding;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.softserveinc.tender.util.Util.formatDate;
import static com.softserveinc.tender.util.Constants.CHECKED_USER;
import static com.softserveinc.tender.util.Constants.ENABLED_USER;

@Service("registrationServiceFacade")
@Transactional
public class RegistrationServiceFacadeImpl implements RegistrationServiceFacade{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public List<RoleDto> findUsersRoles() {
        Type targetListType = new TypeToken<List<RoleDto>>(){}.getType();
        return modelMapper.map(roleService.findUsersRoles(), targetListType);
    }

    @Override
    public User saveSeller(SellerRegistrationDataDto sellerData) {
        return mapSellerRegistrationData(sellerData);
    }

    @Override
    public User saveCustomer(CustomerRegistrationDataDto customerData) {
        return mapCustomerRegistrationData(customerData);
    }

    @Override
    public User savePrivateSeller(PrivateSellerRegistrationDataDto sellerData) {
        return mapPrivateSellerRegistrationData(sellerData);
    }

    @Override
    public User savePrivateCustomer(PrivateCustomerRegistrationDataDto customerData) {
        return mapPrivateCustomerRegistrationData(customerData);
    }

    private User mapPrivateCustomerRegistrationData(PrivateCustomerRegistrationDataDto customerData) {
        User savedCustomer = saveUser(customerData.getUserDto());

        saveProfile(customerData.getProfileDto(), savedCustomer);

        return userService.findUserById(savedCustomer.getId());
    }

    private User mapCustomerRegistrationData(CustomerRegistrationDataDto customerData) {
        User savedCustomer = saveUser(customerData.getUserDto());

        Address savedAddress = saveAddress(customerData.getCompanyDto().getAddressDto());

        Company savedCompany = saveCompany(customerData.getCompanyDto(), savedAddress);

        saveProfile(customerData.getProfileDto(), savedCompany, savedCustomer);

        return userService.findUserById(savedCustomer.getId());
    }

    private User mapSellerRegistrationData(SellerRegistrationDataDto sellerData) {
        User savedSeller = saveUser(sellerData.getUserDto(), sellerData.getTradeSphereDto());

        Address savedAddress = saveAddress(sellerData.getCompanyDto().getAddressDto());

        Company savedCompany = saveCompany(sellerData.getCompanyDto(), savedAddress);

        saveProfile(sellerData.getProfileDto(), savedCompany, savedSeller);

        return userService.findUserById(savedSeller.getId());
    }

    private User mapPrivateSellerRegistrationData(PrivateSellerRegistrationDataDto sellerData) {
        User savedSeller = saveUser(sellerData.getUserDto(), sellerData.getTradeSphereDto());

        saveProfile(sellerData.getProfileDto(), savedSeller);

        return userService.findUserById(savedSeller.getId());
    }

    private Company saveCompany(CompanyDto companyDto, Address address) {
        return companyService.save(mapCompany(companyDto, address));
    }

    private Company mapCompany(CompanyDto companyDto, Address address) {
        Company company = new Company();

        if(!companyDto.getSrnNumber().equals("")){
            company.setSrn(Integer.valueOf(companyDto.getSrnNumber()));
        }

        company.setName(companyDto.getName());
        company.setEmail(companyDto.getEmail());
        company.setAddress(addressService.findById(address.getId()));

        return company;
    }

    private Profile saveProfile(ProfileDto profileDto, Company company, User user) {
        return profileService.saveProfile(mapProfile(profileDto, company, user));
    }

    private Profile saveProfile(ProfileDto profileDto, User user) {
        return profileService.saveProfile(mapProfile(profileDto, user));
    }

    private Profile mapProfile(ProfileDto profileDto, Company company, User user) {
        Profile profile = new Profile();

        profile.setFirstName(profileDto.getFirstName());
        profile.setLastName(profileDto.getLastName());
        profile.setPerson(profileDto.getPerson());
        profile.setTelephone(profileDto.getTelephone());
        profile.setBirthday(formatDate(profileDto.getBirthday()));
        profile.setCompany(companyService.findById(company.getId()));
        profile.setUser(userService.findUserById(user.getId()));
        profile.setChecked(CHECKED_USER);

        return profile;
    }

    private Profile mapProfile(ProfileDto profileDto, User user) {
        Profile profile = new Profile();

        profile.setFirstName(profileDto.getFirstName());
        profile.setLastName(profileDto.getLastName());
        profile.setPerson(profileDto.getPerson());
        profile.setTelephone(profileDto.getTelephone());
        profile.setBirthday(formatDate(profileDto.getBirthday()));
        profile.setUser(userService.findUserById(user.getId()));
        profile.setChecked(CHECKED_USER);

        return profile;
    }

    private Address saveAddress(AddressDto addressDto) {
        return addressService.save(mapAddress(addressDto));
    }

    private Address mapAddress(AddressDto addressDto) {
        Address address = new Address();

        if(!addressDto.getPostcode().equals("")){
            address.setPostcode(Integer.valueOf(addressDto.getPostcode()));
        }
        address.setCity(addressDto.getCity());
        address.setStreet(addressDto.getStreet());
        address.setBuildingNumber(addressDto.getBuildingNumber());

        return address;
    }

    private User saveUser(UserDto userDto, TradeSphereDto tradeSphereDto) {
        return userService.saveUser(mapUser(userDto, tradeSphereDto));
    }

    private User saveUser(UserDto userDto) {
        return userService.saveUser(mapUser(userDto));
    }

    private User mapUser(UserDto userDto, TradeSphereDto tradeSphereDto) {
        List<Role> roles = new ArrayList<>();
        List<Category> categories = new ArrayList<>();
        List<Location> locations = new ArrayList<>();

        User user = new User();

        for(Integer id : userDto.getRoles()) {
            roles.add(roleService.findRoleById(id));
        }

        for(Integer id : tradeSphereDto.getCategories()) {
            categories.add(categoryService.findCategoryById(id));
        }

        for(Integer id : tradeSphereDto.getLocations()) {
            locations.add(locationService.findById(id));
        }

        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setRoles(roles);
        user.setSellerCategories(categories);
        user.setSellerLocations(locations);
        user.setCreateDate(new Date());
        user.setEnabled(ENABLED_USER);

        return user;
    }

    private User mapUser(UserDto userDto) {
        List<Role> roles = new ArrayList<>();

        User user = new User();

        for(Integer id : userDto.getRoles()) {
            roles.add(roleService.findRoleById(id));
        }

        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setRoles(roles);
        user.setCreateDate(new Date());
        user.setEnabled(ENABLED_USER);

        return user;
    }
}
