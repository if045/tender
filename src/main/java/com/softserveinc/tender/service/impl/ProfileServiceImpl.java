package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.Profile;
import com.softserveinc.tender.repo.ProfileRepository;
import com.softserveinc.tender.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    public static final String PERCENT = "%";

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public List<Profile> findAllProfiles(Pageable pageable, String searchParam) {
        Integer searchFlag = (searchParam == null) ? 1 : 0;
        if (searchFlag == 0){
            searchParam = PERCENT + searchParam + PERCENT;
        }

        return profileRepository.findAllProfiles(searchParam, searchFlag, pageable);
    }

    @Override
    public Long getProfilesNumber(String searchParam) {
        Integer searchFlag = (searchParam == null) ? 1 : 0;
        if (searchFlag == 0){
            searchParam = PERCENT + searchParam + PERCENT;
        }

        return profileRepository.getProfilesNumber(searchParam, searchFlag);
    }

    @Override
    public Profile findProfileById(int id) {
        return profileRepository.findOne(id);
    }

    @Override
    public Profile saveProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public Profile findProfileByUserLogin(String login) {
        return profileRepository.findByUserLogin(login);
    }
}
