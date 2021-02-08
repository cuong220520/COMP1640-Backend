package com.greenwich.comp1640.config.locale;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Getter
@Setter
@Component
@Validated
@ConfigurationProperties(prefix = "localization")
public class LocaleConfig {
    @NotEmpty(message = "Missing supported locales config")
    private List<String> supportedLocales;
    private String defaultLocale = "vi";
    private String defaultMessage = "Undefined message";

    public List<Locale> supportedLocales() {
        List<Locale> locales = new ArrayList<>();

        for (String locale : this.supportedLocales) {
            locales.add(new Locale(locale));
        }
        return locales;
    }

    public Locale defaultLocale() {
        return new Locale(this.getDefaultLocale());
    }
}
