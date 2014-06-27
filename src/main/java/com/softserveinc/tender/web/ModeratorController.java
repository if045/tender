package com.softserveinc.tender.web;

import com.softserveinc.tender.dto.ConflictDto;
import com.softserveinc.tender.facade.ModeratorServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/moderator")
public class ModeratorController {

    @Autowired
    private ModeratorServiceFacade moderatorServiceFacade;

    @RequestMapping(value = "/conflicts", method = RequestMethod.GET)
    public @ResponseBody List<ConflictDto> getConflicts(){
        return moderatorServiceFacade.getConflicts();
    }

}
