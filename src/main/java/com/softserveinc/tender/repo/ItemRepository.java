package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Integer>{
    Item findByName(String name);
}
