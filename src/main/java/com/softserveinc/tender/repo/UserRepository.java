package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByModeratorCategoriesId(int categoryId);
}
