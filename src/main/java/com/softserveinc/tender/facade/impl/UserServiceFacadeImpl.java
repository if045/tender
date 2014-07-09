package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.AddressDto;
import com.softserveinc.tender.dto.CategoryDto;
import com.softserveinc.tender.dto.CompanyDto;
import com.softserveinc.tender.dto.CustomerRegistrationDataDto;
import com.softserveinc.tender.dto.LocationDto;
import com.softserveinc.tender.dto.LoggedUserDto;
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
import com.softserveinc.tender.entity.CheckedProfile;
import com.softserveinc.tender.entity.Company;
import com.softserveinc.tender.entity.Feedback;
import com.softserveinc.tender.entity.Location;
import com.softserveinc.tender.entity.Profile;
import com.softserveinc.tender.entity.Role;
import com.softserveinc.tender.entity.User;
import com.softserveinc.tender.entity.template.Roles;
import com.softserveinc.tender.facade.UserServiceFacade;
import com.softserveinc.tender.service.AddressService;
import com.softserveinc.tender.service.CategoryService;
import com.softserveinc.tender.service.CheckedProfileService;
import com.softserveinc.tender.service.CheckedStatusService;
import com.softserveinc.tender.service.CompanyService;
import com.softserveinc.tender.service.LocationService;
import com.softserveinc.tender.service.ProfileService;
import com.softserveinc.tender.service.RoleService;
import com.softserveinc.tender.service.UserService;
import com.softserveinc.tender.util.Constants;

import com.softserveinc.tender.util.UtilMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.softserveinc.tender.util.Constants.CHECKED_PROFILE_UNCHECKED_STATUS;
import static com.softserveinc.tender.util.Constants.CHECKED_USER;
import static com.softserveinc.tender.util.Constants.ENABLED_USER;
import static com.softserveinc.tender.util.Constants.LEGAL_PERSON;
import static com.softserveinc.tender.util.Util.formatDate;
import static com.softserveinc.tender.util.Util.getUserLogin;
import static com.softserveinc.tender.util.Util.setCurrentTimeStamp;
import static com.softserveinc.tender.util.Constants.CUSTOMER_AND_SELLER_HOME_PAGE;
import static com.softserveinc.tender.util.Constants.MODERATOR_HOME_PAGE;
import static com.softserveinc.tender.util.Constants.ADMINISTRATOR_HOME_PAGE;

@Service("registrationServiceFacade")
@Transactional
public class UserServiceFacadeImpl implements UserServiceFacade {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UtilMapper utilMapper;

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

    @Autowired
    private CheckedProfileService checkedProfileService;

    @Autowired
    private CheckedStatusService checkedStatusService;

    // registration logic
    @Override
    public List<RoleDto> findUsersRoles() {
        Type targetListType = new TypeToken<List<RoleDto>>() {
        }.getType();
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

        Profile savedProfile = saveProfile(customerData.getProfileDto(), savedCustomer);

        saveCheckedProfile(savedProfile);

        return userService.findUserById(savedCustomer.getId());
    }

    private User mapCustomerRegistrationData(CustomerRegistrationDataDto customerData) {
        User savedCustomer = saveUser(customerData.getUserDto());

        Address savedAddress = saveAddress(customerData.getCompanyDto().getAddressDto());

        Company savedCompany = saveCompany(customerData.getCompanyDto(), savedAddress);

        Profile savedProfile = saveProfile(customerData.getProfileDto(), savedCompany, savedCustomer);

        saveCheckedProfile(savedProfile);

        return userService.findUserById(savedCustomer.getId());
    }

    private User mapSellerRegistrationData(SellerRegistrationDataDto sellerData) {
        User savedSeller = saveUser(sellerData.getUserDto(), sellerData.getTradeSphereDto());

        Address savedAddress = saveAddress(sellerData.getCompanyDto().getAddressDto());

        Company savedCompany = saveCompany(sellerData.getCompanyDto(), savedAddress);

        Profile savedProfile = saveProfile(sellerData.getProfileDto(), savedCompany, savedSeller);

        saveCheckedProfile(savedProfile);

        return userService.findUserById(savedSeller.getId());
    }

    private User mapPrivateSellerRegistrationData(PrivateSellerRegistrationDataDto sellerData) {
        User savedSeller = saveUser(sellerData.getUserDto(), sellerData.getTradeSphereDto());

        Profile savedProfile = saveProfile(sellerData.getProfileDto(), savedSeller);

        saveCheckedProfile(savedProfile);

        return userService.findUserById(savedSeller.getId());
    }

    private Company saveCompany(CompanyDto companyDto, Address address) {
        return companyService.save(mapCompany(companyDto, address));
    }

    private Company mapCompany(CompanyDto companyDto, Address address) {
        Company company = new Company();

        if (!companyDto.getSrnNumber().equals("")) {
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

        if (!addressDto.getPostcode().equals("")) {
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

        for (Integer id : userDto.getRoles()) {
            roles.add(roleService.findRoleById(id));
        }

        for (Integer id : tradeSphereDto.getCategories()) {
            categories.add(categoryService.findCategoryById(id));
        }

        for (Integer id : tradeSphereDto.getLocations()) {
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

        for (Integer id : userDto.getRoles()) {
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

    private CheckedProfile saveCheckedProfile(Profile profile) {
        CheckedProfile checkedProfile = new CheckedProfile();

        checkedProfile.setProfile(profileService.findProfileById(profile.getId()));
        checkedProfile.setStatus(checkedStatusService.findByName(CHECKED_PROFILE_UNCHECKED_STATUS));
        return checkedProfileService.save(checkedProfile);
    }
    // end of registration logic

    // Fill profile page logic
    @Override
    public UsersProfileDataDto findUsersProfileInfo() {
        return mapUserProfileData(userService.findByLogin(getUserLogin()));
    }

    @Override
    public UsersProfileDataDto findUsersProfileInfoByLogin(String userLogin) {
        return mapUserProfileData(userService.findByLogin(userLogin));
    }

    @Override
    public String getHomePage() {
        List<Role> roles = userService.findByLogin(getUserLogin()).getRoles();
        String homePage = null;
        if (roles.size() == 1) {
            if (roles.get(0).getName().equals(Roles.CUSTOMER.toString()) ||
                    roles.get(0).getName().equals(Roles.SELLER.toString())) {
                homePage = CUSTOMER_AND_SELLER_HOME_PAGE;
            } else if (roles.get(0).getName().equals(Roles.MODERATOR.toString())) {
                homePage = MODERATOR_HOME_PAGE;
            } else if (roles.get(0).getName().equals(Roles.ADMIN.toString())) {
                homePage = ADMINISTRATOR_HOME_PAGE;
            }
        }
        if (roles.size() == 2) {
            if (roles.get(0).getName().equals(Roles.CUSTOMER.toString()) ||
                    roles.get(0).getName().equals(Roles.SELLER.toString())) {
                homePage = CUSTOMER_AND_SELLER_HOME_PAGE;
            }
        }
        return homePage;
    }

    public LoggedUserDto getLoggedUserInfo() {
        LoggedUserDto loggedUserDto=new LoggedUserDto();
        List<String> roles = new ArrayList<>();
        String userLogin = getUserLogin();
        if (!userLogin.equals(Constants.UNKNOWN_USER)){
            for (Role role:userService.findByLogin(userLogin).getRoles()){
                roles.add(role.getName());
            }
            loggedUserDto.setLogin(userLogin);
        }
        loggedUserDto.setRoles(roles);
        return loggedUserDto;
    }

    private UsersProfileDataDto mapUserProfileData(User user) {
        UsersProfileDataDto profileData = new UsersProfileDataDto();

        if (user.getProfile().getPerson() == LEGAL_PERSON) {
            profileData.setCompanyDto(utilMapper.mapObject(user.getProfile().getCompany(), CompanyDto.class));
        }

        profileData.setTradeSphereDto(mapTradeSphere(user));
        profileData.setPersonalInfoDto(mapPersonalInfo(user));
        profileData.setRatingDto(mapRatings(user));

        return profileData;
    }

    private UserDto mapUser(User user) {
        UserDto userDto = new UserDto();

        userDto.setLogin(user.getLogin());
        userDto.setRolesNames(mapRoles(user));

        return userDto;
    }

    private List<RoleDto> mapRoles(User user) {
        List<RoleDto> roles = new ArrayList<>();

        for (Role role : user.getRoles()) {
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

        tradeSphereDto.setCategoriesDto(utilMapper.mapObjects(user.getSellerCategories(), CategoryDto.class));
        tradeSphereDto.setLocationsDto(utilMapper.mapObjects(user.getSellerLocations(), LocationDto.class));

        return tradeSphereDto;
    }

    private List<RatingDto> mapRatings(User user) {
        List<RatingDto> ratings = new ArrayList<>();

        for (Feedback feedback : user.getProfile().getFeedbacks()) {
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
