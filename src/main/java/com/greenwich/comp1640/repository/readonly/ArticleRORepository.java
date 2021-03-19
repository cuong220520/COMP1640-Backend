package com.greenwich.comp1640.repository.readonly;

import com.greenwich.comp1640.config.datasource.ReadOnlyRepository;
import com.greenwich.comp1640.model.Article;
import com.greenwich.comp1640.util.constant.ArticleStatusConst;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@ReadOnlyRepository
public interface ArticleRORepository extends GenericRORepository<Article, Long> {

    Optional<Article> findById(Long id);
    Page<Article> findByUserUsername(String username, Pageable pageable);
    Page<Article> findByFacultyCode(String code, Pageable pageable);
    List<Article> findByCampaignCode(String code);
    Page<Article> findByCampaignCode(String code, Pageable pageable);
    Page<Article> findByStatus(ArticleStatusConst status, Pageable pageable);
    Page<Article> findByFacultyCodeAndStatus(String code, ArticleStatusConst status, Pageable pageable);
    Page<Article> findByFacultyCodeAndCampaignCode(String facultyCode, String campaignCode, Pageable pageable);
    Page<Article> findByFacultyCodeAndUserUsername(String facultyCode, String username, Pageable pageable);
    Page<Article> findByFacultyCodeAndStatusAndCampaignCode(String facultyCode,
                                                            ArticleStatusConst status,
                                                            String campaignCode,
                                                            Pageable pageable);
    Page<Article> findByFacultyCodeAndStatusAndUserUsername(String facultyCode,
                                                            ArticleStatusConst status,
                                                            String username,
                                                            Pageable pageable);
    Page<Article> findByFacultyCodeAndCampaignCodeAndUserUsername(String facultyCode,
                                                            String campaignCode,
                                                            String username,
                                                            Pageable pageable);
    Page<Article> findByFacultyCodeAndCampaignCodeAndUserUsernameAndStatus(String facultyCode,
                                                                  String campaignCode,
                                                                  String username,
                                                                  ArticleStatusConst status,
                                                                  Pageable pageable);

}
