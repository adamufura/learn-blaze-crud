package com.flexisaf.spring_crud.view;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.flexisaf.spring_crud.model.User;

import java.util.List;

@EntityView(User.class)
public interface UserView {

    @IdMapping
    Long getId();

    String getName();

    String getEmail();

    List<PostSummaryView> getPosts();
}