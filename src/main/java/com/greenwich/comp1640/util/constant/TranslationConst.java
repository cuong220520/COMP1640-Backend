package com.greenwich.comp1640.util.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class TranslationConst {
    @AllArgsConstructor
    @Getter
    public enum KeyPrefix {
        ERROR_PREFIX("error"),
        FIELD_NAME_PREFIX("field"),
        ENTITY_PREFIX("entity"),
        VALIDATION_PREFIX("validation");
        private String value;
    }

    public static class ValidationMessage {
        public static final String NOT_EMPTY = "notEmpty";
        public static final String LENGTH = "length";
        public static final String PATTERN = "pattern";
        public static final String NOT_NEGATIVE = "notNegative";
        public static final String NOT_NULL = "notNull";

        private ValidationMessage() {
        }
    }
}
