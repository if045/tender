package com.softserveinc.tender.facade;

import com.softserveinc.tender.dto.ProfileDto;
import java.util.List;

public interface ProfileServiceFacade {

    List<ProfileDto> findAllProfiles();
    ProfileDto updateProfileStatus(Integer userId, String statusName);
}
