package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Item;
import com.softserveinc.tender.repo.TenderFilter;

import java.util.List;

public interface ItemService {

    List<Item> findAll();
    List<Item> findAllItemsByTenders(TenderFilter tenderFilter);
    List<Item> findByCategoryId(Integer id);
    List<Item> findByCategoryIdAndType(Integer id, Character type);
}
