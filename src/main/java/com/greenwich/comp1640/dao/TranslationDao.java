package com.greenwich.comp1640.dao;

import com.greenwich.comp1640.model.Translation;
import com.greenwich.comp1640.repository.readonly.TranslationRORepository;
import com.greenwich.comp1640.repository.readwrite.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TranslationDao extends BaseDao<Translation, Long> {
    private final TranslationRepository translationRepository;
    private final TranslationRORepository translationRORepository;

    @Autowired
    public TranslationDao(TranslationRepository translationRepository, TranslationRORepository translationRORepository) {
        super(translationRepository, translationRORepository);
        this.translationRepository = translationRepository;
        this.translationRORepository = translationRORepository;
    }

    public Translation findByKeyAndLocale(String key, String locale) {
        return this.translationRORepository.findByKeyAndLocale(key, locale);
    }
}
