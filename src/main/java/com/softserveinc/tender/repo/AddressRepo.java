package com.softserveinc.tender.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.softserveinc.tender.entity.Address;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer> {

}
