package com.softserveinc.tender.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.softserveinc.tender.entity.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LocationRepo extends JpaRepository<Location, Integer> {
    @Query("select distinct location from Tender tender inner join tender.locations location ")
    List<Location> getTendersLocation();

    @Query("select distinct l from Location l where l.id in (:locations)")
    List<Location> getLocationsByIds(@Param("locations") List<Integer> locations);
}
