package com.softserveinc.tender.facade;

import com.softserveinc.tender.dto.ConflictDto;
import com.softserveinc.tender.dto.ConflictSaveDto;
import com.softserveinc.tender.dto.DealDto;
import com.softserveinc.tender.dto.FeedbackDto;
import com.softserveinc.tender.dto.FeedbackSaveDto;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface DealServiceFacade {

    List<DealDto> findAllDeals(Pageable pageable, String tenderTitle, String userRole);
    Long getNewDealsNumber(String userRole, String tenderTitle);
    void updateMyDealsDate();
    Long getDealsNumber(String userRole, String tenderTitle);
    void updateDealWithStatus(Integer dealId, String statusName);
    ConflictDto saveConflict(ConflictSaveDto conflictSaveDto);
    FeedbackDto saveFeedback(FeedbackSaveDto feedbackSaveDto);
}
