package ch.berke_poslu.sorter;

import ch.berke_poslu.model.Student;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

public class StudentSorter {
    
    // 1. comparator als eigene klasse
    public static class GradePointAverageComparator implements Comparator<Student> {
        @Override
        public int compare(Student s1, Student s2) {
            return s1.getGradePointAverage().compareTo(s2.getGradePointAverage());
        }
    }

    // 2. anonymer comparator
    public static Comparator<Student> getBirthDateComparator() {
        return new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getBirthDate().compareTo(s2.getBirthDate());
            }
        };
    }

    // 3. lambda-ausdruck als comparator
    public static Comparator<Student> getSemesterComparator() {
        return (s1, s2) -> s1.getSemester().compareTo(s2.getSemester());
    }

    // 4. comparator-kette für mehrstufige sortierung
    public static Comparator<Student> getMultiFieldComparator() {
        return Comparator
            .comparing(Student::getLastName)
            .thenComparing(Student::getFirstName)
            .thenComparing(Student::getGradePointAverage);
    }

    // 5. natürliche sortierung (über comparable)
    public static void sortByNaturalOrder(List<Student> students) {
        Collections.sort(students);
    }

    // 6. umgekehrte sortierung
    public static void sortByReverseOrder(List<Student> students) {
        Collections.sort(students, Collections.reverseOrder());
    }

    // 7. sortierung nach anzahl kurse
    public static Comparator<Student> getCoursesComparator() {
        return (s1, s2) -> {
            int s1Size = s1.getCourses().size();
            int s2Size = s2.getCourses().size();
            return Integer.compare(s1Size, s2Size);
        };
    }

    // 8. sortierung nach aktivitätsstatus
    public static Comparator<Student> getActiveStatusComparator() {
        return (s1, s2) -> s1.getIsActive().compareTo(s2.getIsActive());
    }

    // beispiel für die anwendung aller sortiermethoden
    public static void demonstrateAllSortingMethods(List<Student> students) {
        System.out.println("ursprüngliche liste:");
        printStudents(students);

        // natürliche sortierung
        List<Student> naturalOrdered = new ArrayList<>(students);
        sortByNaturalOrder(naturalOrdered);
        System.out.println("\nnatürliche sortierung (nach nachname, vorname):");
        printStudents(naturalOrdered);

        // umgekehrte sortierung
        List<Student> reverseOrdered = new ArrayList<>(students);
        sortByReverseOrder(reverseOrdered);
        System.out.println("\numgekehrte sortierung:");
        printStudents(reverseOrdered);

        // sortierung nach notendurchschnitt
        List<Student> gpaOrdered = new ArrayList<>(students);
        gpaOrdered.sort(new GradePointAverageComparator());
        System.out.println("\nsortierung nach notendurchschnitt:");
        printStudents(gpaOrdered);

        // sortierung nach geburtsdatum
        List<Student> birthDateOrdered = new ArrayList<>(students);
        birthDateOrdered.sort(getBirthDateComparator());
        System.out.println("\nsortierung nach geburtsdatum:");
        printStudents(birthDateOrdered);

        // sortierung nach semester
        List<Student> semesterOrdered = new ArrayList<>(students);
        semesterOrdered.sort(getSemesterComparator());
        System.out.println("\nsortierung nach semester:");
        printStudents(semesterOrdered);

        // mehrstufige sortierung
        List<Student> multiFieldOrdered = new ArrayList<>(students);
        multiFieldOrdered.sort(getMultiFieldComparator());
        System.out.println("\nmehrstufige sortierung (nachname, vorname, notendurchschnitt):");
        printStudents(multiFieldOrdered);

        // sortierung nach anzahl kurse
        List<Student> coursesOrdered = new ArrayList<>(students);
        coursesOrdered.sort(getCoursesComparator());
        System.out.println("\nsortierung nach anzahl kurse:");
        printStudents(coursesOrdered);

        // sortierung nach aktivitätsstatus
        List<Student> activeStatusOrdered = new ArrayList<>(students);
        activeStatusOrdered.sort(getActiveStatusComparator());
        System.out.println("\nsortierung nach aktivitätsstatus:");
        printStudents(activeStatusOrdered);
    }

    private static void printStudents(List<Student> students) {
        students.forEach(student -> 
            System.out.printf("%s %s (notendurchschnitt: %.2f, semester: %d, aktiv: %s)%n",
                student.getFirstName(),
                student.getLastName(),
                student.getGradePointAverage(),
                student.getSemester(),
                student.getIsActive()
            )
        );
    }
} 
