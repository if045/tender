package com.softserveinc.tender.web;

import com.softserveinc.tender.dto.ItemDto;
import com.softserveinc.tender.facade.ItemServiceFacade;
import com.softserveinc.tender.repo.TenderFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemServiceFacade itemServiceFacade;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody
    List<ItemDto> findItems(
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "type", required = false) Character type) {
        return itemServiceFacade.findItems(new TenderFilter(category,type));
    }
}
