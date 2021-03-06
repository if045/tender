package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.User;
import java.util.List;

public interface UserService {

    List<User> findAll();
    User findUserById(int id);
    User findByModeratorCategoriesId(int categoryId);
    User findByLogin(String login);
    User saveUser(User user);
}
