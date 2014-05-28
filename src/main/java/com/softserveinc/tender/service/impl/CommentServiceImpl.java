package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.Comment;
import com.softserveinc.tender.repo.CommentRepository;
import com.softserveinc.tender.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> findByTender(Integer tenderId) {
        return commentRepository.findByTender(tenderId);
    }

    @Override
    public List<Comment> findByUser(Integer userId) {
        return commentRepository.findByUser(userId);
    }

    @Override
    public Comment findById(Integer id) {
        return commentRepository.findOne(id);
    }

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void deleteById(Integer id) {
        commentRepository.delete(id);
    }
}
