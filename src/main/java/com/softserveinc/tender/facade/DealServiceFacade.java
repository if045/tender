package com.softserveinc.tender.facade;

import com.softserveinc.tender.dto.CategoryDto;
import com.softserveinc.tender.dto.ConflictDto;
import com.softserveinc.tender.dto.ConflictSaveDto;
import com.softserveinc.tender.dto.DealDto;
import com.softserveinc.tender.dto.ItemDto;
import com.softserveinc.tender.dto.LocationDto;
import com.softserveinc.tender.dto.TenderDto;
import com.softserveinc.tender.dto.TenderStatusDto;
import com.softserveinc.tender.repo.TenderFilter;

import java.util.List;

public interface DealServiceFacade {

    List<DealDto> findAllDeals();
    void updateDealWithStatus(Integer dealId, String statusName);
    ConflictDto saveConflict(ConflictSaveDto conflictSaveDto);
}
