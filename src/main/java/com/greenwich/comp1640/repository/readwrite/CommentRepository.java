package com.greenwich.comp1640.repository.readwrite;

import com.greenwich.comp1640.model.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends GenericRepository<Comment, Long> {
}
