package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.ConflictDto;
import com.softserveinc.tender.entity.Conflict;
import com.softserveinc.tender.facade.ModeratorServiceFacade;
import com.softserveinc.tender.service.ConflictService;
import com.softserveinc.tender.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.softserveinc.tender.util.Util.getUserLogin;

@Service("moderatorServiceFacade")
@Transactional
public class ModeratorServiceFacadeImpl implements ModeratorServiceFacade{

    @Autowired
    private ConflictService conflictService;

    @Autowired
    private UserService userService;

    @Override
    public List<ConflictDto> getConflicts() {
        List<Conflict> conflicts = conflictService.findAllByModeratorId(userService.findByLogin(getUserLogin()).getId());
        return mapConflicts(conflicts);
    }

    private List<ConflictDto> mapConflicts(List<Conflict> conflicts) {
        List<ConflictDto> conflictDtos = new ArrayList<>();
        for (Conflict conflict : conflicts) {
            conflictDtos.add(mapConflict(conflict));
        }
        return conflictDtos;
    }

    private ConflictDto mapConflict(Conflict conflict) {
        ConflictDto conflictDto = new ConflictDto();
        conflictDto.setTitle(conflict.getBid().getProposal().getTender().getTitle());
        conflictDto.setCustomerName(conflict.getBid().getProposal().getTender().getAuthor().getFirstName());
        conflictDto.setSellerName(conflict.getBid().getProposal().getSeller().getProfile().getFirstName());
        return conflictDto;
    }
}