package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findByTender(Integer tenderId);
    List<Comment> findByUser(Integer userId);
    Comment findById(Integer id);
    void save(Comment comment);
    void deleteById(Integer id);
}
