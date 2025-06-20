package ch.bbw.sortieren.comparators;

import ch.bbw.sortieren.Student;
import java.util.Comparator;

public class EnrollmentDateComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.getEnrollmentDate().compareTo(s2.getEnrollmentDate());
    }
}