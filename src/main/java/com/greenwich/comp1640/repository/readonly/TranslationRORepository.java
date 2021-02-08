package com.greenwich.comp1640.repository.readonly;

import com.greenwich.comp1640.config.datasource.ReadOnlyRepository;
import com.greenwich.comp1640.model.Translation;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
@ReadOnlyRepository
public interface TranslationRORepository extends GenericRORepository<Translation, Long> {
    Translation findByKeyAndLocale(String key, String locale);
}
