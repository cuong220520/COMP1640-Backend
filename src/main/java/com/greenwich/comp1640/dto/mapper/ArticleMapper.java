package com.greenwich.comp1640.dto.mapper;

import com.greenwich.comp1640.dto.request.article.CreateArticleRequestDto;
import com.greenwich.comp1640.dto.request.article.UpdateArticleRequestDto;
import com.greenwich.comp1640.dto.response.ArticleResponseDto;
import com.greenwich.comp1640.model.Article;
import com.greenwich.comp1640.model.Campaign;
import com.greenwich.comp1640.model.Faculty;
import com.greenwich.comp1640.model.User;
import com.greenwich.comp1640.util.constant.ArticleStatusConst;

import java.util.ArrayList;
import java.util.List;

public class ArticleMapper {

    private ArticleMapper() {}

    public static ArticleResponseDto toDto(Article article) {
        ArticleResponseDto articleResponseDto = new ArticleResponseDto();
        articleResponseDto.setId(article.getId());
        articleResponseDto.setName(article.getName());
        articleResponseDto.setMessage(article.getMessage());
        articleResponseDto.setImageUrl(article.getImageUrl());
        articleResponseDto.setDocumentUrl(article.getDocumentUrl());
        articleResponseDto.setUserUsername(article.getUser().getUsername());
        articleResponseDto.setFacultyCode(article.getFaculty().getCode());
        articleResponseDto.setCampaignCode(article.getCampaign().getCode());
        articleResponseDto.setStatus(article.getStatus().getValue());

        return articleResponseDto;
    }

    public static List<ArticleResponseDto> toListDto(List<Article> articleList) {
        List<ArticleResponseDto> articleResponseDtoList = new ArrayList<>();

        for (Article article : articleList) {
            articleResponseDtoList.add(toDto(article));
        }

        return articleResponseDtoList;
    }

    public static Article createFromDto(CreateArticleRequestDto createArticleRequestDto,
                                        User user,
                                        Faculty faculty,
                                        Campaign campaign) {
        Article article = new Article();

        article.setName(createArticleRequestDto.getName());
        article.setMessage(createArticleRequestDto.getMessage());
        article.setImageUrl(createArticleRequestDto.getImageUrl());
        article.setDocumentUrl(createArticleRequestDto.getDocumentUrl());
        article.setUser(user);
        article.setFaculty(faculty);
        article.setCampaign(campaign);
        article.setStatus(ArticleStatusConst.PENDING);

        return article;
    }

    public static Article updateFromDto(Article article, UpdateArticleRequestDto updateArticleRequestDto) {
        if (updateArticleRequestDto.getName() != null) {
            article.setName(updateArticleRequestDto.getName());
        }

        if (updateArticleRequestDto.getMessage() != null) {
            article.setMessage(updateArticleRequestDto.getMessage());
        }

        if (updateArticleRequestDto.getImageUrl() != null) {
            article.setImageUrl(updateArticleRequestDto.getImageUrl());
        }

        if (updateArticleRequestDto.getStatus() != null) {
            article.setStatus(updateArticleRequestDto.getStatus());
        }

        if (updateArticleRequestDto.getDocumentUrl() != null) {
            article.setDocumentUrl(updateArticleRequestDto.getDocumentUrl());
        }

        return article;
    }

}
