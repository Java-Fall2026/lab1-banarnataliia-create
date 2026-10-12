package ua.education.util;

import java.time.LocalDate;

class ValidationHelper {
    private ValidationHelper() {}

    static void requireNotNull(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " must not be null");
        }
    }

    static void requireNotBlank(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " must not be blank, got: " + value);
        }
    }

    static void requirePositive(double value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException(fieldName + " must be greater than or equal to 0, got: " + value);
        }
    }

    static void requireMinAge(LocalDate birthDate, int minAge, String fieldName) {
        requireNotNull(birthDate, fieldName);
        if (LocalDate.now().minusYears(minAge).isBefore(birthDate)) {
            throw new IllegalArgumentException(fieldName + " must be at least " + minAge + " years old, got: " + birthDate);
        }
    }

    static void requireNotFuture(LocalDate date, String fieldName) {
        requireNotNull(date, fieldName);
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(fieldName + " must not be in the future, got: " + date);
        }
    }
}