package com.softserveinc.tender.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.softserveinc.tender.entity.Location;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepo extends JpaRepository<Location, Integer> {
}
