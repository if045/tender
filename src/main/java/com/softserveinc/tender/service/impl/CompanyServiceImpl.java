package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.Company;
import com.softserveinc.tender.repo.CompanyRepo;
import com.softserveinc.tender.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepo companyRepo;

    @Override
    public Company save(Company address) {
        return companyRepo.save(address);
    }

    @Override
    public Company findById(int id) {
        return companyRepo.findOne(id);
    }

    @Override
    public List<Company> findAll() {
        return companyRepo.findAll();
    }

    @Override
    public void deleteById(int id) {
        companyRepo.delete(id);
    }
}
