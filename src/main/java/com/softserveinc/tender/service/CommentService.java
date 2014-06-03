package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Comment;
import com.softserveinc.tender.entity.Tender;
import com.softserveinc.tender.entity.User;

import java.util.List;

public interface CommentService {

    List<Comment> findByTender(Tender tender);
    List<Comment> findByUser(User user);
    Comment findById(Integer id);
    void save(Comment comment);
    void deleteById(Integer id);
}
