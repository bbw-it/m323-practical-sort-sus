package ch.bbw.sortieren.comparators;

import ch.bbw.sortieren.Student;
import java.util.Comparator;

public class AgeComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return Integer.compare(s1.getAge(), s2.getAge());
    }
}