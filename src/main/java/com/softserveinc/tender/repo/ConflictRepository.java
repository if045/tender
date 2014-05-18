package com.softserveinc.tender.repo;


import com.softserveinc.tender.entity.Conflict;
import com.softserveinc.tender.entity.ConflictStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConflictRepository extends JpaRepository<Conflict, Integer> {

}
