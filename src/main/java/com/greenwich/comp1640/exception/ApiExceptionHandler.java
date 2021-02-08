package com.greenwich.comp1640.exception;

import com.google.common.base.CaseFormat;
import com.greenwich.comp1640.config.locale.LocaleConfig;
import com.greenwich.comp1640.locale.LocaleService;
import com.greenwich.comp1640.response.GeneralResponse;
import com.greenwich.comp1640.response.ResponseFactory;
import com.greenwich.comp1640.util.constant.ResponseStatusCodeConst;
import com.greenwich.comp1640.util.constant.TranslationConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {
    @Autowired
    LocaleService localeService;

    @Autowired
    LocaleConfig localeConfig;

    @Autowired
    ResponseFactory responseFactory;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<GeneralResponse<Object>> handleAllException(Exception exc) {
        log.error(exc.toString());
        return responseFactory.fail(null, ResponseStatusCodeConst.INTERNAL_SERVER_ERROR, null);
    }

    @ExceptionHandler(CustomExceptions.InternalException.class)
    public ResponseEntity<GeneralResponse<Object>> handleInternalException(CustomExceptions.InternalException exc) {
        log.error(exc.toString());
        return responseFactory.fail(null, ResponseStatusCodeConst.INTERNAL_SERVER_ERROR, null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeneralResponse<Object>> handleValidateException(MethodArgumentNotValidException exc) {
        log.error(exc.toString());
        Map<String, String> errors = new HashMap<>();
        exc.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, ((FieldError) error).getField());
            String translatedFieldName = localeService.getMessage(localeService.getTranslationKey(TranslationConst.KeyPrefix.FIELD_NAME_PREFIX, fieldName), null);
            Object[] args = new Object[]{translatedFieldName};
            String defaultMessage = error.getDefaultMessage();
            String translatedMessage = localeService.getMessage(localeService.getTranslationKey(TranslationConst.KeyPrefix.VALIDATION_PREFIX, defaultMessage), args);
            errors.put(fieldName, translatedMessage);
        });
        return responseFactory.fail(errors, ResponseStatusCodeConst.VALIDATION_ERROR, null);
    }

    @ExceptionHandler(CustomExceptions.DuplicateEntityException.class)
    public ResponseEntity<GeneralResponse<Object>> handleDuplicateEntityException(CustomExceptions.DuplicateEntityException exc) {
        log.error(String.format("Duplicated data %s", exc.getMessage()));
        String translatedEntityName = localeService.getMessage(localeService.getTranslationKey(TranslationConst.KeyPrefix.ENTITY_PREFIX, exc.getMessage()), null);
        Object[] args = new Object[]{translatedEntityName};
        return responseFactory.fail(null, ResponseStatusCodeConst.DUPLICATE_ERROR, args);
    }

    @ExceptionHandler(CustomExceptions.EntityNotFoundException.class)
    public ResponseEntity<GeneralResponse<Object>> handleEntityNotFoundException(CustomExceptions.EntityNotFoundException exc) {
        log.error(String.format("Not found data %s", exc.getMessage()));
        String translatedEntityName = localeService.getMessage(localeService.getTranslationKey(TranslationConst.KeyPrefix.ENTITY_PREFIX, exc.getMessage()), null);
        Object[] args = new Object[]{translatedEntityName};
        return responseFactory.fail(null, ResponseStatusCodeConst.DATA_NOT_FOUND_ERROR, args);
    }
}
