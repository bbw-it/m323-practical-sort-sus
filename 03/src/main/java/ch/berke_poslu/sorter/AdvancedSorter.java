package ch.berke_poslu.sorter;

import ch.berke_poslu.model.Student;
import ch.berke_poslu.model.Course;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

public class AdvancedSorter {
    
    // sortierung nach anzahl eingeschriebener kurse
    public static Comparator<Student> getEnrolledCoursesCountComparator() {
        return (s1, s2) -> Integer.compare(
            s1.getEnrolledCourses().size(),
            s2.getEnrolledCourses().size()
        );
    }

    // sortierung nach anzahl eingeschriebener studenten
    public static Comparator<Course> getEnrolledStudentsCountComparator() {
        return (c1, c2) -> Integer.compare(
            c1.getEnrolledStudents().size(),
            c2.getEnrolledStudents().size()
        );
    }

    // sortierung nach notendurchschnitt
    public static Comparator<Student> getAverageCourseGradeComparator() {
        return (s1, s2) -> Double.compare(
            s1.getGradePointAverage(),
            s2.getGradePointAverage()
        );
    }

    // mehrstufige sortierung: nach anzahl kurse, dann notendurchschnitt
    public static Comparator<Student> getMultiLevelCourseComparator() {
        return Comparator
            .<Student, Integer>comparing(student -> student.getEnrolledCourses().size())
            .thenComparing(Student::getGradePointAverage)
            .thenComparing(Student::getLastName)
            .thenComparing(Student::getFirstName);
    }

    // sortierung nach startzeit und anzahl eingeschriebener studenten
    public static Comparator<Course> getCourseStartTimeAndEnrollmentComparator() {
        return Comparator
            .comparing(Course::getStartTime)
            .thenComparing(course -> course.getEnrolledStudents().size());
    }

    // demonstration aller erweiterten sortiermethoden
    public static void demonstrateAdvancedSorting(List<Student> students, List<Course> courses) {
        System.out.println("\nerweiterte sortierungen:");
        System.out.println("=======================");

        // sortierung nach anzahl eingeschriebener kurse
        List<Student> studentsByCourseCount = new ArrayList<>(students);
        studentsByCourseCount.sort(getEnrolledCoursesCountComparator());
        System.out.println("\nstudenten sortiert nach anzahl eingeschriebener kurse:");
        printStudents(studentsByCourseCount);

        // sortierung nach anzahl eingeschriebener studenten
        List<Course> coursesByStudentCount = new ArrayList<>(courses);
        coursesByStudentCount.sort(getEnrolledStudentsCountComparator());
        System.out.println("\nkurse sortiert nach anzahl eingeschriebener studenten:");
        printCourses(coursesByStudentCount);

        // mehrstufige sortierung
        List<Student> multiLevelSorted = new ArrayList<>(students);
        multiLevelSorted.sort(getMultiLevelCourseComparator());
        System.out.println("\nstudenten sortiert nach kursanzahl, notendurchschnitt und name:");
        printStudents(multiLevelSorted);

        // kurs-sortierung nach startzeit und einschreibungen
        List<Course> timeAndEnrollmentSorted = new ArrayList<>(courses);
        timeAndEnrollmentSorted.sort(getCourseStartTimeAndEnrollmentComparator());
        System.out.println("\nkurse sortiert nach startzeit und einschreibungen:");
        printCourses(timeAndEnrollmentSorted);
    }

    private static void printStudents(List<Student> students) {
        students.forEach(student -> 
            System.out.printf("%s %s (kurse: %d, notendurchschnitt: %.2f)%n",
                student.getFirstName(),
                student.getLastName(),
                student.getEnrolledCourses().size(),
                student.getGradePointAverage()
            )
        );
    }

    private static void printCourses(List<Course> courses) {
        courses.forEach(course -> 
            System.out.printf("%s - %s (studenten: %d, start: %s)%n",
                course.getCourseId(),
                course.getName(),
                course.getEnrolledStudents().size(),
                course.getStartTime()
            )
        );
    }
} 
