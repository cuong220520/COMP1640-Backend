package com.greenwich.comp1640.repository.readwrite;

import com.greenwich.comp1640.model.Article;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends GenericRepository<Article, Long> {
}
