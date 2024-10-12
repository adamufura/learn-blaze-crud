package com.flexisaf.spring_crud.repository;

import com.flexisaf.spring_crud.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Add any custom queries if needed
}
