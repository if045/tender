package com.softserveinc.tender.web;

import com.softserveinc.tender.dto.DealDto;
import com.softserveinc.tender.facade.DealServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/moderator")
public class ModeratorController {
    @Autowired
    private DealServiceFacade dealFacade;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<DealDto> findDeals(@RequestParam(value = "pageNumber",required = true) Integer pageNumber,
                            @RequestParam(value = "pageSize",required = true) Integer pageSize,
                            @RequestParam(value = "orderBy", required = false, defaultValue = "date") String orderBy,
                            @RequestParam(value = "sortDirection", required = false, defaultValue = "DESC") String sortDirection,
                            @RequestParam(value = "searchParam",required = false) String searchParam) {

        Sort.Direction pageSortDirection = Sort.Direction.fromString(sortDirection);

        return dealFacade.findAllDeals(new PageRequest(pageNumber, pageSize, pageSortDirection, orderBy), searchParam);
    }
}
