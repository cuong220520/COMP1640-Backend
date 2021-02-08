package com.greenwich.comp1640.repository.readwrite;

import com.greenwich.comp1640.model.Translation;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslationRepository extends GenericRepository<Translation, Long> {
}
