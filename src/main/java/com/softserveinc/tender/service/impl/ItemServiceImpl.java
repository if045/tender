package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.Item;
import com.softserveinc.tender.repo.ItemRepository;
import com.softserveinc.tender.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    public List<Item> findAll(){
        return itemRepository.findAll();
    }
}
