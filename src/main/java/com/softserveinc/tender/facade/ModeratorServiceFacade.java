package com.softserveinc.tender.facade;

import com.softserveinc.tender.dto.ConflictDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ModeratorServiceFacade {
    List<ConflictDto> getConflicts(Pageable pageable, String searchParam);

    Long getConflictsNumber(String searchParam);
}
