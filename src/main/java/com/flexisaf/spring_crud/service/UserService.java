package com.flexisaf.spring_crud.service;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViewSetting;
import com.flexisaf.spring_crud.model.User;
import com.flexisaf.spring_crud.repository.UserRepository;
import com.flexisaf.spring_crud.view.UserView;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CriteriaBuilderFactory builderFactory;

    @Autowired
    private EntityViewManager viewManager;

    @Autowired
    private EntityManager entityManager;

    // Find all users using Blaze-Persistence
    public List<UserView> findAllUsers() {
        // Create a criteria query for User
        CriteriaBuilder<User> userCriteriaBuilder = builderFactory.create(entityManager, User.class, "u");

        // Apply the entity view for UserView
        return viewManager.applySetting(
                EntityViewSetting.create(UserView.class),
                userCriteriaBuilder
        ).getResultList();
    }

    // Example: Query users by name
    public List<UserView> findUsersByName(String name) {
        // Create criteria query with a filter on the name
        CriteriaBuilder<User> userCriteriaBuilder = builderFactory.create(entityManager, User.class, "u")
                .where("u.name").eq(name);

        // Apply the entity view for UserView
        return viewManager.applySetting(
                EntityViewSetting.create(UserView.class),
                userCriteriaBuilder
        ).getResultList();
    }

    // Other CRUD operations using standard JPA repository
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
