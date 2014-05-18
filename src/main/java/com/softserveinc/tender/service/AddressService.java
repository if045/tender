package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.*;
import java.util.List;

public interface AddressService {
    public Address save(Address address);
    public Address findById(int id);
    public List<Address> findAll();
    public Address update(Address address);
    public void deleteById(int id);
}
