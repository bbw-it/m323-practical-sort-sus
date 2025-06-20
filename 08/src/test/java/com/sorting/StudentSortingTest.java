package com.sorting;

import com.sorting.model.Student;
import com.sorting.util.StudentSorter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class StudentSortingTest {
    private List<Student> students;
    
    @BeforeEach
    public void setUp() {
        // Generate test data
        StudentDataGenerator generator = new StudentDataGenerator();
        students = new ArrayList<>();
        students = generator.generateStudentData();
        
        // Print all generated students
        System.out.println("\nGenerated Test Students:");
        System.out.println("------------------------");
        students.forEach(student -> {
            System.out.println("Student ID: " + student.getStudentId());
            System.out.println("Name: " + student.getFirstName() + " " + student.getLastName());
            System.out.println("Birth Date: " + student.getBirthDate());
            System.out.println("GPA: " + student.getGradePointAverage());
            System.out.println("Address: " + student.getAddress());
            System.out.println("Number of Courses: " + student.getEnrolledCourses().length);
            System.out.println("------------------------");
        });
    }
    
    @Test
    public void testLastNameComparator() {
        System.out.println("\nSorting by Last Name (Separate Class):");
        students.sort(new StudentSorter.LastNameComparator());
        printStudents(students);
    }
    
    @Test
    public void testFirstNameComparator() {
        System.out.println("\nSorting by First Name (Anonymous Class):");
        students.sort(StudentSorter.getFirstNameComparator());
        printStudents(students);
    }
    
    @Test
    public void testBirthDateComparator() {
        System.out.println("\nSorting by Birth Date (Lambda):");
        students.sort(StudentSorter.getBirthDateComparator());
        printStudents(students);
    }
    
    @Test
    public void testMultiFieldComparator() {
        System.out.println("\nSorting by Multiple Fields (Comparator Chain):");
        students.sort(StudentSorter.getMultiFieldComparator());
        printStudents(students);
    }
    
    @Test
    public void testNaturalOrder() {
        System.out.println("\nSorting by Natural Order (Student ID):");
        StudentSorter.sortByNaturalOrder(students);
        printStudents(students);
    }
    
    @Test
    public void testReverseOrder() {
        System.out.println("\nSorting by Reverse Order:");
        StudentSorter.sortByReverseOrder(students);
        printStudents(students);
    }
    
    @Test
    public void testGPASort() {
        System.out.println("\nSorting by GPA:");
        StudentSorter.sortByGPA(students);
        printStudents(students);
    }
    
    @Test
    public void testAddressCitySort() {
        System.out.println("\nSorting by Address City:");
        StudentSorter.sortByAddressCity(students);
        printStudents(students);
    }
    
    @Test
    public void testNumberOfCoursesSort() {
        System.out.println("\nSorting by Number of Courses:");
        StudentSorter.sortByNumberOfCourses(students);
        printStudents(students);
    }
    
    @Test
    public void testComplexSort() {
        System.out.println("\nComplex Multi-level Sort:");
        StudentSorter.sortByComplexCriteria(students);
        printStudents(students);
    }
    
    private void printStudents(List<Student> students) {
        students.stream()
            .limit(5)
            .forEach(System.out::println);
    }
} 