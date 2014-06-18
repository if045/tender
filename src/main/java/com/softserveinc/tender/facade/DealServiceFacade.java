package com.softserveinc.tender.facade;

import com.softserveinc.tender.dto.DealDto;
import com.softserveinc.tender.entity.Deal;
import com.softserveinc.tender.dto.FeedbackDto;
import com.softserveinc.tender.dto.FeedbackSaveDto;

import java.util.List;

public interface DealServiceFacade {

    List<DealDto> findAllDeals();
    void updateDealWithStatus(Integer dealId, String statusName);
    List<DealDto> mapDeals(List<Deal> deals);
    DealDto mapDeal(Deal deal);
    FeedbackDto saveFeedback(FeedbackSaveDto feedbackSaveDto);
}
