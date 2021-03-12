package com.greenwich.comp1640.service.impl;

import com.greenwich.comp1640.dao.ArticleDao;
import com.greenwich.comp1640.dao.CampaignDao;
import com.greenwich.comp1640.dao.FacultyDao;
import com.greenwich.comp1640.dao.UserDao;
import com.greenwich.comp1640.dto.mapper.ArticleMapper;
import com.greenwich.comp1640.dto.request.article.CreateArticleRequestDto;
import com.greenwich.comp1640.dto.request.article.UpdateArticleRequestDto;
import com.greenwich.comp1640.dto.response.ArticleResponseDto;
import com.greenwich.comp1640.model.Article;
import com.greenwich.comp1640.model.Campaign;
import com.greenwich.comp1640.model.Faculty;
import com.greenwich.comp1640.model.User;
import com.greenwich.comp1640.response.GeneralResponse;
import com.greenwich.comp1640.response.ResponseFactory;
import com.greenwich.comp1640.service.abstr.ArticleService;
import com.greenwich.comp1640.util.DateUtil;
import com.greenwich.comp1640.util.constant.ArticleStatusConst;
import com.greenwich.comp1640.util.constant.ResponseStatusCodeConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {
    
    @Autowired
    ResponseFactory responseFactory;
    
    @Autowired
    ArticleDao articleDao;

    @Autowired
    UserDao userDao;

    @Autowired
    FacultyDao facultyDao;

    @Autowired
    CampaignDao campaignDao;

    @Override
    public ResponseEntity<GeneralResponse<Object>> getAllArticles() {
        List<Article> articleList = articleDao.findAllArticle();

        List<ArticleResponseDto> articleResponseDtoList = ArticleMapper.toListDto(articleList);

        return responseFactory.success(articleResponseDtoList);
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> createArticle(CreateArticleRequestDto createArticleRequestDto) {

        User user = userDao.findByUsername(createArticleRequestDto.getUserUsername());

        if (user == null) {
            log.error(String.format("Can not find user with username: %s", createArticleRequestDto.getUserUsername()));
            return responseFactory.fail(String.format("Can not find user with username: %s", createArticleRequestDto.getUserUsername()),
                    ResponseStatusCodeConst.DATA_NOT_FOUND_ERROR,
                    null);
        }

        Faculty faculty = facultyDao.findByCode(createArticleRequestDto.getFacultyCode());

        if (faculty == null) {
            log.error(String.format("Can not find faculty with code: %s", createArticleRequestDto.getFacultyCode()));
            return responseFactory.fail(String.format("Can not find faculty with code: %s", createArticleRequestDto.getFacultyCode()),
                    ResponseStatusCodeConst.DATA_NOT_FOUND_ERROR,
                    null);
        }

        Campaign campaign = campaignDao.findByCode(createArticleRequestDto.getCampaignCode());

        if (campaign == null) {
            log.error(String.format("Can not find campaign with code: %s", createArticleRequestDto.getCampaignCode()));
            return responseFactory.fail(String.format("Can not find campaign with code: %s", createArticleRequestDto.getCampaignCode()),
                    ResponseStatusCodeConst.DATA_NOT_FOUND_ERROR,
                    null);
        }

        if (DateUtil.now().after(campaign.getSubmitDeadline())) {
            log.error(String.format("Submission time has expired"));
            return responseFactory.fail(String.format("Submission time has expired"),
                    ResponseStatusCodeConst.BUSINESS_ERROR,
                    null);
        }

        Article newArticle = ArticleMapper.createFromDto(createArticleRequestDto, user, faculty, campaign);

        articleDao.saveArticle(newArticle);

        return responseFactory.success(ArticleMapper.toDto(newArticle));
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> updateArticle(Long id, UpdateArticleRequestDto updateArticleRequestDto) {
        Optional<Article> article = articleDao.findById(id);

        if (!article.isPresent()) {
            log.error(String.format("Can not find article with article id: %d", id));
            return responseFactory.fail(String.format("Can not find article with article id: %d", id),
                    ResponseStatusCodeConst.DATA_NOT_FOUND_ERROR,
                    null);
        }

        Campaign campaign = campaignDao.findByCode(article.get().getCampaign().getCode());

        if (campaign == null) {
            log.error(String.format("Can not find campaign with code: %s", article.get().getCampaign().getCode()));
            return responseFactory.fail(String.format("Can not find campaign with code: %s", article.get().getCampaign().getCode()),
                    ResponseStatusCodeConst.DATA_NOT_FOUND_ERROR,
                    null);
        }

        if (DateUtil.now().after(campaign.getEditDeadline())) {
            log.error(String.format("Modify time has expired"));
            return responseFactory.fail(String.format("Modify time has expired"),
                    ResponseStatusCodeConst.BUSINESS_ERROR,
                    null);
        }

        if (DateUtil.now().after(campaign.getSubmitDeadline())) {
            log.error(String.format("Submission time has expired"));
            return responseFactory.fail(String.format("Submission time has expired"),
                    ResponseStatusCodeConst.BUSINESS_ERROR,
                    null);
        }

        Article updatedArticle = ArticleMapper.updateFromDto(article.get(), updateArticleRequestDto);

        articleDao.saveArticle(updatedArticle);

        return responseFactory.success(ArticleMapper.toDto(updatedArticle));
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> getArticle(Long id) {
        Optional<Article> article = articleDao.findById(id);

        if (!article.isPresent()) {
            log.error(String.format("Can not find article with article id: %d", id));
            return responseFactory.fail(String.format("Can not find article with article id: %d", id),
                    ResponseStatusCodeConst.DATA_NOT_FOUND_ERROR,
                    null);
        }

        return responseFactory.success(ArticleMapper.toDto(article.get()));
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> getAllArticlesByUser(String username) {
        List<Article> articles = articleDao.findByUsername(username);

        return responseFactory.success(ArticleMapper.toListDto(articles));
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> getAllArticlesByFacultyCode(String code) {
        List<Article> articles = articleDao.findByFacultyCode(code);

        return responseFactory.success(ArticleMapper.toListDto(articles));
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> getAllArticlesByCampaignCode(String code) {
        List<Article> articles = articleDao.findByCampaignCode(code);

        return responseFactory.success(ArticleMapper.toListDto(articles));
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> getAllArticlesByStatus(ArticleStatusConst status) {
        List<Article> articles = articleDao.findByStatus(status);

        return responseFactory.success(ArticleMapper.toListDto(articles));
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> updateArticleStatus(Long id, ArticleStatusConst status) {
        Optional<Article> article = articleDao.findById(id);

        if (!article.isPresent()) {
            log.error(String.format("Can not find article with article id: %d", id));
            return responseFactory.fail(String.format("Can not find article with article id: %d", id),
                    ResponseStatusCodeConst.DATA_NOT_FOUND_ERROR,
                    null);
        }

        Article existArticle = article.get();
        existArticle.setStatus(status);

        articleDao.saveArticle(existArticle);

        return responseFactory.success(ArticleMapper.toDto(existArticle));
    }
}
