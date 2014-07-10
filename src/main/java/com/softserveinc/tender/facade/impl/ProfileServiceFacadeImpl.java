package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.ProfileDto;
import com.softserveinc.tender.entity.CheckedProfile;
import com.softserveinc.tender.entity.Profile;
import com.softserveinc.tender.facade.ProfileServiceFacade;
import com.softserveinc.tender.service.CheckedProfileService;
import com.softserveinc.tender.service.CheckedStatusService;
import com.softserveinc.tender.service.ProfileService;
import com.softserveinc.tender.service.UserService;
import com.softserveinc.tender.util.Convertible;
import com.softserveinc.tender.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("profileServiceFacade")
@Transactional
public class ProfileServiceFacadeImpl implements ProfileServiceFacade {

    private static final String PROFILE_CHECKED_STATUS = "Checked";

    @Autowired
    private ProfileService profileService;

    @Autowired
    private CheckedProfileService checkedProfileService;

    @Autowired
    private CheckedStatusService checkedStatusService;

    @Autowired
    private UserService userService;

    @Autowired
    private Convertible convertible;

    @Override
    public List<ProfileDto> findAllProfiles(Pageable pageable, String searchParam) {
        return convertible.mapObjects(profileService.findAllProfiles(pageable, searchParam), ProfileDto.class);
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

        return convertible.mapObject(profile, ProfileDto.class);
    }
}
