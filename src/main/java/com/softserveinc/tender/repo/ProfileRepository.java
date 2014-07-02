package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Integer>{

    Profile findByUserLogin(String login);

    @Query("select p from Profile p")
    List<Profile> findAllProfiles();
}
