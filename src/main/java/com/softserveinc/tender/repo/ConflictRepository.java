package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.Conflict;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConflictRepository extends JpaRepository<Conflict, Integer> {

    @Query("select c from Conflict c where c.moderator.id = :moderatorId" +
            " and (1 = :searchFlag or c.bid.proposal.tender.title like :tenderTitle)")
    List<Conflict> findConflicts(@Param("moderatorId") Integer id,
                                 @Param("tenderTitle") String title,
                                 @Param("searchFlag") Integer searchFlag,
                                 Pageable pageable);

    @Query("select count(distinct c) from Conflict c where c.moderator.id = :moderatorId" +
            " and (1 = :searchFlag or c.bid.proposal.tender.title like :tenderTitle)")
    Long getConflictsNumber(@Param("moderatorId") Integer id,
                            @Param("tenderTitle") String title,
                            @Param("searchFlag") Integer searchFlag);
}
