package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.ConflictDto;
import com.softserveinc.tender.entity.Conflict;
import com.softserveinc.tender.facade.ModeratorServiceFacade;
import com.softserveinc.tender.service.ConflictService;
import com.softserveinc.tender.service.UserService;
import com.softserveinc.tender.util.UtilMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.softserveinc.tender.util.Util.getUserLogin;

@Service("moderatorServiceFacade")
@Transactional
public class ModeratorServiceFacadeImpl implements ModeratorServiceFacade{

    @Autowired
    private ConflictService conflictService;

    @Autowired
    private UserService userService;

    @Autowired
    private UtilMapper utilMapper;

    @Override
    public List<ConflictDto> getConflicts() {
        List<Conflict> conflicts = conflictService.findAllByModeratorId(userService.findByLogin(getUserLogin()).getId());
        return utilMapper.mapObjects(conflicts, ConflictDto.class);
    }
}
