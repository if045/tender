package com.softserveinc.tender.facade;

import com.softserveinc.tender.dto.ConflictDto;

import java.util.List;

public interface ModeratorServiceFacade {
    List<ConflictDto> getConflicts();
}
