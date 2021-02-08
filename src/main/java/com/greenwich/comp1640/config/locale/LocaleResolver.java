package com.greenwich.comp1640.config.locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

@Component
public class LocaleResolver extends AcceptHeaderLocaleResolver implements WebMvcConfigurer {

    @Autowired
    LocaleConfig localeConfig;

    @Override
    public Locale getDefaultLocale() {
        return localeConfig.defaultLocale();
    }

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String language = request.getHeader("Accept-Language");
        if (language == null || language.isEmpty()) {
            return getDefaultLocale();
        }
        List<Locale.LanguageRange> list = Locale.LanguageRange.parse(language);
        Locale locale = Locale.lookup(list, localeConfig.supportedLocales());
        if (locale == null) {
            return getDefaultLocale();
        }
        return locale;
    }
}
