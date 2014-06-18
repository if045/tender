package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Profile;
import java.util.List;

public interface ProfileService {

    List<Profile> findAll();
    Profile findProfileById(int id);
    Profile findProfileByUserLogin(String login);
    Profile saveProfile(Profile profile);
}
