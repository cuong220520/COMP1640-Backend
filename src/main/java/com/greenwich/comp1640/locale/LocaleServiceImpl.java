package com.greenwich.comp1640.locale;

import com.greenwich.comp1640.util.constant.TranslationConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Locale;


@Slf4j
@Component
public class LocaleServiceImpl implements LocaleService {
    private static final String DEFAULT_MESSAGE = "Undefined message";

    @Autowired
    private DBMessageSource messageSource;

    @Override
    public String getMessage(String code, @Nullable Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        String message;
        try {
            message = messageSource.getMessage(code, args, locale);
        } catch (Exception exception) {
            log.error(String.format("Error when get message from resource: %s", exception.toString()));
            return DEFAULT_MESSAGE;
        }

        if (message.isEmpty()) {
            return DEFAULT_MESSAGE;
        }

        return message;
    }

    @Override
    public String getTranslationKey(TranslationConst.KeyPrefix prefix, String code) {
        return String.format("%s.%s", prefix.getValue(), code);
    }
}
