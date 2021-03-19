package com.greenwich.comp1640.service.impl;

import com.greenwich.comp1640.dao.ArticleDao;
import com.greenwich.comp1640.dao.CommentDao;
import com.greenwich.comp1640.dao.UserDao;
import com.greenwich.comp1640.dto.mapper.CommentMapper;
import com.greenwich.comp1640.dto.request.comment.CreateCommentRequestDto;
import com.greenwich.comp1640.dto.request.comment.UpdateCommentRequestDto;
import com.greenwich.comp1640.model.Article;
import com.greenwich.comp1640.model.Comment;
import com.greenwich.comp1640.model.User;
import com.greenwich.comp1640.response.GeneralResponse;
import com.greenwich.comp1640.response.ResponseFactory;
import com.greenwich.comp1640.service.abstr.CommentService;
import com.greenwich.comp1640.util.constant.ResponseStatusCodeConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Autowired
    UserDao userDao;

    @Autowired
    ArticleDao articleDao;

    @Autowired
    CommentDao commentDao;

    @Autowired
    ResponseFactory responseFactory;

    @Override
    public ResponseEntity<GeneralResponse<Object>> createComment(CreateCommentRequestDto createCommentRequestDto) {
        User user = userDao.findByUsername(createCommentRequestDto.getUsername());

        if (user == null) {
            log.error(String.format("Can not find user with username: %s", createCommentRequestDto.getUsername()));
            return responseFactory.fail(String.format("Can not find user with username: %s", createCommentRequestDto.getUsername()),
                    ResponseStatusCodeConst.DATA_NOT_FOUND_ERROR,
                    null);
        }

        Optional<Article> article = articleDao.findById(createCommentRequestDto.getArticleId());

        if (!article.isPresent()) {
            log.error(String.format("Can not find article with article id: %d", createCommentRequestDto.getArticleId()));
            return responseFactory.fail(String.format("Can not find article with article id: %d", createCommentRequestDto.getArticleId()),
                    ResponseStatusCodeConst.DATA_NOT_FOUND_ERROR,
                    null);
        }

        Comment comment = CommentMapper.createFromDto(createCommentRequestDto, user, article.get());

        commentDao.saveComment(comment);

        return responseFactory.success(CommentMapper.toDto(comment));
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> updateComment(Long id, UpdateCommentRequestDto updateCommentRequestDto) {
        Optional<Comment> comment = commentDao.findById(id);

        if (!comment.isPresent()) {
            log.error(String.format("Can not find comment with comment id: %d", id));
            return responseFactory.fail(String.format("Can not find comment with comment id: %d", id),
                    ResponseStatusCodeConst.DATA_NOT_FOUND_ERROR,
                    null);
        }

        Comment updatedComment = CommentMapper.updateFromDto(comment.get(), updateCommentRequestDto);

        commentDao.saveComment(updatedComment);

        return responseFactory.success(CommentMapper.toDto(updatedComment));
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> getAllCmtByUser(String username) {
        User user = userDao.findByUsername(username);

        if (user == null) {
            log.error(String.format("Can not find user with username: %s", username));
            return responseFactory.fail(String.format("Can not find user with username: %s", username),
                    ResponseStatusCodeConst.DATA_NOT_FOUND_ERROR,
                    null);
        }

        List<Comment> commentList = commentDao.findByUser(username);

        return responseFactory.success(CommentMapper.toListDto(commentList));
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> getAllCmtByArticle(Long articleId) {
        Optional<Article> article = articleDao.findById(articleId);

        if (!article.isPresent()) {
            log.error(String.format("Can not find article with article id: %d", articleId));
            return responseFactory.fail(String.format("Can not find article with article id: %d", articleId),
                    ResponseStatusCodeConst.DATA_NOT_FOUND_ERROR,
                    null);
        }

        List<Comment> commentList = commentDao.findByArticle(articleId);

        return responseFactory.success(CommentMapper.toListDto(commentList));
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> getAllCmtByUserAndArticle(String username, Long articleId) {
        User user = userDao.findByUsername(username);

        if (user == null) {
            log.error(String.format("Can not find user with username: %s", username));
            return responseFactory.fail(String.format("Can not find user with username: %s", username),
                    ResponseStatusCodeConst.DATA_NOT_FOUND_ERROR,
                    null);
        }

        Optional<Article> article = articleDao.findById(articleId);

        if (!article.isPresent()) {
            log.error(String.format("Can not find article with article id: %d", articleId));
            return responseFactory.fail(String.format("Can not find article with article id: %d", articleId),
                    ResponseStatusCodeConst.DATA_NOT_FOUND_ERROR,
                    null);
        }

        List<Comment> commentList = commentDao.findByArticleAndUser(username, articleId);

        return responseFactory.success(CommentMapper.toListDto(commentList));
    }
}
