package com.greenwich.comp1640.dao;

import com.greenwich.comp1640.model.Article;
import com.greenwich.comp1640.repository.readonly.ArticleRORepository;
import com.greenwich.comp1640.repository.readwrite.ArticleRepository;
import com.greenwich.comp1640.util.constant.ArticleStatusConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ArticleDao extends BaseDao<Article, Long> {
    private final ArticleRepository articleRepository;
    private final ArticleRORepository articleRORepository;

    @Autowired
    public ArticleDao(ArticleRepository articleRepository, ArticleRORepository articleRORepository) {
        super(articleRepository, articleRORepository);
        this.articleRepository = articleRepository;
        this.articleRORepository = articleRORepository;
    }

    public Article saveArticle(Article article) {
        return this.articleRepository.save(article);
    }

    public Page<Article> findAllArticle(Pageable pageable) {
        return this.articleRORepository.findAll(pageable);
    }

    public Page<Article> findByUsername(String username, Pageable pageable) {
        return this.articleRORepository.findByUserUsername(username, pageable);
    }

    public Optional<Article> findById(Long id) {
        return this.articleRORepository.findById(id);
    }

    public Page<Article> findByFacultyCode(String code, Pageable pageable) {
        return this.articleRORepository.findByFacultyCode(code, pageable);
    }

    public Page<Article> findByCampaignCode(String code, Pageable pageable) {
        return this.articleRORepository.findByCampaignCode(code, pageable);
    }

    public Page<Article> findByStatus(ArticleStatusConst status, Pageable pageable) {
        return this.articleRORepository.findByStatus(status, pageable);
    }

    public Page<Article> findByFacultyAndStatus(String code, ArticleStatusConst status, Pageable pageable) {
        return this.articleRORepository.findByFacultyCodeAndStatus(code, status, pageable);
    }

    public Page<Article> findByFacultyAndCampaign(String facultyCode, String campaignCode, Pageable pageable) {
        return this.articleRORepository.findByFacultyCodeAndCampaignCode(facultyCode, campaignCode, pageable);
    }

    public Page<Article> findByFacultyAndUsername(String facultyCode, String username, Pageable pageable) {
        return this.articleRORepository.findByFacultyCodeAndUserUsername(facultyCode, username, pageable);
    }

    public Page<Article> findByFacultyAndStatusAndCampaign(String facultyCode,
                                                           ArticleStatusConst status,
                                                           String campaignCode,
                                                           Pageable pageable) {
        return this.articleRORepository.findByFacultyCodeAndStatusAndCampaignCode(facultyCode, status, campaignCode, pageable);
    }

    public Page<Article> findByFacultyAndStatusAndUsername(String facultyCode,
                                                           ArticleStatusConst status,
                                                           String username,
                                                           Pageable pageable) {
        return this.articleRORepository.findByFacultyCodeAndStatusAndUserUsername(facultyCode, status, username, pageable);
    }

    public Page<Article> findByFacultyAndCampaignAndUsername(String facultyCode,
                                                           String campaignCode,
                                                           String username,
                                                           Pageable pageable) {
        return this.articleRORepository.findByFacultyCodeAndCampaignCodeAndUserUsername(facultyCode, campaignCode, username, pageable);
    }

    public Page<Article> findByFacultyAndCampaignAndUsernameAndStatus(String facultyCode,
                                                             String campaignCode,
                                                             String username,
                                                             ArticleStatusConst status,
                                                             Pageable pageable) {
        return this.articleRORepository.findByFacultyCodeAndCampaignCodeAndUserUsernameAndStatus(facultyCode, campaignCode, username, status, pageable);
    }

}
