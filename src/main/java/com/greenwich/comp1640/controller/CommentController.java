package com.greenwich.comp1640.controller;

import com.greenwich.comp1640.dto.request.comment.CreateCommentRequestDto;
import com.greenwich.comp1640.dto.request.comment.UpdateCommentRequestDto;
import com.greenwich.comp1640.response.GeneralResponse;
import com.greenwich.comp1640.service.abstr.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping
    public ResponseEntity<GeneralResponse<Object>> createComment(@RequestBody @Valid CreateCommentRequestDto createCommentRequestDto) {
        return commentService.createComment(createCommentRequestDto);
    }

    @GetMapping(value = "/get-by-user")
    public ResponseEntity<GeneralResponse<Object>> getCommentByUser(@RequestParam("username") String username) {
        return commentService.getAllCmtByUser(username);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<GeneralResponse<Object>> updateComment(
            @PathVariable("id") Long id,
            @Valid @RequestBody UpdateCommentRequestDto updateCommentRequestDto) {
        return commentService.updateComment(id, updateCommentRequestDto);
    }

    @GetMapping(value = "/get-by-article")
    public ResponseEntity<GeneralResponse<Object>> getComment(@RequestParam("article_id") Long articleId) {
        return commentService.getAllCmtByArticle(articleId);
    }

    @GetMapping(value = "/get-by-user-article")
    public ResponseEntity<GeneralResponse<Object>> getCurrentComment(@RequestParam("username") String username,
                                                                     @RequestParam("article_id") Long articleId) {
        return commentService.getAllCmtByUserAndArticle(username, articleId);
    }
    
}
