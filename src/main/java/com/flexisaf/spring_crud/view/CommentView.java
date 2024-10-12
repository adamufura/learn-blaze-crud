package com.flexisaf.spring_crud.view;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.flexisaf.spring_crud.model.Comment;

@EntityView(Comment.class)
public interface CommentView {

    @IdMapping
    Long getId();

    String getText();

    PostSummaryView getPost(); // Optional to show a summary of the post

    UserSummaryView getUser(); // Include a summary of the user who commented
}
