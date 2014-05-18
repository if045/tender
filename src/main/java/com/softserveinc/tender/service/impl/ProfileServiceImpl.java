package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.Profile;
import com.softserveinc.tender.repo.ProfileRepository;
import com.softserveinc.tender.service.ProfileService;

import java.util.List;

public class ProfileServiceImpl implements ProfileService {

    @Service
    @Transactional


        @Autowired
        private ProfileRepository profileRepository;

        @Override
        public List<Profile> findAll() {
            return profileRepository.findAll();
        }

        @Override
        public Profile findDealById(int id) {
            return profileRepository.findOne(id);
        }

        @Override
        public void saveProfile(Profile profile) {
            profileRepository.save(deal);
        }
}
