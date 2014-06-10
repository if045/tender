package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Item;
import com.softserveinc.tender.repo.TenderFilter;

import java.util.List;

public interface ItemService {

    List<Item> findAll();
    List<Item> findAllItemsByTenders(TenderFilter tenderFilter);
    List<Item> findItemsByCategoryAndType(TenderFilter tenderFilter);
    Item findByName(String name);
    Item save(Item item);
}
