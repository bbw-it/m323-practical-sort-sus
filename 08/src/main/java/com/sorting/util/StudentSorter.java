package com.sorting.util;

import com.sorting.model.Student;
import java.util.Comparator;
import java.util.List;

public class StudentSorter {
    
    // 1. Comparator as a separate class
    public static class LastNameComparator implements Comparator<Student> {
        @Override
        public int compare(Student s1, Student s2) {
            return s1.getLastName().compareTo(s2.getLastName());
        }
    }
    
    // 2. Anonymous class comparator
    public static Comparator<Student> getFirstNameComparator() {
        return new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getFirstName().compareTo(s2.getFirstName());
            }
        };
    }
    
    // 3. Lambda expression comparator
    public static Comparator<Student> getBirthDateComparator() {
        return (s1, s2) -> s1.getBirthDate().compareTo(s2.getBirthDate());
    }
    
    // 4. Comparator chain
    public static Comparator<Student> getMultiFieldComparator() {
        return Comparator
            .comparing(Student::getLastName)
            .thenComparing(Student::getFirstName)
            .thenComparing(Student::getStudentId);
    }
    
    // 5. Natural order (using Comparable)
    public static void sortByNaturalOrder(List<Student> students) {
        students.sort(null); // Uses Student's compareTo method
    }
    
    // 6. Reverse order
    public static void sortByReverseOrder(List<Student> students) {
        students.sort(Comparator.reverseOrder());
    }
    
    // 7. Sort by GPA
    public static void sortByGPA(List<Student> students) {
        students.sort(Comparator.comparingDouble(Student::getGradePointAverage));
    }
    
    // 8. Sort by address city
    public static void sortByAddressCity(List<Student> students) {
        students.sort(Comparator.comparing(s -> s.getAddress().getCity()));
    }
    
    // 9. Sort by number of courses
    public static void sortByNumberOfCourses(List<Student> students) {
        students.sort(Comparator.comparingInt(s -> s.getEnrolledCourses().length));
    }
    
    // 10. Complex multi-level sort
    public static void sortByComplexCriteria(List<Student> students) {
        students.sort(Comparator
            .comparing(Student::getAddress, Comparator.comparing(addr -> addr.getCountry()))
            .thenComparing(s -> s.getAddress().getCity())
            .thenComparing(Student::getLastName)
            .thenComparing(Student::getFirstName)
            .thenComparingDouble(Student::getGradePointAverage)
            .reversed());
    }
} 