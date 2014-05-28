package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByTender(Integer tenderId);
    List<Comment> findByUser(Integer userId);
}
