package com.greenwich.comp1640.locale;

import com.greenwich.comp1640.util.constant.TranslationConst;
import org.springframework.lang.Nullable;

public interface LocaleService {
    String getMessage(String code, @Nullable Object[] args);

    String getTranslationKey(TranslationConst.KeyPrefix prefix, String code);
}