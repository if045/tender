package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.Comment;
import com.softserveinc.tender.entity.Tender;
import com.softserveinc.tender.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByTender(Tender tender);
    List<Comment> findByUser(User user);
}
