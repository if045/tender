package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.ConflictDto;
import com.softserveinc.tender.dto.ConflictSaveDto;
import com.softserveinc.tender.dto.DealDto;
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
import com.softserveinc.tender.entity.template.Roles;
import com.softserveinc.tender.facade.DealServiceFacade;
import com.softserveinc.tender.service.ConflictService;
import com.softserveinc.tender.service.DealService;
import com.softserveinc.tender.service.FeedbackService;
import com.softserveinc.tender.service.ProfileService;
import com.softserveinc.tender.service.UserService;
import com.softserveinc.tender.util.Convertible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;


@Service("dealServiceFacade")
@Transactional
public class DealServiceFacadeImpl implements DealServiceFacade {

    @Autowired
    private DealService dealService;

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private Convertible convertible;

    @Autowired
    private ConflictService conflictService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @Override
    public List<DealDto> findAllDeals(Pageable pageable, String tenderTitle, String userRole) {
        List<Deal> deals = null;

        for (Role role : userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getRoles()) {
            if (role.getName().equals(Roles.CUSTOMER.name()) && userRole.equals(Roles.CUSTOMER.name())) {
                deals = dealService.findAllDealsForCustomer(pageable,
                        profileService.findProfileByUserLogin(userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getLogin()).getId(), tenderTitle);
            } else if (role.getName().equals(Roles.SELLER.name()) && userRole.equals(Roles.SELLER.name())){
                deals = dealService.findAllDealsForSeller(pageable,
                        profileService.findProfileByUserLogin(userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getLogin()).getId(), tenderTitle);
            }
        }

        return convertible.mapObjects(deals, DealDto.class);
    }

    @Override
    public Long getNewDealsNumber(String userRole, String tenderTitle) {
        Long newDealsNumber = 0L;

        for (Role role : userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getRoles()) {
            if (role.getName().equals(Roles.SELLER.name()) && userRole.equals(Roles.SELLER.name())) {
                newDealsNumber = dealService.getNewDealsNumberForSeller(
                        profileService.findProfileByUserLogin(userService.findByLogin(SecurityContextHolder
                                .getContext().getAuthentication().getName()).getLogin()).getId(), tenderTitle);
            }
        }

        return newDealsNumber;
    }

    @Override
    public void updateMyDealsDate() {
        User currentUser = userService.findUserById(userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
        Calendar calendar = Calendar.getInstance();
        Timestamp myDealsDate = new Timestamp(calendar.getTime().getTime());
        currentUser.setMyDealsDate(myDealsDate);
        userService.saveUser(currentUser);
    }

    @Override
    public Long getDealsNumber(String userRole, String tenderTitle) {
        Long dealsNumber = 0L;

        for (Role role : userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getRoles()) {
            if (role.getName().equals(Roles.CUSTOMER.name()) && userRole.equals(Roles.CUSTOMER.name())) {
                dealsNumber = dealService.getDealsNumberForCustomer(
                        profileService.findProfileByUserLogin(userService.findByLogin(SecurityContextHolder
                                .getContext().getAuthentication().getName()).getLogin()).getId(), tenderTitle);
            } else if (role.getName().equals(Roles.SELLER.name()) && userRole.equals(Roles.SELLER.name())){
                dealsNumber = dealService.getDealsNumberForSeller(
                        profileService.findProfileByUserLogin(userService.findByLogin(SecurityContextHolder
                                .getContext().getAuthentication().getName()).getLogin()).getId(), tenderTitle);
            }
        }

        return dealsNumber;
    }

    @Override
    public void updateDealWithStatus(Integer dealId, String statusName) {
        dealService.updateDealWithStatus(dealId, statusName);
    }

    @Override
    public ConflictDto saveConflict(ConflictSaveDto conflictSaveDto) {
        Conflict conflict = new Conflict();
        conflict.setDescription(conflictSaveDto.getDescription());
        User moderator = new User();
        moderator.setId(userService.findByModeratorCategoriesId(dealService.findDealById(conflictSaveDto.getDealId()).getBid().getUnit().getItem().getCategory().getId()).getId());
        conflict.setModerator(moderator);
        ConflictStatus conflictStatus = new ConflictStatus();
        conflictStatus.setId(1);
        conflict.setStatus(conflictStatus);
        Bid bid = new Bid();
        bid.setId(dealService.findDealById(conflictSaveDto.getDealId()).getBid().getId());
        conflict.setBid(bid);

        Conflict savedConflict = conflictService.save(conflict);
        return convertible.mapObject(savedConflict, ConflictDto.class);
    }

    @Override
    public FeedbackDto saveFeedback(FeedbackSaveDto feedbackSaveDto) {
        Feedback feedback = new Feedback();
        feedback.setCommunication(feedbackSaveDto.getCommunication());
        feedback.setSpeed(feedbackSaveDto.getSpeed());
        feedback.setLogistic(feedbackSaveDto.getLogistic());
        feedback.setComment(feedbackSaveDto.getComment());
        Profile profile = new Profile();
        for (Role role : userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getRoles()) {
            if (role.getName().equals(Roles.SELLER.name())) {
                profile.setId(dealService.findDealById(feedbackSaveDto.getProfileId()).getCustomer().getId());
            } else if (role.getName().equals(Roles.CUSTOMER.name())) {
                profile.setId(dealService.findDealById(feedbackSaveDto.getProfileId()).getSeller().getId());
            }
        }
        feedback.setProfile(profile);
        User user = new User();
        user.setId(userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
        feedback.setUser(user);

        Feedback savedFeedback = feedbackService.save(feedback);
        return convertible.mapObject(savedFeedback, FeedbackDto.class);
    }
}
