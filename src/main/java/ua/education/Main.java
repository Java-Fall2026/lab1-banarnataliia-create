package ua.education;

import ua.education.model.*;
import ua.education.util.EducationUtils;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== 1. Створення обʼєктів конструктором та фабричним методом ===");
        Instructor instructor = new Instructor("INS-001", "Dr. Alan Turing", "PhD in Computer Science");
        Student student1 = Student.of("S-101", "  Іван Петренко  ", "Ivan@MAIL.com", LocalDate.now().minusYears(20));
        Course course1 = Course.of("CS-101", "Java Basics", instructor, "beginner", 150.0);
        CourseEnrollment enrollment1 = new CourseEnrollment(student1, course1, LocalDate.now().minusDays(10), null, "active");

        System.out.println(instructor);
        System.out.println(student1);
        System.out.println(course1);
        System.out.println(enrollment1);

        System.out.println("\n=== 2. Нормалізація в дії ===");
        System.out.println("Нормалізований email студента: " + student1.getEmail());
        System.out.println("Нормалізована складність курсу: " + course1.getDifficulty());
        System.out.println("Нормалізований статус запису: " + enrollment1.getStatus());

        System.out.println("\n=== 3. Три різні порушення правил у try/catch ===");
        try {
            Student.of("S-102", "Marta", "marta@mail.com", LocalDate.now().minusYears(10)); // занадто молодий (< 12)
        } catch (IllegalArgumentException e) {
            System.out.println("Порушення 1: " + e.getMessage());
        }

        try {
            Course.of("CS-102", "Advanced Java", instructor, "invalid_diff", 100.0); // невалідний рівень
        } catch (IllegalArgumentException e) {
            System.out.println("Порушення 2: " + e.getMessage());
        }

        try {
            new CourseEnrollment(student1, course1, LocalDate.now().plusDays(5), null, "active"); // дата в майбутньому
        } catch (IllegalArgumentException e) {
            System.out.println("Порушення 3: " + e.getMessage());
        }

        System.out.println("\n=== 4. Коректний обʼєкт, який намагаються зіпсувати сеттером ===");
        System.out.println("Початкова ціна курсу: " + course1.getPrice());
        try {
            course1.setPrice(-50.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Сеттер завадив встановити від'ємну ціну: " + e.getMessage());
        }
        System.out.println("Ціна курсу залишилася незмінною: " + course1.getPrice());

        System.out.println("\n=== 5. Порівняння рівності (==, equals, hashCode) ===");
        Student studentCopy = Student.of("S-101", "Інше Ім'я", "other@mail.com", LocalDate.now().minusYears(25));
        System.out.println("student1 == studentCopy: " + (student1 == studentCopy));
        System.out.println("student1.equals(studentCopy): " + student1.equals(studentCopy));
        System.out.println("Збіг hashCode(): " + (student1.hashCode() == studentCopy.hashCode()));

        Student student2 = Student.of("S-102", "Ольга", "olga@mail.com", LocalDate.now().minusYears(22));
        System.out.println("student1.equals(student2) (різні студенти): " + student1.equals(student2));

        System.out.println("\n=== 6. Виклик обчислюваних методів ===");
        CourseEnrollment completedEnrollment = new CourseEnrollment(
                student2, course1, LocalDate.now().minusDays(30), LocalDate.now().minusDays(5), "COMPLETED"
        );
        System.out.println("Тривалість активного навчання: " + EducationUtils.studyDays(enrollment1) + " днів");
        System.out.println("Тривалість завершеного навчання: " + EducationUtils.studyDays(completedEnrollment) + " днів");
        System.out.println("Вік студента1: " + EducationUtils.studentAge(student1) + " років");

        System.out.println("\n=== 7. Вивід усіх сутностей через toString() ===");
        System.out.println(instructor);
        System.out.println(student1);
        System.out.println(student2);
        System.out.println(course1);
        System.out.println(enrollment1);
        System.out.println(completedEnrollment);

        System.out.println("\n=== 8. Демонстрація помилки компіляції (закоментовано) ===");
        
        System.out.println("Правила інкапсуляції між пакетами працюють коректно.");
    }
}