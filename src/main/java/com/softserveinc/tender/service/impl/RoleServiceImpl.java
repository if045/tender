package com.softserveinc.tender.service.impl;


import com.softserveinc.tender.entity.Role;
import com.softserveinc.tender.repo.RoleRepository;
import com.softserveinc.tender.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {



    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findRoleById(int id) {return roleRepository.findOne(id); }

    @Override
    public void saveRole (Role role) {
        roleRepository.save(deal);
    }
}
