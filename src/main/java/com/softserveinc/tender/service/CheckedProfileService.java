package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.CheckedProfile;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CheckedProfileService {

    List<CheckedProfile> findAll();
    CheckedProfile findCheckedProfileById(int id);
    CheckedProfile findCheckedProfileByProfileId(int profileId);
    CheckedProfile save(CheckedProfile checkedProfile);
}
