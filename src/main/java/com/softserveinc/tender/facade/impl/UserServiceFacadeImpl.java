package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.AddressDto;
import com.softserveinc.tender.dto.CategoryDto;
import com.softserveinc.tender.dto.CompanyDto;
import com.softserveinc.tender.dto.CustomerRegistrationDataDto;
import com.softserveinc.tender.dto.LocationDto;
import com.softserveinc.tender.dto.PersonalInfoDto;
import com.softserveinc.tender.dto.PrivateCustomerRegistrationDataDto;
import com.softserveinc.tender.dto.PrivateSellerRegistrationDataDto;
import com.softserveinc.tender.dto.ProfileDto;
import com.softserveinc.tender.dto.RatingDto;
import com.softserveinc.tender.dto.RoleDto;
import com.softserveinc.tender.dto.SellerRegistrationDataDto;
import com.softserveinc.tender.dto.TradeSphereDto;
import com.softserveinc.tender.dto.UserDto;
import com.softserveinc.tender.dto.UsersProfileDataDto;
import com.softserveinc.tender.entity.Address;
import com.softserveinc.tender.entity.Category;
import com.softserveinc.tender.entity.Company;
import com.softserveinc.tender.entity.Feedback;
import com.softserveinc.tender.entity.Location;
import com.softserveinc.tender.entity.Profile;
import com.softserveinc.tender.entity.Role;
import com.softserveinc.tender.entity.User;
import com.softserveinc.tender.facade.UserServiceFacade;
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

import static com.softserveinc.tender.util.Constants.LEGAL_PERSON;
import static com.softserveinc.tender.util.Util.formatDate;
import static com.softserveinc.tender.util.Constants.CHECKED_USER;
import static com.softserveinc.tender.util.Constants.ENABLED_USER;
import static com.softserveinc.tender.util.Util.getUserLogin;
import static com.softserveinc.tender.util.Util.setCurrentTimeStamp;

@Service("registrationServiceFacade")
@Transactional
public class UserServiceFacadeImpl implements UserServiceFacade {

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

    // registration logic
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
        user.setMyDealsDate(setCurrentTimeStamp());

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
        user.setMyDealsDate(setCurrentTimeStamp());

        return user;
    }

    // Fill profile page logic
    @Override
    public UsersProfileDataDto findUsersProfileInfo() {
        return mapUserProfileData(userService.findByLogin(getUserLogin()));
    }

    private UsersProfileDataDto mapUserProfileData(User user) {
        UsersProfileDataDto profileData = new UsersProfileDataDto();

        profileData.setCompanyDto(mapCompany(user));
        profileData.setTradeSphereDto(mapTradeSphere(user));
        profileData.setPersonalInfoDto(mapPersonalInfo(user));
        profileData.setRatingDto(mapRatings(user));

        return profileData;
    }

    private AddressDto mapAddress(User user) {
        AddressDto addressDto = new AddressDto();

        addressDto.setStreet(user.getProfile().getCompany().getAddress().getStreet());
        addressDto.setCity(user.getProfile().getCompany().getAddress().getCity());
        addressDto.setBuildingNumber(String.valueOf(user.getProfile().getCompany().getAddress().getBuildingNumber()));
        addressDto.setPostcode(String.valueOf(user.getProfile().getCompany().getAddress().getPostcode()));

        return addressDto;
    }

    private CompanyDto mapCompany(User user) {
        CompanyDto companyDto = new CompanyDto();

        if(user.getProfile().getPerson() == LEGAL_PERSON) {
            companyDto.setName(user.getProfile().getCompany().getName());
            companyDto.setEmail(user.getProfile().getCompany().getEmail());
            companyDto.setSrnNumber(String.valueOf(user.getProfile().getCompany().getSrn()));
            companyDto.setAddressDto(mapAddress(user));
        }

        return companyDto;
    }

    private UserDto mapUser(User user) {
        UserDto userDto = new UserDto();

        userDto.setLogin(user.getLogin());
        userDto.setRolesNames(mapRoles(user));

        return userDto;
    }

    private List<RoleDto> mapRoles(User user) {
        List<RoleDto> roles = new ArrayList<>();

        for(Role role : user.getRoles()) {
            roles.add(mapRole(role));
        }
        return roles;
    }

    private RoleDto mapRole(Role role) {
        RoleDto roleDto = new RoleDto();

        roleDto.setId(role.getId());
        roleDto.setName(role.getName());

        return roleDto;
    }

    private ProfileDto mapProfile(User user) {
        ProfileDto profileDto = new ProfileDto();

        profileDto.setFirstName(user.getProfile().getFirstName());
        profileDto.setLastName(user.getProfile().getLastName());
        profileDto.setTelephone(user.getProfile().getTelephone());
        profileDto.setPerson(user.getProfile().getPerson());
        profileDto.setBirthday(String.valueOf(user.getProfile().getBirthday()));

        return profileDto;
    }

    private PersonalInfoDto mapPersonalInfo(User user) {
        PersonalInfoDto personalInfoDto = new PersonalInfoDto();

        personalInfoDto.setProfileDto(mapProfile(user));
        personalInfoDto.setUserDto(mapUser(user));

        return personalInfoDto;
    }

    private TradeSphereDto mapTradeSphere(User user) {
        TradeSphereDto tradeSphereDto = new TradeSphereDto();

        tradeSphereDto.setCategoriesDto(mapCategories(user));
        tradeSphereDto.setLocationsDto(mapLocations(user));

        return tradeSphereDto;
    }

    private List<CategoryDto> mapCategories(User user) {
        List<CategoryDto> categories = new ArrayList<>();

        for(Category category : user.getSellerCategories()) {
            categories.add(mapCategory(category));
        }
        return categories;
    }

    private CategoryDto mapCategory(Category category) {
        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());

        return categoryDto;
    }

    private List<LocationDto> mapLocations(User user) {
        List<LocationDto> locations = new ArrayList<>();

        for(Location location : user.getSellerLocations()) {
            locations.add(mapLocation(location));
        }
        return locations;
    }

    private LocationDto mapLocation(Location location) {
        LocationDto locationDto = new LocationDto();

        locationDto.setId(location.getId());
        locationDto.setName(location.getName());

        return locationDto;
    }

    private List<RatingDto> mapRatings(User user) {
        List<RatingDto> ratings = new ArrayList<>();

        for(Feedback feedback : user.getProfile().getFeedbacks()) {
            ratings.add(mapRating(feedback));
        }
        return ratings;
    }

    private RatingDto mapRating(Feedback feedback) {
        RatingDto ratingDto = new RatingDto();

        ratingDto.setCommunication(feedback.getCommunication());
        ratingDto.setLogistic(feedback.getLogistic());
        ratingDto.setSpeed(feedback.getSpeed());

        return ratingDto;
    }
}