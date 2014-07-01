package com.softserveinc.tender.web;

import com.softserveinc.tender.dto.ProfileDto;
import com.softserveinc.tender.facade.ProfileServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.softserveinc.tender.dto.ConflictDto;
import com.softserveinc.tender.facade.ModeratorServiceFacade;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/moderator")
public class ModeratorController {
    @Autowired
    private ProfileServiceFacade profileFacade;

    @Autowired
    private ModeratorServiceFacade moderatorServiceFacade;

    @PreAuthorize("hasRole('MODERATOR')")
    @RequestMapping(value = "/profiles", method = RequestMethod.GET)
    public @ResponseBody List<ProfileDto> findUsersProfiles() {
        return profileFacade.findAllProfiles();
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @RequestMapping(value = "/profilestatus/{userId}", method = RequestMethod.POST)
    public @ResponseBody ProfileDto updateUserProfileStatus(@PathVariable("userId") Integer userId,
                                                            @RequestParam("statusName") String statusName) {
        return profileFacade.updateProfileStatus(userId, statusName);
    }

    @RequestMapping(value = "/conflicts", method = RequestMethod.GET)
    public @ResponseBody List<ConflictDto> getConflicts(){
        return moderatorServiceFacade.getConflicts();
    }
}
