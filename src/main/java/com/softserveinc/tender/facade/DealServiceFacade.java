package com.softserveinc.tender.facade;

import com.softserveinc.tender.dto.*;
import com.softserveinc.tender.repo.TenderFilter;

import java.util.List;

public interface DealServiceFacade {
    List<DealDto> findAllDeals();
    void updateDealWithStatus(Integer dealId, String statusName);
    FeedbackDto saveFeedback(FeedbackSaveDto feedbackSaveDto);
}
