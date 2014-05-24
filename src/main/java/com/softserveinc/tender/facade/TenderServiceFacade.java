package com.softserveinc.tender.facade;

import com.softserveinc.tender.dto.TenderStatusDto;
import java.util.List;

public interface TenderServiceFacade {
    public List<TenderStatusDto> findTenderStatuses();
}
