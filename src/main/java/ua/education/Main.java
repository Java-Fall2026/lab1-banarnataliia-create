package ua.education;
import ua.education.model.*;
import ua.education.util.EducationUtils;
import java.time.LocalDate;
public class Main {
    public static void main(String[] args) {
        System.out.println("=== 1. Створення record та доступ до компонентів ===");
        Instructor instructor = new Instructor("I-01", "Alan Turing", "PhD");
        Student student = Student.of("S-01", "Наталія", "TEST@MAIL.COM", LocalDate.of(2005, 5, 10));
        System.out.println("Ім'я: " + student.name());
        System.out.println("Email: " + student.email());
        System.out.println("Викладач: " + instructor.fullName());
        System.out.println("=== 2. Enum ===");
        for (CourseLevel level : CourseLevel.values())
            System.out.println(level + ": коефіцієнт " + level.getDifficultyFactor());
        try { CourseLevel.valueOf("WRONG"); }
        catch (IllegalArgumentException e) { System.out.println("Неправильна константа: " + e.getMessage()); }
        System.out.println("=== 3. Два switch-вирази ===");
        System.out.println("Рівень (опис): " + EducationUtils.levelDescription(CourseLevel.INTERMEDIATE));
        System.out.println("Доступна дія: " + EducationUtils.enrollmentAction(EnrollmentStatus.ACTIVE));
        System.out.println("=== 4. Некоректний record ===");
        try { new Student("", "Test", "test@mail.com", LocalDate.of(2000, 1, 1)); }
        catch (IllegalArgumentException e) { System.out.println("Помилка: " + e.getMessage()); }
        System.out.println("=== 5. equals та hashCode ===");
        Student copy = new Student("S-01", "Наталія", "test@mail.com", LocalDate.of(2005, 5, 10));
        System.out.println("equals: " + student.equals(copy));
        System.out.println("hashCode однакові: " + (student.hashCode() == copy.hashCode()));
        System.out.println("=== 6. Згенерований toString ===");
        System.out.println(student);
        System.out.println(instructor);
        Course course = Course.of("CS-101", "Java", instructor, CourseLevel.BEGINNER, 150);
        CourseEnrollment enrollment = new CourseEnrollment(student, course, LocalDate.now().minusDays(10), null, EnrollmentStatus.ACTIVE);
        System.out.println("=== 7. Змінювані класи ===");
        course.setPrice(180);
        System.out.println("Ціна: " + course.getPrice());
        enrollment.setCompletionDate(LocalDate.now());
        enrollment.setStatus(EnrollmentStatus.COMPLETED);
        System.out.println("Статус: " + enrollment.getStatus());
        System.out.println("Днів навчання: " + EducationUtils.studyDays(enrollment));
        System.out.println("Вік студента: " + EducationUtils.studentAge(student));
    }
}
