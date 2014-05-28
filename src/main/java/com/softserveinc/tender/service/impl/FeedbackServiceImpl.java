package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.Feedback;
import com.softserveinc.tender.repo.FeedbackRepository;
import com.softserveinc.tender.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public List<Feedback> findByProfile(Integer profileId) {
        return feedbackRepository.findByProfile(profileId);
    }

    @Override
    public void save(Feedback feedback) {
        feedbackRepository.save(feedback);
    }

    @Override
    public void deleteById(Integer id) {
        feedbackRepository.delete(id);
    }
}
