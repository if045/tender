package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.CheckedProfile;
import com.softserveinc.tender.repo.CheckedProfileRepository;
import com.softserveinc.tender.service.CheckedProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CheckedProfileServiceImpl implements CheckedProfileService {

    @Autowired
    private CheckedProfileRepository checkedProfileRepository;

    @Override
    public List<CheckedProfile> findAll() {
        return checkedProfileRepository.findAll();
    }

    @Override
    public CheckedProfile findCheckedProfileById(int id) {
        return checkedProfileRepository.findOne(id);
    }

    @Override
    public CheckedProfile findCheckedProfileByProfileId(int profileId) {
        return checkedProfileRepository.findByProfileId(profileId);
    }

    @Override
    public CheckedProfile save(CheckedProfile checkedProfile){
        return checkedProfileRepository.save(checkedProfile);
    }
}
