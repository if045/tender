package com.softserveinc.tender.facade;

import com.softserveinc.tender.dto.CategoryDto;
import com.softserveinc.tender.dto.ItemDto;
import com.softserveinc.tender.dto.LocationDto;
import com.softserveinc.tender.dto.TenderSaveDto;
import com.softserveinc.tender.dto.ProposalDto;
import com.softserveinc.tender.dto.TenderDto;
import com.softserveinc.tender.dto.TenderStatusDto;
import com.softserveinc.tender.dto.TendersNumberDto;
import com.softserveinc.tender.dto.UnitDto;
import com.softserveinc.tender.repo.TenderFilter;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TenderServiceFacade {

    List<TenderDto> findByCustomParams(TenderFilter tenderFilter, Pageable pageable);
    TendersNumberDto findByCustomParamsResultSize(TenderFilter tenderFilter);
    List<TenderDto> findByCustomParams(TenderFilter tenderFilter);
    TenderDto saveTender(TenderSaveDto tenderSaveDto);
    List<TenderStatusDto> findTendersStatuses();
    List<ItemDto> findTendersItems(TenderFilter tenderFilter);
    List<LocationDto> findTendersLocations();
    List<CategoryDto> findTendersCategories();
    List<UnitDto> findUnitsByTenderId(Integer tenderId);
    void updateTenderWithStatus(Integer tenderId, String statusName);
    List<ProposalDto> findTendersProposals(Integer tenderId);
}
