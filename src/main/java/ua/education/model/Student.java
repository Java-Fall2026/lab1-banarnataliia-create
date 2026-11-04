package ua.education.model;
import java.time.LocalDate;
import ua.education.util.EducationUtils;
public record Student(String studentId, String name, String email, LocalDate birthDate) {
    public Student {
        EducationUtils.validateNotBlank(studentId, "Student ID");
        EducationUtils.validateNotBlank(name, "Student name");
        EducationUtils.validateNotBlank(email, "Student email");
        EducationUtils.validateStudentAge(birthDate);
        studentId = EducationUtils.normalizeText(studentId);
        name = EducationUtils.normalizeText(name);
        email = EducationUtils.normalizeEmail(email);
    }
    public static Student of(String studentId, String name, String email, LocalDate birthDate) {
        return new Student(studentId, name, email, birthDate);
    }
}
