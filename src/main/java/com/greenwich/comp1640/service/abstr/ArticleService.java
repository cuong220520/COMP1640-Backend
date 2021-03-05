package com.greenwich.comp1640.service.abstr;

import com.greenwich.comp1640.dto.request.article.CreateArticleRequestDto;
import com.greenwich.comp1640.dto.request.article.UpdateArticleRequestDto;
import com.greenwich.comp1640.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

public interface ArticleService {

    ResponseEntity<GeneralResponse<Object>> getAllArticles();

    ResponseEntity<GeneralResponse<Object>> createArticle(CreateArticleRequestDto createArticleRequestDto);

    ResponseEntity<GeneralResponse<Object>> updateArticle(Long id, UpdateArticleRequestDto updateArticleRequestDto);

    ResponseEntity<GeneralResponse<Object>> getArticle(Long id);

    ResponseEntity<GeneralResponse<Object>> getAllArticlesByUser(String username);

    ResponseEntity<GeneralResponse<Object>> getAllArticlesByFacultyCode(String code);

    ResponseEntity<GeneralResponse<Object>> getAllArticlesByCampaignCode(String code);

}
