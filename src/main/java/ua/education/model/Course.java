package ua.education.model;

import ua.education.util.EducationUtils;
import java.util.Objects;

public class Course extends ua.common.BaseEntity {
    private final String courseCode;
    private final String title;
    private final Instructor instructor;
    private final String difficulty;
    private double price;

    private Course(String courseCode, String title, Instructor instructor, String difficulty, double price) {
        super();
        EducationUtils.validateNotBlank(courseCode, "Course code");
        EducationUtils.validateNotBlank(title, "Course title");
        EducationUtils.validateNotNull(instructor, "Course instructor");
        EducationUtils.validateDifficulty(difficulty);

        this.courseCode = EducationUtils.normalizeText(courseCode);
        this.title = EducationUtils.normalizeText(title);
        this.instructor = instructor;
        this.difficulty = EducationUtils.normalizeEnum(difficulty);
        setPrice(price);
    }

    public static Course of(String courseCode, String title, Instructor instructor, String difficulty, double price) {
        return new Course(courseCode, title, instructor, difficulty, price);
    }

    public String getCourseCode() { return courseCode; }
    public String getTitle() { return title; }
    public Instructor getInstructor() { return instructor; }
    public String getDifficulty() { return difficulty; }
    public double getPrice() { return price; }

    public final void setPrice(double price) {
        EducationUtils.validatePrice(price);
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseCode, course.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseCode='" + courseCode + '\'' +
                ", title='" + title + '\'' +
                ", instructor=" + instructor.getInstructorCode() +
                ", difficulty='" + difficulty + '\'' +
                ", price=" + price +
                ", createdAt=" + createdAt +
                '}';
    }
}