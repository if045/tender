package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.ProfileDto;
import com.softserveinc.tender.entity.Profile;
import com.softserveinc.tender.facade.ProfileServiceFacade;
import com.softserveinc.tender.service.ProfileService;
import com.softserveinc.tender.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Service("profileServiceFacade")
@Transactional
public class ProfileServiceFacadeImpl implements ProfileServiceFacade {

    @Autowired
    private ProfileService profileService;

    @Override
    public List<ProfileDto> findAllProfiles() {
        List<Profile> profiles = profileService.findAllProfiles();

        return mapProfiles(profiles);
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

        return profileDto;
    }
}
