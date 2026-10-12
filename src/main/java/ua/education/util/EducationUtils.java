package ua.education.util;

import ua.education.model.CourseEnrollment;
import ua.education.model.Student;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class EducationUtils {
    public static final int MIN_STUDENT_AGE = 12;
    public static final String[] ALLOWED_DIFFICULTIES = {"BEGINNER", "INTERMEDIATE", "ADVANCED"};
    public static final String[] ALLOWED_STATUSES = {"ACTIVE", "COMPLETED", "DROPPED"};

    private EducationUtils() {}

    public static void validateNotNull(Object value, String fieldName) {
        ValidationHelper.requireNotNull(value, fieldName);
    }

    public static void validateNotBlank(String value, String fieldName) {
        ValidationHelper.requireNotBlank(value, fieldName);
    }

    public static void validatePrice(double price) {
        ValidationHelper.requirePositive(price, "Course price");
    }

    public static void validateStudentAge(LocalDate birthDate) {
        ValidationHelper.requireMinAge(birthDate, MIN_STUDENT_AGE, "Student birthDate");
    }

    public static void validateEnrollDate(LocalDate enrollDate) {
        ValidationHelper.requireNotFuture(enrollDate, "Enrollment enrollDate");
    }

    public static void validateDifficulty(String difficulty) {
        ValidationHelper.requireNotBlank(difficulty, "Course difficulty");
        String normalized = FormatHelper.normalizeEnum(difficulty);
        boolean valid = false;
        for (String d : ALLOWED_DIFFICULTIES) {
            if (d.equals(normalized)) {
                valid = true;
                break;
            }
        }
        if (!valid) {
            throw new IllegalArgumentException("Course difficulty must be BEGINNER, INTERMEDIATE, or ADVANCED, got: " + difficulty);
        }
    }

    public static void validateStatus(String status, LocalDate completionDate, LocalDate enrollDate) {
        ValidationHelper.requireNotBlank(status, "Enrollment status");
        String normalized = FormatHelper.normalizeEnum(status);
        boolean valid = false;
        for (String s : ALLOWED_STATUSES) {
            if (s.equals(normalized)) {
                valid = true;
                break;
            }
        }
        if (!valid) {
            throw new IllegalArgumentException("Enrollment status must be ACTIVE, COMPLETED, or DROPPED, got: " + status);
        }
        if ("COMPLETED".equals(normalized) && completionDate == null) {
            throw new IllegalArgumentException("Completion date must not be null when status is COMPLETED");
        }
        if (completionDate != null && enrollDate != null && completionDate.isBefore(enrollDate)) {
            throw new IllegalArgumentException("Completion date must be greater than or equal to enrollDate, got completionDate: " + completionDate + ", enrollDate: " + enrollDate);
        }
    }

    public static String normalizeEmail(String email) {
        return FormatHelper.normalizeEmail(email);
    }

    public static String normalizeText(String text) {
        return FormatHelper.normalizeString(text);
    }

    public static String normalizeEnum(String value) {
        return FormatHelper.normalizeEnum(value);
    }

    public static long studyDays(CourseEnrollment enrollment) {
        if (enrollment == null || enrollment.getCompletionDate() == null) {
            return -1;
        }
        return ChronoUnit.DAYS.between(enrollment.getEnrollDate(), enrollment.getCompletionDate());
    }

    public static int studentAge(Student student) {
        if (student == null || student.getBirthDate() == null) {
            return 0;
        }
        return Period.between(student.getBirthDate(), LocalDate.now()).getYears();
    }
}