package com.softserveinc.tender.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.softserveinc.tender.entity.Company;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Integer> {
}
