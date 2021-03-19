package com.greenwich.comp1640.dto.mapper;

import com.greenwich.comp1640.dto.request.comment.CreateCommentRequestDto;
import com.greenwich.comp1640.dto.request.comment.UpdateCommentRequestDto;
import com.greenwich.comp1640.dto.response.CommentResponseDto;
import com.greenwich.comp1640.model.Article;
import com.greenwich.comp1640.model.Comment;
import com.greenwich.comp1640.model.User;

import java.util.ArrayList;
import java.util.List;

public class CommentMapper {

    private CommentMapper() {

    }

    public static CommentResponseDto toDto(Comment comment) {
        CommentResponseDto commentResponseDto = new CommentResponseDto();
        commentResponseDto.setId(comment.getId());
        commentResponseDto.setContent(comment.getContent());
        commentResponseDto.setUsername(comment.getUser().getUsername());
        commentResponseDto.setArticleId(comment.getArticle().getId());

        return commentResponseDto;
    }

    public static List<CommentResponseDto> toListDto(List<Comment> commentList) {
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();

        for (Comment comment : commentList) {
            commentResponseDtoList.add(toDto(comment));
        }

        return commentResponseDtoList;
    }

    public static Comment createFromDto(CreateCommentRequestDto createCommentRequestDto, User user, Article article) {
        Comment comment = new Comment();

        comment.setContent(createCommentRequestDto.getContent());
        comment.setUser(user);
        comment.setArticle(article);

        return comment;
    }

    public static Comment updateFromDto(Comment comment, UpdateCommentRequestDto updateCommentRequestDto) {
        if (updateCommentRequestDto.getContent() != null) {
            comment.setContent(updateCommentRequestDto.getContent());
        }

        return comment;
    }
    
}
