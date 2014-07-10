package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Company;
import java.util.List;

public interface CompanyService {

    public Company save(Company company);
    public Company findById(int id);
    public List<Company> findAll();
    public void deleteById(int id);
}
