package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.Profile;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Integer>{

    Profile findByUserLogin(String login);

    @Query("select p from Profile p where (1 = :searchFlag or p.user.login like :profileLogin)")
    List<Profile> findAllProfiles(@Param("profileLogin") String profileLogin,
                                  @Param("searchFlag") Integer searchFlag,
                                  Pageable pageable);

    @Query("select count(distinct p) from Profile p where (1 = :searchFlag or p.user.login like :profileLogin)")
    Long getProfilesNumber(@Param("profileLogin") String profileLogin,
                           @Param("searchFlag") Integer searchFlag);
}
