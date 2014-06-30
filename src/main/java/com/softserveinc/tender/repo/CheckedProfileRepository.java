package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.CheckedProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface CheckedProfileRepository extends JpaRepository<CheckedProfile, Integer> {
    @Query("select c from CheckedProfile c where c.profile.id = :profileId")
    CheckedProfile findByProfileId(@Param("profileId")Integer profileId);
}
