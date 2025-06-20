package ch.bbw.sortieren;

import java.time.LocalDate;
import java.util.List;

public class Student implements Comparable<Student> {
    private String name;
    private int age;
    private double gpa;
    private LocalDate enrollmentDate;
    private List<String> courses;

    public Student(String name, int age, double gpa, LocalDate enrollmentDate, List<String> courses) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
        this.enrollmentDate = enrollmentDate;
        this.courses = courses;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public double getGpa() { return gpa; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public List<String> getCourses() { return courses; }

    @Override
    public int compareTo(Student other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return String.format("%s, %d, %.2f, %s, %s", name, age, gpa, enrollmentDate, courses);
    }
}