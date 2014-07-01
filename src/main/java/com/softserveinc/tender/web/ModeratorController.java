package com.softserveinc.tender.web;

import com.softserveinc.tender.dto.ProfileDto;
import com.softserveinc.tender.dto.ProfilesNumberDto;
import com.softserveinc.tender.facade.ProfileServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public @ResponseBody List<ProfileDto> findUsersProfiles(@RequestParam(value = "pageNumber",required = true) Integer pageNumber,
                                                            @RequestParam(value = "pageSize",required = true) Integer pageSize,
                                                            @RequestParam(value = "orderBy", required = false, defaultValue = "user.login") String orderBy,
                                                            @RequestParam(value = "sortDirection", required = false, defaultValue = "DESC") String sortDirection,
                                                            @RequestParam(value = "searchParam",required = false) String searchParam) {

        Sort.Direction pageSortDirection = Sort.Direction.fromString(sortDirection);

        return profileFacade.findAllProfiles(new PageRequest(pageNumber, pageSize, pageSortDirection, orderBy), searchParam);
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @RequestMapping(value = "/profilesnumber", method = RequestMethod.GET)
    public @ResponseBody ProfilesNumberDto getDealsNumber(@RequestParam(value = "searchParam",required = false) String searchParam) {
        return profileFacade.getProfilesNumber(searchParam);
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
