package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.Profile;
import com.softserveinc.tender.repo.ProfileRepository;
import com.softserveinc.tender.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    @Override
    public Profile findProfileById(int id) {
        return profileRepository.findOne(id);
    }

    @Override
    public void saveProfile(Profile profile) {
        profileRepository.save(profile);
    }

    @Override
    public Profile findProfileByUserLogin(String login) {
        return profileRepository.findByUserLogin(login);
    }
}
