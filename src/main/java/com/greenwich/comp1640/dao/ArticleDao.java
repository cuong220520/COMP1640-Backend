package com.greenwich.comp1640.dao;

import com.greenwich.comp1640.model.Article;
import com.greenwich.comp1640.repository.readonly.ArticleRORepository;
import com.greenwich.comp1640.repository.readwrite.ArticleRepository;
import com.greenwich.comp1640.util.constant.ArticleStatusConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    public List<Article> findAllArticle() {
        return this.articleRORepository.findAll();
    }

    public List<Article> findByUsername(String username) {
        return this.articleRORepository.findByUserUsername(username);
    }

    public Optional<Article> findById(Long id) {
        return this.articleRORepository.findById(id);
    }

    public List<Article> findByFacultyCode(String code) {
        return this.articleRORepository.findByFacultyCode(code);
    }

    public List<Article> findByCampaignCode(String code) {
        return this.articleRORepository.findByCampaignCode(code);
    }

    public List<Article> findByStatus(ArticleStatusConst status) {
        return this.articleRORepository.findByStatus(status);
    }

    public List<Article> findByFacultyAndStatus(String code, ArticleStatusConst status) {
        return this.articleRORepository.findByFacultyCodeAndStatus(code, status);
    }

    public List<Article> findByFacultyAndStatusAndCampaign(String facultyCode, ArticleStatusConst status, String campaignCode) {
        return this.articleRORepository.findByFacultyCodeAndStatusAndCampaignCode(facultyCode, status, campaignCode);
    }
}
