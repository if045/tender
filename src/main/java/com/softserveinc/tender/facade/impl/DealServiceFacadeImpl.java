package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.ConflictDto;
import com.softserveinc.tender.dto.ConflictSaveDto;
import com.softserveinc.tender.dto.DealDto;
import com.softserveinc.tender.dto.DealsNumberDto;
import com.softserveinc.tender.dto.FeedbackDto;
import com.softserveinc.tender.dto.FeedbackSaveDto;
import com.softserveinc.tender.entity.Deal;
import com.softserveinc.tender.entity.Feedback;
import com.softserveinc.tender.entity.Profile;
import com.softserveinc.tender.entity.Role;
import com.softserveinc.tender.entity.User;
import com.softserveinc.tender.entity.Conflict;
import com.softserveinc.tender.entity.ConflictStatus;
import com.softserveinc.tender.entity.Bid;
import com.softserveinc.tender.facade.DealServiceFacade;
import com.softserveinc.tender.service.ConflictService;
import com.softserveinc.tender.service.DealService;
import com.softserveinc.tender.service.FeedbackService;
import com.softserveinc.tender.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ConflictService conflictService;

    @Autowired
    private UserService userService;

    @Override
    public List<DealDto> findAllDeals(Pageable pageable, String tenderTitle) {
        List<Deal> deals = null;
        for (Role role : userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getRoles()) {
            if (role.getName().equals("CUSTOMER")) {
                deals = dealService.findAllDealsForCustomer(pageable, userService.findByLogin(SecurityContextHolder
                        .getContext().getAuthentication().getName()).getId(), tenderTitle);
            } else if (role.getName().equals("SELLER")){
                deals = dealService.findAllDealsForSeller(pageable, userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId(), tenderTitle);
            }
        }

        return mapDeals(deals);
    }

    @Override
    public DealsNumberDto getDealsNumber() {
        Long dealsNumber = 0L;

        for (Role role : userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getRoles()) {
            if (role.getName().equals("CUSTOMER")) {
                dealsNumber = dealService.getDealsNumberForCustomer(userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
            } else if (role.getName().equals("SELLER")){
                dealsNumber = dealService.getDealsNumberForSeller(userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
            }
        }

        DealsNumberDto dealsNumberDto = new DealsNumberDto();
        dealsNumberDto.setDealsNumber(dealsNumber);

        return dealsNumberDto;
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

        String businessPartner = "";
        for (Role role : userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getRoles()) {
            if (role.getName().equals("CUSTOMER")) {
                businessPartner = deal.getSeller().getFirstName() + " " + deal.getSeller().getLastName();
            } else if (role.getName().equals("SELLER")){
                businessPartner = deal.getCustomer().getFirstName() + " " + deal.getCustomer().getLastName();
            }
        }
        dealDto.setBusinessPartner(businessPartner);

        return dealDto;
    }

    private ConflictDto mapConflict(Conflict conflict) {
        ConflictDto conflictDto = new ConflictDto();
        conflictDto.setId(conflict.getId());
        conflictDto.setBidId(conflict.getBid().getId());
        conflictDto.setDescription(conflict.getDescription());
        conflictDto.setStatusId(conflict.getStatus().getId());
        return conflictDto;
    }

    @Override
    public ConflictDto saveConflict(ConflictSaveDto conflictSaveDto) {
        Conflict conflict = new Conflict();
        conflict.setDescription(conflictSaveDto.getDescription());
        User moderator = new User();
        moderator.setId(userService.findByModeratorCategoriesId(dealService.findDealById(conflictSaveDto.getBidId()).getBid().getUnit().getItem().getCategory().getId()).getId());
        conflict.setModerator(moderator);
        ConflictStatus conflictStatus = new ConflictStatus();
        conflictStatus.setId(1);
        conflict.setStatus(conflictStatus);
        Bid bid = new Bid();
        bid.setId(dealService.findDealById(conflictSaveDto.getBidId()).getBid().getId());
        conflict.setBid(bid);

        Conflict savedConflict = conflictService.save(conflict);
        return mapConflict(savedConflict);
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
        User user = new User();
        user.setId(userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
        feedback.setUser(user);

        Feedback savedFeedback = feedbackService.save(feedback);
        return mapFeedback(savedFeedback);
    }
}
