package ua.education.model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import ua.education.util.EducationUtils;
public class CourseEnrollment {
    private final Student student;
    private final Course course;
    private final LocalDate enrollDate;
    private final LocalDateTime createdAt;
    private LocalDate completionDate;
    private EnrollmentStatus status;
    public CourseEnrollment(Student student, Course course, LocalDate enrollDate, LocalDate completionDate, EnrollmentStatus status) {
        EducationUtils.validateNotNull(student, "Enrollment student");
        EducationUtils.validateNotNull(course, "Enrollment course");
        EducationUtils.validateEnrollDate(enrollDate);
        EducationUtils.validateStatus(status, completionDate, enrollDate);
        this.student = student;
        this.course = course;
        this.enrollDate = enrollDate;
        this.completionDate = completionDate;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }
    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public LocalDate getEnrollDate() { return enrollDate; }
    public LocalDate getCompletionDate() { return completionDate; }
    public EnrollmentStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public final void setCompletionDate(LocalDate date) {
        EducationUtils.validateStatus(status, date, enrollDate);
        completionDate = date;
    }
    public final void setStatus(EnrollmentStatus newStatus) {
        EducationUtils.validateStatus(newStatus, completionDate, enrollDate);
        status = newStatus;
    }
    @Override public String toString() {
        return "CourseEnrollment{student=" + student.studentId() + ", course=" + course.getCourseCode() + ", enrollDate=" + enrollDate + ", completionDate=" + completionDate + ", status=" + status + ", createdAt=" + createdAt + "}";
    }
}
