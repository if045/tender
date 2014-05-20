package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    List<Feedback> findByProfile(Integer profileId);
}
