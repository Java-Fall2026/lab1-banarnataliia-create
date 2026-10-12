package ua.education.model;

import ua.education.util.EducationUtils;
import java.time.LocalDate;
import java.util.Objects;

public class CourseEnrollment extends ua.common.BaseEntity {
    private final Student student;
    private final Course course;
    private final LocalDate enrollDate;
    private LocalDate completionDate;
    private String status;

    public CourseEnrollment(Student student, Course course, LocalDate enrollDate, LocalDate completionDate, String status) {
        super();
        EducationUtils.validateNotNull(student, "Enrollment student");
        EducationUtils.validateNotNull(course, "Enrollment course");
        EducationUtils.validateEnrollDate(enrollDate);

        this.student = student;
        this.course = course;
        this.enrollDate = enrollDate;

        setCompletionDate(completionDate);
        setStatus(status);
    }

    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public LocalDate getEnrollDate() { return enrollDate; }
    public LocalDate getCompletionDate() { return completionDate; }
    public String getStatus() { return status; }

    public final void setCompletionDate(LocalDate completionDate) {
        if (completionDate != null && enrollDate != null && completionDate.isBefore(enrollDate)) {
            throw new IllegalArgumentException("Completion date must be greater than or equal to enrollDate, got: " + completionDate);
        }
        this.completionDate = completionDate;
    }

    public final void setStatus(String status) {
        EducationUtils.validateStatus(status, completionDate, enrollDate);
        this.status = EducationUtils.normalizeEnum(status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseEnrollment that = (CourseEnrollment) o;
        return Objects.equals(student, that.student) && Objects.equals(course, that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, course);
    }

    @Override
    public String toString() {
        return "CourseEnrollment{" +
                "student=" + student.getStudentId() +
                ", course=" + course.getCourseCode() +
                ", enrollDate=" + enrollDate +
                ", completionDate=" + completionDate +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}