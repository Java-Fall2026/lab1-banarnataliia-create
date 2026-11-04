package ua.education.model;
public enum CourseLevel {
    BEGINNER(1.0), INTERMEDIATE(1.3), ADVANCED(1.6);
    private final double difficultyFactor;
    CourseLevel(double difficultyFactor) { this.difficultyFactor = difficultyFactor; }
    public double getDifficultyFactor() { return difficultyFactor; }
}
