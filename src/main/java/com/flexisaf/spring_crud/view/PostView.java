package com.flexisaf.spring_crud.view;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.flexisaf.spring_crud.model.Post;

import java.util.List;

@EntityView(Post.class)
public interface PostView {

    @IdMapping
    Long getId();

    String getTitle();  // Adjust these fields based on your Post entity

    String getContent();

    List<CommentView> getComments();  // Include comments

    UserSummaryView getUser(); // Use a summary view for User to avoid recursion
}
