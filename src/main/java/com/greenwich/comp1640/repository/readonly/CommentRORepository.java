package com.greenwich.comp1640.repository.readonly;

import com.greenwich.comp1640.config.datasource.ReadOnlyRepository;
import com.greenwich.comp1640.model.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ReadOnlyRepository
public interface CommentRORepository extends GenericRORepository<Comment, Long> {

    List<Comment> findByUserUsername(String username);

    List<Comment> findByArticleId(Long articleId);

    List<Comment> findByUserUsernameAndArticleId(String username, Long articleId);

}
