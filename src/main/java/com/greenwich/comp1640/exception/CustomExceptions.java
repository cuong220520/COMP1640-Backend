package com.greenwich.comp1640.exception;

public class CustomExceptions {
    private CustomExceptions() {
    }

    public static class EntityNotFoundException extends RuntimeException {
        public EntityNotFoundException(String message) {
            super(message);
        }
    }

    public static class DuplicateEntityException extends RuntimeException {
        public DuplicateEntityException(String message) {
            super(message);
        }
    }

    public static class UnAuthorizedException extends RuntimeException {
        public UnAuthorizedException() {
            super("Unauthorized");
        }
    }

    public static class PermissionDenied extends RuntimeException {
        public PermissionDenied(String message) {
            super(message);
        }
    }

    public static class InternalException extends RuntimeException {
        public InternalException(String message) {
            super(message);
        }
    }

    public static class BadRequestException extends RuntimeException {
        public BadRequestException(String message) {
            super(message);
        }
    }

    public static class InvalidInputException extends RuntimeException {
        public InvalidInputException(String message) {
            super(message);
        }
    }
}
