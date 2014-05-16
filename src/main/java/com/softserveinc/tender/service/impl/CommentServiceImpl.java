package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.Comment;
import com.softserveinc.tender.entity.User;
import com.softserveinc.tender.repo.CommentRepository;
import com.softserveinc.tender.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> findByTender() {
        return commentRepository.findByTender();
    }

    @Override
    public List<User> findByUser() {
        return commentRepository.findByUser();
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
