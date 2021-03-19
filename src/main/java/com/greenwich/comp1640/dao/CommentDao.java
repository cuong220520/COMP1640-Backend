package com.greenwich.comp1640.dao;

import com.greenwich.comp1640.model.Comment;
import com.greenwich.comp1640.repository.readonly.CommentRORepository;
import com.greenwich.comp1640.repository.readwrite.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDao extends BaseDao<Comment, Long> {

    private final CommentRepository commentRepository;
    private final CommentRORepository commentRORepository;

    @Autowired
    public CommentDao(CommentRepository commentRepository, CommentRORepository commentRORepository) {
        super(commentRepository, commentRORepository);
        this.commentRepository = commentRepository;
        this.commentRORepository = commentRORepository;
    }

    public List<Comment> findByUser(String username) {
        return this.commentRORepository.findByUserUsername(username);
    }

    public Comment saveComment(Comment comment) {
        return this.commentRepository.save(comment);
    }

    public List<Comment> findByArticle(Long articleId) {
        return this.commentRORepository.findByArticleId(articleId);
    }

    public List<Comment> findByArticleAndUser(String username, Long articleId) {
        return this.commentRORepository.findByUserUsernameAndArticleId(username, articleId);
    }
    
}
