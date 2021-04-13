package com.greenwich.comp1640.service.abstr;

import com.greenwich.comp1640.dto.request.article.CreateArticleRequestDto;
import com.greenwich.comp1640.dto.request.article.UpdateArticleRequestDto;
import com.greenwich.comp1640.response.GeneralResponse;
import com.greenwich.comp1640.util.constant.ArticleStatusConst;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ArticleService {

    ResponseEntity<GeneralResponse<Object>> getAllArticles(Pageable pageable);

    ResponseEntity<GeneralResponse<Object>> getAllArticles();

    ResponseEntity<GeneralResponse<Object>> createArticle(CreateArticleRequestDto createArticleRequestDto);

    ResponseEntity<GeneralResponse<Object>> updateArticle(Long id, UpdateArticleRequestDto updateArticleRequestDto);

    ResponseEntity<GeneralResponse<Object>> updateArticleStatus(Long id, ArticleStatusConst status);

    ResponseEntity<GeneralResponse<Object>> getArticle(Long id);

    ResponseEntity<GeneralResponse<Object>> getAllArticlesByStatus(ArticleStatusConst status, Pageable pageable);

    ResponseEntity<GeneralResponse<Object>> getAllArticlesByUser(String username, Pageable pageable);

    ResponseEntity<GeneralResponse<Object>> getAllArticlesByFacultyCode(String code, Pageable pageable);

    ResponseEntity<GeneralResponse<Object>> getAllArticlesByCampaignCode(String code);

    ResponseEntity<GeneralResponse<Object>> getAllArticlesByCampaignCode(String code, Pageable pageable);

    ResponseEntity<GeneralResponse<Object>> getAllArticlesByFacultyCodeAndStatus(String code, ArticleStatusConst status, Pageable pageable);

    ResponseEntity<GeneralResponse<Object>> getAllArticlesByFacultyCodeAndCampaignCode(String facultyCode, String campaignCode, Pageable pageable);

    ResponseEntity<GeneralResponse<Object>> getAllArticlesByFacultyCodeAndUsername(String facultyCode, String username, Pageable pageable);

    ResponseEntity<GeneralResponse<Object>> getAllArticlesByFacultyCodeAndStatusAndCampaignCode(String facultyCode,
                                                                                                ArticleStatusConst status,
                                                                                                String campaignCode,
                                                                                                Pageable pageable);

    ResponseEntity<GeneralResponse<Object>> getAllArticlesByFacultyCodeAndStatusAndUsername(String facultyCode,
                                                                                                ArticleStatusConst status,
                                                                                                String username,
                                                                                                Pageable pageable);

    ResponseEntity<GeneralResponse<Object>> getAllArticlesByFacultyCodeAndCampaignCodeAndUsername(String facultyCode,
                                                                                                String campaignCode,
                                                                                                String username,
                                                                                                Pageable pageable);

    ResponseEntity<GeneralResponse<Object>> getAllArticlesByFacultyCodeAndCampaignCodeAndUsernameAndStatus(String facultyCode,
                                                                                                  String campaignCode,
                                                                                                  String username,
                                                                                                  ArticleStatusConst status,
                                                                                                  Pageable pageable);
}
