package com.greenwich.comp1640.service.abstr;

import com.greenwich.comp1640.dto.request.comment.CreateCommentRequestDto;
import com.greenwich.comp1640.dto.request.comment.UpdateCommentRequestDto;
import com.greenwich.comp1640.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

public interface CommentService {

    ResponseEntity<GeneralResponse<Object>> createComment(CreateCommentRequestDto createCommentRequestDto);

    ResponseEntity<GeneralResponse<Object>> updateComment(Long id, UpdateCommentRequestDto updateCommentRequestDto);

    ResponseEntity<GeneralResponse<Object>> getAllCmtByUser(String username);

    ResponseEntity<GeneralResponse<Object>> getAllCmtByArticle(Long articleId);

    ResponseEntity<GeneralResponse<Object>> getAllCmtByUserAndArticle(String username, Long articleId);
    
}
