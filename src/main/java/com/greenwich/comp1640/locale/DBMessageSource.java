package com.greenwich.comp1640.locale;

import com.greenwich.comp1640.config.locale.LocaleConfig;
import com.greenwich.comp1640.dao.TranslationDao;
import com.greenwich.comp1640.model.Translation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Locale;

@Component("messageSource")
public class DBMessageSource extends AbstractMessageSource {
    @Autowired
    LocaleConfig localeConfig;

    @Autowired
    private TranslationDao translationDao;

    @Override
    protected MessageFormat resolveCode(String key, Locale locale) {
        Translation message = translationDao.findByKeyAndLocale(key, locale.getLanguage());
        if (message == null) {
            message = translationDao.findByKeyAndLocale(key, localeConfig.defaultLocale().getLanguage());
        }
        if (message == null) {
            return new MessageFormat(localeConfig.getDefaultMessage(), locale);
        }
        return new MessageFormat(message.getValue(), locale);
    }
}
