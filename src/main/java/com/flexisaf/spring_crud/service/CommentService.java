package com.flexisaf.spring_crud.service;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViewSetting;
import com.flexisaf.spring_crud.model.Comment;
import com.flexisaf.spring_crud.repository.CommentRepository;
import com.flexisaf.spring_crud.view.CommentSummaryView;
import com.flexisaf.spring_crud.view.CommentView;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CriteriaBuilderFactory builderFactory;

    @Autowired
    private EntityViewManager viewManager;

    @Autowired
    private EntityManager entityManager;

    // Find all comments using Blaze-Persistence
    public List<CommentView> findAllComments() {
        CriteriaBuilder<Comment> commentCriteriaBuilder = builderFactory.create(entityManager, Comment.class, "c");

        return viewManager.applySetting(
                EntityViewSetting.create(CommentView.class),
                commentCriteriaBuilder
        ).getResultList();
    }

    // Query comments by post ID using Blaze-Persistence
    public List<CommentView> findCommentsByPostId(Long postId) {
        CriteriaBuilder<Comment> commentCriteriaBuilder = builderFactory.create(entityManager, Comment.class, "c")
                .where("c.post.id").eq(postId);

        return viewManager.applySetting(
                EntityViewSetting.create(CommentView.class),
                commentCriteriaBuilder
        ).getResultList();
    }

    // Other CRUD operations using standard JPA repository
    public CommentSummaryView saveComment(Comment comment) {
        Comment savedComment = commentRepository.save(comment);
        return viewManager.applySetting(
                EntityViewSetting.create(CommentSummaryView.class),
                builderFactory.create(entityManager, Comment.class, "c").where("c.id").eq(savedComment.getId())
        ).getSingleResult();
    }
}
