package com.greenwich.comp1640.controller;

import com.greenwich.comp1640.dto.request.article.CreateArticleRequestDto;
import com.greenwich.comp1640.dto.request.article.UpdateArticleRequestDto;
import com.greenwich.comp1640.response.GeneralResponse;
import com.greenwich.comp1640.service.abstr.ArticleService;
import com.greenwich.comp1640.util.constant.ArticleStatusConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @PostMapping
    public ResponseEntity<GeneralResponse<Object>> createArticle(@RequestBody @Valid CreateArticleRequestDto createArticleRequestDto) {
        return articleService.createArticle(createArticleRequestDto);
    }

    @GetMapping
    public ResponseEntity<GeneralResponse<Object>> getAllArticle() {
        return articleService.getAllArticles();
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<GeneralResponse<Object>> updateArticle(
            @PathVariable("id") Long id,
            @Valid @RequestBody UpdateArticleRequestDto updateArticleRequestDto) {
        return articleService.updateArticle(id, updateArticleRequestDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GeneralResponse<Object>> getArticle(@PathVariable("id") Long id) {
        return articleService.getArticle(id);
    }

    @GetMapping(value = "/get-by-user")
    public ResponseEntity<GeneralResponse<Object>> getAllArticlesByUser(@RequestParam("username") String username) {
        return articleService.getAllArticlesByUser(username);
    }

    @GetMapping(value = "/get-by-faculty")
    public ResponseEntity<GeneralResponse<Object>> getAllArticlesByFaculty(@RequestParam("code") String code) {
        return articleService.getAllArticlesByFacultyCode(code);
    }

    @GetMapping(value = "/get-by-campaign")
    public ResponseEntity<GeneralResponse<Object>> getAllArticlesByCampaign(@RequestParam("code") String code) {
        return articleService.getAllArticlesByCampaignCode(code);
    }

}
