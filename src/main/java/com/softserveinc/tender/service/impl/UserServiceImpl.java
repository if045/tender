package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.User;
import com.softserveinc.tender.repo.UserRepository;
import com.softserveinc.tender.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(int id) {
        return userRepository.findOne(id);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
