package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.CheckedProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckedProfileRepository extends JpaRepository<CheckedProfile, Integer> {

}
