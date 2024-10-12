package com.flexisaf.spring_crud.view;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.flexisaf.spring_crud.model.Post;

@EntityView(Post.class)
public interface PostSummaryView {

    @IdMapping
    Long getId();

    String getTitle();

    String getContent();
}