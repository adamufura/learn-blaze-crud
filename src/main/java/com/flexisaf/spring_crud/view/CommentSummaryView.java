package com.flexisaf.spring_crud.view;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.flexisaf.spring_crud.model.Comment;

@EntityView(Comment.class)
public interface CommentSummaryView {

    @IdMapping
    Long getId();

    String getText();
}