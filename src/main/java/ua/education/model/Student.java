package ua.education.model;

import ua.education.util.EducationUtils;
import java.time.LocalDate;
import java.util.Objects;

public class Student extends ua.common.BaseEntity {
    private final String studentId;
    private final String name;
    private final String email;
    private final LocalDate birthDate;

    private Student(String studentId, String name, String email, LocalDate birthDate) {
        super();
        EducationUtils.validateNotBlank(studentId, "Student ID");
        EducationUtils.validateNotBlank(name, "Student name");
        EducationUtils.validateNotBlank(email, "Student email");
        EducationUtils.validateStudentAge(birthDate);

        this.studentId = EducationUtils.normalizeText(studentId);
        this.name = EducationUtils.normalizeText(name);
        this.email = EducationUtils.normalizeEmail(email);
        this.birthDate = birthDate;
    }

    public static Student of(String studentId, String name, String email, LocalDate birthDate) {
        return new Student(studentId, name, email, birthDate);
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public LocalDate getBirthDate() { return birthDate; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", createdAt=" + createdAt +
                '}';
    }
}