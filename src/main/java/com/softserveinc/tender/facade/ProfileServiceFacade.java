package com.softserveinc.tender.facade;

import com.softserveinc.tender.dto.ProfileDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProfileServiceFacade {

    List<ProfileDto> findAllProfiles(Pageable pageable, String searchParam);
    Long getProfilesNumber(String searchParam);
    ProfileDto updateProfileStatus(Integer userId, String statusName);
}
