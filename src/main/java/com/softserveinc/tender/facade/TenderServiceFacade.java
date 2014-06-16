package com.softserveinc.tender.facade;

import com.softserveinc.tender.dto.CategoryDto;
import com.softserveinc.tender.dto.ItemDto;
import com.softserveinc.tender.dto.LocationDto;
import com.softserveinc.tender.dto.ProposalSaveDto;
import com.softserveinc.tender.dto.TenderSaveDto;
import com.softserveinc.tender.dto.ProposalDto;
import com.softserveinc.tender.dto.TenderDto;
import com.softserveinc.tender.dto.TenderStatusDto;
import com.softserveinc.tender.dto.UnitDto;
import com.softserveinc.tender.repo.TenderFilter;
import java.util.List;

public interface TenderServiceFacade {

    List<TenderDto> findByCustomParams(TenderFilter tenderFilter, String userLogin);
    TenderDto saveTender(TenderSaveDto tenderSaveDto, String userLogin);
    List<TenderStatusDto> findTendersStatuses();
    List<ItemDto> findTendersItems(TenderFilter tenderFilter);
    List<LocationDto> findTendersLocations();
    List<CategoryDto> findTendersCategories();
    List<UnitDto> findUnitsByTenderId(Integer tenderId);
    TenderDto updateTender(Integer tenderId, String statusName, String endDate, String description);
    List<ProposalDto> findTendersProposals(Integer tenderId);
    ProposalDto saveProposal(ProposalSaveDto proposalSaveDto);
    TenderDto findOneById(Integer id);
}
