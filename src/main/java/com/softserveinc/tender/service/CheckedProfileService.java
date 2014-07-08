package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.CheckedProfile;
import java.util.List;

public interface CheckedProfileService {

    List<CheckedProfile> findAll();
    CheckedProfile findCheckedProfileById(int id);
    CheckedProfile saveCheckedProfile(CheckedProfile checkedProfile);
}
