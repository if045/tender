package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Feedback;
import com.softserveinc.tender.entity.Profile;

import java.util.List;

public interface FeedbackService {

    List<Feedback> findByProfile(Profile profile);
    Feedback save(Feedback feedback);
    void deleteById(Integer id);
}
