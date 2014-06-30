package com.softserveinc.tender.web;

import com.softserveinc.tender.dto.DealDto;
import com.softserveinc.tender.dto.ProfileDto;
import com.softserveinc.tender.facade.DealServiceFacade;
import com.softserveinc.tender.facade.ProfileServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/moderator")
public class ModeratorController {
    @Autowired
    private ProfileServiceFacade profileFacade;

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
}
