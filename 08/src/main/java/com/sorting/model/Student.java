package com.sorting.model;

import java.time.LocalDate;
import java.util.Objects;

public class Student implements Comparable<Student> {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private double gradePointAverage;
    private Address address;
    private Course[] enrolledCourses;
    private int studentId;

    public Student(String firstName, String lastName, LocalDate birthDate, 
                  double gradePointAverage, Address address, Course[] enrolledCourses, 
                  int studentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gradePointAverage = gradePointAverage;
        this.address = address;
        this.enrolledCourses = enrolledCourses;
        this.studentId = studentId;
    }

    // Getters and Setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    
    public double getGradePointAverage() { return gradePointAverage; }
    public void setGradePointAverage(double gradePointAverage) { this.gradePointAverage = gradePointAverage; }
    
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
    
    public Course[] getEnrolledCourses() { return enrolledCourses; }
    public void setEnrolledCourses(Course[] enrolledCourses) { this.enrolledCourses = enrolledCourses; }
    
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    @Override
    public int compareTo(Student other) {
        // Natural ordering by student ID
        return Integer.compare(this.studentId, other.studentId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentId == student.studentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", studentId=" + studentId +
                ", gpa=" + gradePointAverage +
                '}';
    }
} 