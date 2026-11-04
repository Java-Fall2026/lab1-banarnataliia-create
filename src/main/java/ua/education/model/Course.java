package ua.education.model;
import java.time.LocalDateTime;
import java.util.Objects;
import ua.education.util.EducationUtils;
public class Course {
    private final String courseCode;
    private final String title;
    private final Instructor instructor;
    private final CourseLevel difficulty;
    private final LocalDateTime createdAt;
    private double price;
    private Course(String courseCode, String title, Instructor instructor, CourseLevel difficulty, double price) {
        EducationUtils.validateNotBlank(courseCode, "Course code");
        EducationUtils.validateNotBlank(title, "Course title");
        EducationUtils.validateNotNull(instructor, "Course instructor");
        EducationUtils.validateNotNull(difficulty, "Course difficulty");
        this.courseCode = EducationUtils.normalizeText(courseCode);
        this.title = EducationUtils.normalizeText(title);
        this.instructor = instructor;
        this.difficulty = difficulty;
        this.createdAt = LocalDateTime.now();
        setPrice(price);
    }
    public static Course of(String courseCode, String title, Instructor instructor, CourseLevel difficulty, double price) {
        return new Course(courseCode, title, instructor, difficulty, price);
    }
    public String getCourseCode() { return courseCode; }
    public String getTitle() { return title; }
    public Instructor getInstructor() { return instructor; }
    public CourseLevel getDifficulty() { return difficulty; }
    public double getPrice() { return price; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public final void setPrice(double price) { EducationUtils.validatePrice(price); this.price = price; }
    @Override public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Course other)) return false;
        return Objects.equals(courseCode, other.courseCode);
    }
    @Override public int hashCode() { return Objects.hash(courseCode); }
    @Override public String toString() {
        return "Course{courseCode='" + courseCode + "', title='" + title + "', difficulty=" + difficulty + ", price=" + price + ", createdAt=" + createdAt + "}";
    }
}
