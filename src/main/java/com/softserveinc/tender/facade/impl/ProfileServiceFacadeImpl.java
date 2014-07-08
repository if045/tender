package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.ProfileDto;
import com.softserveinc.tender.dto.RoleDto;
import com.softserveinc.tender.entity.CheckedProfile;
import com.softserveinc.tender.entity.CheckedStatus;
import com.softserveinc.tender.entity.Profile;
import com.softserveinc.tender.entity.Role;
import com.softserveinc.tender.facade.ProfileServiceFacade;
import com.softserveinc.tender.service.CheckedProfileService;
import com.softserveinc.tender.service.CheckedStatusService;
import com.softserveinc.tender.service.ProfileService;
import com.softserveinc.tender.service.RoleService;
import com.softserveinc.tender.service.UserService;
import com.softserveinc.tender.util.Util;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Service("profileServiceFacade")
@Transactional
public class ProfileServiceFacadeImpl implements ProfileServiceFacade {

    private static final String PROFILE_CHECKED_STATUS = "Checked";
    private static final String PROFILE_UNCHECKED_STATUS = "Unchecked";

    @Autowired
    private ProfileService profileService;

    @Autowired
    private CheckedProfileService checkedProfileService;

    @Autowired
    private CheckedStatusService checkedStatusService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleService roleService;

    @Override
    public List<ProfileDto> findAllProfiles(Pageable pageable, String searchParam) {
        List<Profile> profiles = profileService.findAllProfiles(pageable, searchParam);

        return mapProfiles(profiles);
    }

    @Override
    public Long getProfilesNumber(String searchParam) {
        return profileService.getProfilesNumber(searchParam);
    }

    @Override
    public ProfileDto updateProfileStatus(Integer userId, String statusName) {
        Profile profile = profileService.findProfileByUserLogin(userService.findUserById(userId).getLogin());
        if(profile != null) {
            if(statusName.equals(PROFILE_CHECKED_STATUS)) {
                profile.setChecked(true);
            } else {
                profile.setChecked(false);
            }
            profileService.saveProfile(profile);

            CheckedProfile checkedProfile = checkedProfileService.findCheckedProfileByProfileId(profile.getId());
            if(checkedProfile == null) {
                checkedProfile = new CheckedProfile();
                checkedProfile.setProfile(profile);
            }
            checkedProfile.setModerator(userService.findByLogin(Util.getUserLogin()));
            checkedProfile.setStatus(checkedStatusService.findByName(statusName));
            checkedProfileService.save(checkedProfile);
        }

        return mapProfile(profile);
    }

    private List<ProfileDto> mapProfiles(List<Profile> profiles) {
        List<ProfileDto> profileDtos = new ArrayList<>();
        for (Profile profile : profiles) {
            profileDtos.add(mapProfile(profile));
        }

        return profileDtos;
    }

    private ProfileDto mapProfile(Profile profile) {
        ProfileDto profileDto = new ProfileDto();
        profileDto.setBirthday(profile.getBirthday().toString());
        profileDto.setFirstName(profile.getFirstName());
        profileDto.setLastName(profile.getLastName());
        profileDto.setTelephone(profile.getTelephone());
        profileDto.setPerson(profile.getPerson());
        profileDto.setUserId(profile.getUser().getId());
        profileDto.setRoles(mapRoles(profile.getUser().getRoles()));
        if(profile.getCompany() != null) {
            profileDto.setCompanyId(profile.getCompany().getId());
        }
        profileDto.setUserLogin(userService.findUserById(profile.getUser().getId()).getLogin());

        CheckedProfile checkedProfile = checkedProfileService.findCheckedProfileByProfileId(profile.getId());
        if(checkedProfile != null) {
            profileDto.setStatus(checkedProfile.getStatus().getName());
        } else {
            profileDto.setStatus(PROFILE_UNCHECKED_STATUS);
        }

        return profileDto;
    }

    private List<RoleDto> mapRoles(List<Role> roles) {
        Type targetListType = new TypeToken<List<RoleDto>>() {
        }.getType();
        return modelMapper.map(roles, targetListType);
    }
}
