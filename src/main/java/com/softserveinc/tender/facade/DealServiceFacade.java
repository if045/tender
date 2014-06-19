package com.softserveinc.tender.facade;

import com.softserveinc.tender.dto.ConflictDto;
import com.softserveinc.tender.dto.ConflictSaveDto;
import com.softserveinc.tender.dto.DealDto;
import com.softserveinc.tender.dto.DealsNumberDto;
import com.softserveinc.tender.dto.FeedbackDto;
import com.softserveinc.tender.dto.FeedbackSaveDto;
import org.springframework.data.domain.Pageable;
import com.softserveinc.tender.entity.Deal;
import java.util.List;

public interface DealServiceFacade {

    List<DealDto> findAllDeals(Pageable pageable);
    DealsNumberDto getNewDealsNumber();
    DealsNumberDto getDealsNumber();
    void updateDealWithStatus(Integer dealId, String statusName);
    ConflictDto saveConflict(ConflictSaveDto conflictSaveDto);
    List<DealDto> mapDeals(List<Deal> deals);
    DealDto mapDeal(Deal deal);
    FeedbackDto saveFeedback(FeedbackSaveDto feedbackSaveDto);
}
