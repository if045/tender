package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Profile;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProfileService {

    List<Profile> findAllProfiles(Pageable pageable, String searchParam);
    Long getProfilesNumber(String searchParam);
    Profile findProfileById(int id);
    Profile findProfileByUserLogin(String login);
    Profile saveProfile(Profile profile);
}
