package ua.education.model;

import ua.education.util.EducationUtils;
import java.util.Objects;

public class Instructor extends ua.common.BaseEntity {
    private final String instructorCode;
    private final String fullName;
    private final String degree;

    public Instructor(String instructorCode, String fullName, String degree) {
        super();
        EducationUtils.validateNotBlank(instructorCode, "Instructor code");
        EducationUtils.validateNotBlank(fullName, "Instructor fullName");
        EducationUtils.validateNotBlank(degree, "Instructor degree");

        this.instructorCode = EducationUtils.normalizeText(instructorCode);
        this.fullName = EducationUtils.normalizeText(fullName);
        this.degree = EducationUtils.normalizeText(degree);
    }

    public String getInstructorCode() { return instructorCode; }
    public String getFullName() { return fullName; }
    public String getDegree() { return degree; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructor that = (Instructor) o;
        return Objects.equals(instructorCode, that.instructorCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instructorCode);
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "instructorCode='" + instructorCode + '\'' +
                ", fullName='" + fullName + '\'' +
                ", degree='" + degree + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}