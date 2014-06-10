package com.softserveinc.tender.web;

import com.softserveinc.tender.dto.DealDto;
import com.softserveinc.tender.dto.FeedbackSaveDto;
import com.softserveinc.tender.facade.DealServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
      public @ResponseBody List<DealDto> findDeals() {
        return dealFacade.findAllDeals();
    }

    @RequestMapping(value = "/{id}/feedback", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    FeedbackSaveDto addFeedback(@RequestBody FeedbackSaveDto feedbackSaveDto) {
        return dealFacade.saveFeedback(feedbackSaveDto);
    }
}
