package com.flexisaf.spring_crud.service;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViewSetting;
import com.flexisaf.spring_crud.model.Post;
import com.flexisaf.spring_crud.model.User;
import com.flexisaf.spring_crud.repository.PostRepository;
import com.flexisaf.spring_crud.repository.UserRepository;
import com.flexisaf.spring_crud.view.PostView;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CriteriaBuilderFactory builderFactory;

    @Autowired
    private EntityViewManager viewManager;

    @Autowired
    private EntityManager entityManager;

    // Find all posts using Blaze-Persistence
    public List<PostView> findAllPosts() {
        CriteriaBuilder<Post> postCriteriaBuilder = builderFactory.create(entityManager, Post.class, "p");

        return viewManager.applySetting(
                EntityViewSetting.create(PostView.class),
                postCriteriaBuilder
        ).getResultList();
    }

    // Query posts by user ID using Blaze-Persistence
    public List<PostView> findPostsByUserId(Long userId) {
        CriteriaBuilder<Post> postCriteriaBuilder = builderFactory.create(entityManager, Post.class, "p")
                .where("p.user.id").eq(userId);

        return viewManager.applySetting(
                EntityViewSetting.create(PostView.class),
                postCriteriaBuilder
        ).getResultList();
    }

    // Save a new post and return a PostView
    public PostView savePost(Post post, Long userId) {
        // Find the user by ID and associate it with the post
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        post.setUser(user);

        // Save the post entity
        Post savedPost = postRepository.save(post);

        // Convert the saved Post entity to a PostView
        return viewManager.find(entityManager, PostView.class, savedPost.getId());
    }
}
