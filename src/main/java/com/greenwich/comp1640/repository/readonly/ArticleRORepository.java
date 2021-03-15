package com.greenwich.comp1640.repository.readonly;

import com.greenwich.comp1640.config.datasource.ReadOnlyRepository;
import com.greenwich.comp1640.model.Article;
import com.greenwich.comp1640.util.constant.ArticleStatusConst;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@ReadOnlyRepository
public interface ArticleRORepository extends GenericRORepository<Article, Long> {

    Optional<Article> findById(Long id);
    List<Article> findByUserUsername(String username);
    List<Article> findByFacultyCode(String code);
    List<Article> findByCampaignCode(String code);
    List<Article> findByStatus(ArticleStatusConst status);
    List<Article> findByFacultyCodeAndStatus(String code, ArticleStatusConst status);
    List<Article> findByFacultyCodeAndStatusAndCampaignCode(String facultyCode, ArticleStatusConst status, String campaignCode);

}
