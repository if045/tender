package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Integer>{
}
