package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.Address;
import com.softserveinc.tender.service.*;
import com.softserveinc.tender.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepo addressRepo;

    @Override
    public Address save(Address address) {
        return addressRepo.save(address);
    }

    @Override
    public Address findById(int id) {
        return addressRepo.findOne(id);
    }

    @Override
    public List<Address> findAll() {
        return addressRepo.findAll();
    }

    @Override
    public Address update(Address address) {
        return addressRepo.saveAndFlush(address);
    }

    @Override
    public void deleteById(int addressID) {
        addressRepo.delete(addressID);
    }
}
