package com.flexisaf.spring_crud.view;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.flexisaf.spring_crud.model.User;

@EntityView(User.class)
public interface UserSummaryView {

    @IdMapping
    Long getId();

    String getName();

    String getEmail();
}