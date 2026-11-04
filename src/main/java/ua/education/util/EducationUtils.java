package ua.education.util;
import ua.education.model.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
public final class EducationUtils {
    public static final int MIN_STUDENT_AGE = 12;
    private EducationUtils() {}
    public static void validateNotNull(Object value, String name) { ValidationHelper.requireNotNull(value, name); }
    public static void validateNotBlank(String value, String name) { ValidationHelper.requireNotBlank(value, name); }
    public static void validatePrice(double price) { ValidationHelper.requirePositive(price, "Course price"); }
    public static void validateStudentAge(LocalDate date) { ValidationHelper.requireMinAge(date, MIN_STUDENT_AGE, "Student birthDate"); }
    public static void validateEnrollDate(LocalDate date) { ValidationHelper.requireNotFuture(date, "Enrollment enrollDate"); }
    public static void validateStatus(EnrollmentStatus status, LocalDate completion, LocalDate enrolled) {
        validateNotNull(status, "Enrollment status");
        if (status == EnrollmentStatus.COMPLETED && completion == null)
            throw new IllegalArgumentException("Completion date is required for COMPLETED status");
        if (completion != null && enrolled != null && completion.isBefore(enrolled))
            throw new IllegalArgumentException("Completion date must not precede enrollment date");
        if (completion != null && completion.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Completion date must not be in the future");
    }
    public static String normalizeText(String text) { return FormatHelper.normalizeString(text); }
    public static String normalizeEmail(String email) { return FormatHelper.normalizeEmail(email); }
    public static long studyDays(CourseEnrollment enrollment) {
        if (enrollment == null || enrollment.getCompletionDate() == null) return -1;
        return ChronoUnit.DAYS.between(enrollment.getEnrollDate(), enrollment.getCompletionDate());
    }
    public static int studentAge(Student student) {
        if (student == null) return 0;
        return Period.between(student.birthDate(), LocalDate.now()).getYears();
    }
    public static String levelDescription(CourseLevel level) {
        validateNotNull(level, "Course level");
        return switch (level) {
            case BEGINNER -> "Beginner";
            case INTERMEDIATE -> "Intermediate";
            case ADVANCED -> "Advanced";
        };
    }
    public static String enrollmentAction(EnrollmentStatus status) {
        validateNotNull(status, "Enrollment status");
        return switch (status) {
            case ACTIVE -> "Continue learning";
            case COMPLETED -> "View certificate";
            case DROPPED -> "Enroll again";
        };
    }
}
