package com.greenwich.comp1640.controller;

import com.greenwich.comp1640.dto.parameter.PagingOptionDto;
import com.greenwich.comp1640.dto.request.article.CreateArticleRequestDto;
import com.greenwich.comp1640.dto.request.article.UpdateArticleRequestDto;
import com.greenwich.comp1640.response.GeneralResponse;
import com.greenwich.comp1640.service.abstr.ArticleService;
import com.greenwich.comp1640.util.constant.ArticleStatusConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<GeneralResponse<Object>> getAllArticle(PagingOptionDto pagingOptionDto, String sort) {
        Pageable pageable = pagingOptionDto.createPageable(pagingOptionDto.getPage(), pagingOptionDto.getLimit(), "-updated_at");

        return articleService.getAllArticles(pageable);
    }

    @GetMapping("/all")
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
    public ResponseEntity<GeneralResponse<Object>> getAllArticlesByUser(@RequestParam("username") String username,
                                                                        PagingOptionDto pagingOptionDto,
                                                                        String sort) {
        Pageable pageable = pagingOptionDto.createPageable(pagingOptionDto.getPage(), pagingOptionDto.getLimit(), "-updated_at");

        return articleService.getAllArticlesByUser(username, pageable);
    }

    @GetMapping(value = "/get-by-faculty")
    public ResponseEntity<GeneralResponse<Object>> getAllArticlesByFaculty(@RequestParam("code") String code,
                                                                           PagingOptionDto pagingOptionDto,
                                                                           String sort) {
        Pageable pageable = pagingOptionDto.createPageable(pagingOptionDto.getPage(), pagingOptionDto.getLimit(), "-updated_at");

        return articleService.getAllArticlesByFacultyCode(code, pageable);
    }

    @GetMapping(value = "/get-by-campaign")
    public ResponseEntity<GeneralResponse<Object>> getAllArticlesByCampaign(@RequestParam("code") String code,
                                                                            PagingOptionDto pagingOptionDto,
                                                                            String sort) {
        Pageable pageable = pagingOptionDto.createPageable(pagingOptionDto.getPage(), pagingOptionDto.getLimit(), "-updated_at");

        return articleService.getAllArticlesByCampaignCode(code, pageable);
    }

    @GetMapping(value = "/get-by-status")
    public ResponseEntity<GeneralResponse<Object>> getAllArticlesByStatus(@RequestParam("status") ArticleStatusConst status,
                                                                          PagingOptionDto pagingOptionDto,
                                                                          String sort) {
        Pageable pageable = pagingOptionDto.createPageable(pagingOptionDto.getPage(), pagingOptionDto.getLimit(), "-updated_at");

        return articleService.getAllArticlesByStatus(status, pageable);
    }

    @GetMapping(value = "/update-status/{id}")
    public ResponseEntity<GeneralResponse<Object>> updateArticleStatus(
            @PathVariable("id") Long id,
            @RequestParam("status") ArticleStatusConst status) {
        return articleService.updateArticleStatus(id, status);
    }

    @GetMapping(value = "/get-by-faculty-status")
    public ResponseEntity<GeneralResponse<Object>> getAllArticlesByFacultyAndStatus(@RequestParam("code") String code,
                                                                                    @RequestParam("status") ArticleStatusConst status,
                                                                                    PagingOptionDto pagingOptionDto,
                                                                                    String sort) {
        Pageable pageable = pagingOptionDto.createPageable(pagingOptionDto.getPage(), pagingOptionDto.getLimit(), "-updated_at");

        return articleService.getAllArticlesByFacultyCodeAndStatus(code, status, pageable);
    }

    @GetMapping(value = "/get-by-faculty-campaign")
    public ResponseEntity<GeneralResponse<Object>> getAllArticlesByFacultyAndCampaign(@RequestParam("faculty_code") String facultyCode,
                                                                                    @RequestParam("campaign_code") String campaignCode,
                                                                                    PagingOptionDto pagingOptionDto,
                                                                                    String sort) {
        Pageable pageable = pagingOptionDto.createPageable(pagingOptionDto.getPage(), pagingOptionDto.getLimit(), "-updated_at");

        return articleService.getAllArticlesByFacultyCodeAndCampaignCode(facultyCode, campaignCode, pageable);
    }

    @GetMapping(value = "/get-by-faculty-user")
    public ResponseEntity<GeneralResponse<Object>> getAllArticlesByFacultyAndUsername(@RequestParam("faculty_code") String facultyCode,
                                                                                    @RequestParam("username") String username,
                                                                                    PagingOptionDto pagingOptionDto,
                                                                                    String sort) {
        Pageable pageable = pagingOptionDto.createPageable(pagingOptionDto.getPage(), pagingOptionDto.getLimit(), "-updated_at");

        return articleService.getAllArticlesByFacultyCodeAndUsername(facultyCode, username, pageable);
    }

    @GetMapping(value = "/get-by-faculty-status-campaign")
    public ResponseEntity<GeneralResponse<Object>> getAllArticlesByFacultyAndStatus(@RequestParam("faculty_code") String facultyCode,
                                                                                    @RequestParam("status") ArticleStatusConst status,
                                                                                    @RequestParam("campaign_code") String campaignCode,
                                                                                    PagingOptionDto pagingOptionDto,
                                                                                    String sort) {
        Pageable pageable = pagingOptionDto.createPageable(pagingOptionDto.getPage(), pagingOptionDto.getLimit(), "-updated_at");

        return articleService.getAllArticlesByFacultyCodeAndStatusAndCampaignCode(facultyCode, status, campaignCode, pageable);
    }

    @GetMapping(value = "/get-by-faculty-status-user")
    public ResponseEntity<GeneralResponse<Object>> getAllArticlesByFacultyAndStatusAndUser(@RequestParam("faculty_code") String facultyCode,
                                                                                    @RequestParam("status") ArticleStatusConst status,
                                                                                    @RequestParam("username") String username,
                                                                                    PagingOptionDto pagingOptionDto,
                                                                                    String sort) {
        Pageable pageable = pagingOptionDto.createPageable(pagingOptionDto.getPage(), pagingOptionDto.getLimit(), "-updated_at");

        return articleService.getAllArticlesByFacultyCodeAndStatusAndUsername(facultyCode, status, username, pageable);
    }

    @GetMapping(value = "/get-by-faculty-campaign-user")
    public ResponseEntity<GeneralResponse<Object>> getAllArticlesByFacultyAndCampaignAndUser(@RequestParam("faculty_code") String facultyCode,
                                                                                    @RequestParam("username") String username,
                                                                                    @RequestParam("campaign_code") String campaignCode,
                                                                                    PagingOptionDto pagingOptionDto,
                                                                                    String sort) {
        Pageable pageable = pagingOptionDto.createPageable(pagingOptionDto.getPage(), pagingOptionDto.getLimit(), "-updated_at");

        return articleService.getAllArticlesByFacultyCodeAndCampaignCodeAndUsername(facultyCode, campaignCode, username, pageable);
    }

    @GetMapping(value = "/get-by-faculty-campaign-user-status")
    public ResponseEntity<GeneralResponse<Object>> getAllArticlesByFacultyAndCampaignAndUserAndStatus(@RequestParam("faculty_code") String facultyCode,
                                                                                             @RequestParam("username") String username,
                                                                                             @RequestParam("campaign_code") String campaignCode,
                                                                                             @RequestParam("status") ArticleStatusConst status,
                                                                                             PagingOptionDto pagingOptionDto,
                                                                                             String sort) {
        Pageable pageable = pagingOptionDto.createPageable(pagingOptionDto.getPage(), pagingOptionDto.getLimit(), "-updated_at");

        return articleService.getAllArticlesByFacultyCodeAndCampaignCodeAndUsernameAndStatus(facultyCode,
                campaignCode, username, status, pageable);
    }

    @GetMapping(value = "/get-all-by-campaign")
    public ResponseEntity<GeneralResponse<Object>> getByCampaignCode(@RequestParam("code") String code) {
        return articleService.getAllArticlesByCampaignCode(code);
    }
}
