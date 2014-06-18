package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.DealDto;
import com.softserveinc.tender.dto.FeedbackDto;
import com.softserveinc.tender.dto.FeedbackSaveDto;
import com.softserveinc.tender.entity.Deal;
import com.softserveinc.tender.entity.Feedback;
import com.softserveinc.tender.entity.Profile;
import com.softserveinc.tender.entity.User;
import com.softserveinc.tender.facade.DealServiceFacade;
import com.softserveinc.tender.service.DealService;
import com.softserveinc.tender.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("dealServiceFacade")
@Transactional
public class DealServiceFacadeImpl implements DealServiceFacade {

    @Autowired
    private DealService dealService;

    @Autowired
    private FeedbackService feedbackService;

    @Override
    public List<DealDto> findAllDeals() {
        List<Deal> deals = dealService.findAllDeals();
        return mapDeals(deals);
    }

    @Override
    public void updateDealWithStatus(Integer dealId, String statusName) {
        dealService.updateDealWithStatus(dealId, statusName);
    }

    @Override
    public List<DealDto> mapDeals(List<Deal> deals) {
        List<DealDto> dealDtos = new ArrayList<>();
        for (Deal deal : deals) {
            dealDtos.add(mapDeal(deal));
        }

        return dealDtos;
    }

    @Override
    public DealDto mapDeal(Deal deal) {
        DealDto dealDto = new DealDto();
        dealDto.setId(deal.getId());
        dealDto.setDate(deal.getDate());
        dealDto.setStatus(deal.getStatus().getName());
        dealDto.setSum(deal.getSum());
        dealDto.setDate(deal.getDate());
        dealDto.setTitle(deal.getProposal().getTender().getTitle());
        dealDto.setBusinessPartner(deal.getCustomer().getFirstName());

        return dealDto;
    }
    private FeedbackDto mapFeedback(Feedback feedback) {
        FeedbackDto feedbackDto = new FeedbackDto();
        feedbackDto.setId(feedback.getId());
        feedbackDto.setProfileId(feedback.getProfile().getId());
        feedbackDto.setUserId(feedback.getUser().getId());
        feedbackDto.setCommunication(feedback.getCommunication());
        feedbackDto.setSpeed(feedback.getSpeed());
        feedbackDto.setLogistic(feedback.getLogistic());
        feedbackDto.setComment(feedback.getComment());
        return feedbackDto;
    }

    @Override
    public FeedbackDto saveFeedback(FeedbackSaveDto feedbackSaveDto) {
        Feedback feedback = new Feedback();
        feedback.setCommunication(feedbackSaveDto.getCommunication());
        feedback.setSpeed(feedbackSaveDto.getSpeed());
        feedback.setLogistic(feedbackSaveDto.getLogistic());
        feedback.setComment(feedbackSaveDto.getComment());
        Profile profile = new Profile();
        profile.setId(dealService.findDealById(feedbackSaveDto.getProfileId()).getCustomer().getId());
        feedback.setProfile(profile);
        //TODO: change user id after finish security
        User user = new User();
        user.setId(1);
        feedback.setUser(user);

        Feedback savedFeedback = feedbackService.save(feedback);
        return mapFeedback(savedFeedback);
    }
}
