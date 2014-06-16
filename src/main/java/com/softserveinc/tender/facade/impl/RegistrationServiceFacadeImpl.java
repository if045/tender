package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.AddressDto;
import com.softserveinc.tender.dto.CompanyDto;
import com.softserveinc.tender.dto.ProfileDto;
import com.softserveinc.tender.dto.RoleDto;
import com.softserveinc.tender.dto.TradeSphereDto;
import com.softserveinc.tender.dto.UserDto;
import com.softserveinc.tender.dto.UserRegistrationDataDto;
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

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.softserveinc.tender.util.Util.formatDate;

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

    public User saveUser(UserRegistrationDataDto userData) {
        return mapUserRegistrationData(userData);
    }

    private User mapUserRegistrationData(UserRegistrationDataDto userData) {
        User savedUser = saveUser(userData.getUserDto(), userData.getTradeSphereDto());

        Address savedAddress = saveAddress(userData.getCompanyDto().getAddressDto());

        Company savedCompany = saveCompany(userData.getCompanyDto(), savedAddress);

        saveProfile(userData.getProfileDto(), savedCompany, savedUser);

        return userService.findUserById(savedUser.getId());
    }

    private Company saveCompany(CompanyDto companyDto, Address address) {
        return companyService.save(mapCompany(companyDto, address));
    }

    private Company mapCompany(CompanyDto companyDto, Address address) {
        Company company = new Company();

        company.setName(companyDto.getName());
        company.setEmail(companyDto.getEmail());
        company.setSrn(companyDto.getSrnNumber());
        company.setAddress(addressService.findById(address.getId()));

        return company;
    }

    private Profile saveProfile(ProfileDto profileDto, Company company, User user) {
        return profileService.saveProfile(mapProfile(profileDto, company, user));
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
        profile.setChecked(false);

        return profile;
    }

    private Address saveAddress(AddressDto addressDto) {
        return addressService.save(mapAddress(addressDto));
    }

    private Address mapAddress(AddressDto addressDto) {
        Address address = new Address();

        address.setCity(addressDto.getCity());
        address.setStreet(addressDto.getStreet());
        address.setBuildingNumber(addressDto.getBuildingNumber());
        address.setPostcode(addressDto.getPostcode());

        return address;
    }

    private User saveUser(UserDto userDto, TradeSphereDto tradeSphereDto) {
        return userService.saveUser(mapUser(userDto, tradeSphereDto));
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

        return user;
    }
}
