package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.Conflict;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConflictRepository extends JpaRepository<Conflict, Integer> {

    List<Conflict> findByModeratorId(Integer id);
}
