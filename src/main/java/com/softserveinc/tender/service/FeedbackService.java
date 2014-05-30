package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Feedback;
import java.util.List;

public interface FeedbackService {

    List<Feedback> findByProfile(Integer profileId);
    void save(Feedback feedback);
    void deleteById(Integer id);
}
