package com.softserveinc.tender.facade;

import com.softserveinc.tender.dto.*;
import com.softserveinc.tender.repo.TenderFilter;
import java.util.List;

public interface TenderServiceFacade {

    List<TenderStatusDto> findTenderStatuses();
    List<ItemDto> findTendersItems(TenderFilter tenderFilter);
    List<TenderDto> findByCustomParams(TenderFilter tenderFilter);
    List<LocationDto> findLocations();
    List<CategoryDto> findCategories();
    List<ProposalDto> findTenderProposals(Integer tenderId);

}
