package com.greenwich.comp1640.response;

import com.greenwich.comp1640.locale.LocaleService;
import com.greenwich.comp1640.util.constant.TranslationConst;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomResponseStatusCode {

    private String code;
    private String message;
    private LocaleService localeService;

    public CustomResponseStatusCode(String code, @Nullable Object[] args, LocaleService localeService) {
        this.code = code;
        this.localeService = localeService;
        this.message = this.getMessageFromCode(code, args);
    }

    public String getMessageFromCode(String code, @Nullable Object[] args) {
        String key = localeService.getTranslationKey(TranslationConst.KeyPrefix.ERROR_PREFIX, code);
        return this.localeService.getMessage(key, args);
    }
}

