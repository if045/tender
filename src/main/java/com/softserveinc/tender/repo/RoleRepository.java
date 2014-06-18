package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("select r from Role r where r.name like 'seller' or r.name like 'customer'")
    List<Role> findUsersRoles();
}
