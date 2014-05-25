package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Item;


import java.util.List;

public interface ItemService {
    List<Item> findAll();
    List<Item> findAllItemsByTenders();
}
