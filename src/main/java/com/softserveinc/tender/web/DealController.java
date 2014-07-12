package com.softserveinc.tender.web;

import com.softserveinc.tender.dto.ConflictDto;
import com.softserveinc.tender.dto.ConflictSaveDto;
import com.softserveinc.tender.dto.DealDto;
import com.softserveinc.tender.dto.FeedbackDto;
import com.softserveinc.tender.dto.FeedbackSaveDto;
import com.softserveinc.tender.facade.DealServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/deals")
public class DealController {
    @Autowired
    private DealServiceFacade dealFacade;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<DealDto> findDeals(@RequestParam(value = "pageNumber",required = true) Integer pageNumber,
                                                 @RequestParam(value = "pageSize",required = true) Integer pageSize,
                                                 @RequestParam(value = "orderBy", required = false, defaultValue = "date") String orderBy,
                                                 @RequestParam(value = "sortDirection", required = false, defaultValue = "DESC") String sortDirection,
                                                 @RequestParam(value = "searchParam",required = false) String searchParam,
                                                 @RequestParam(value = "userRole",required = false) String userRole) {

        Sort.Direction pageSortDirection = Sort.Direction.fromString(sortDirection);

        return dealFacade.findAllDeals(new PageRequest(pageNumber, pageSize, pageSortDirection, orderBy), searchParam, userRole);
    }

    @RequestMapping(value = "/number", method = RequestMethod.GET)
    public @ResponseBody Long getDealsNumber(@RequestParam(value = "userRole",required = false) String userRole,
                                             @RequestParam(value = "searchParam",required = false) String searchParam) {
         return dealFacade.getDealsNumber(userRole, searchParam);
    }

    @RequestMapping(value = "/newdeals", method = RequestMethod.GET)
    public @ResponseBody Long getNewDealsNumber(@RequestParam(value = "userRole",required = false) String userRole,
                                                @RequestParam(value = "searchParam",required = false) String searchParam) {
        return dealFacade.getNewDealsNumber(userRole, searchParam);
    }

    @RequestMapping(value = "/mydealsdate", method = RequestMethod.PUT)
    public @ResponseBody void updateMyDealsDate() {
        dealFacade.updateMyDealsDate();
    }

    @PreAuthorize("hasAnyRole('CUSTOMER','SELLER')")
    @RequestMapping(value = "/{dealId}", method = RequestMethod.PUT)
    public @ResponseBody void updateDealWithStatus(@PathVariable("dealId") Integer dealId,
                                                     @RequestParam("statusName") String statusName) {
        dealFacade.updateDealWithStatus(dealId, statusName);
    }

    @RequestMapping(value = "/{id}/conflicts", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    ConflictDto addConflict(@RequestBody ConflictSaveDto conflictSaveDto) {
        return dealFacade.saveConflict(conflictSaveDto);
    }

    @RequestMapping(value = "/{id}/feedbacks", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    FeedbackDto addFeedback(@RequestBody FeedbackSaveDto feedbackSaveDto) {
        return dealFacade.saveFeedback(feedbackSaveDto);
    }
}
