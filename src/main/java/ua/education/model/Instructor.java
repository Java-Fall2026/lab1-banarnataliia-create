package ua.education.model;
import ua.education.util.EducationUtils;
public record Instructor(String instructorCode, String fullName, String degree) {
    public Instructor {
        EducationUtils.validateNotBlank(instructorCode, "Instructor code");
        EducationUtils.validateNotBlank(fullName, "Instructor full name");
        EducationUtils.validateNotBlank(degree, "Instructor degree");
        instructorCode = EducationUtils.normalizeText(instructorCode);
        fullName = EducationUtils.normalizeText(fullName);
        degree = EducationUtils.normalizeText(degree);
    }
}
