package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Feedback;

import java.util.List;

public interface FeedbackService {
    List<Feedback> findByProfile();
    void save(Feedback feedback);
    void deleteById(Integer id);
}
