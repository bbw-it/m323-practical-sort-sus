package ch.berke_poslu;

import ch.berke_poslu.model.Student;
import ch.berke_poslu.model.Course;
import ch.berke_poslu.sorter.StudentSorter;
import ch.berke_poslu.sorter.AdvancedSorter;
import org.instancio.Instancio;
import org.instancio.Select;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // kurse generieren
        List<Course> courses = generateCourses(10);
        
        // studenten mit kursverknüpfungen generieren
        List<Student> students = generateStudents(100, courses);

        // grundlegende sortiermethoden demonstrieren
        System.out.println("grundlegende sortierungen:");
        System.out.println("=========================");
        StudentSorter.demonstrateAllSortingMethods(students);

        // erweiterte sortierungen mit verknüpfungen demonstrieren
        AdvancedSorter.demonstrateAdvancedSorting(students, courses);
    }

    private static List<Course> generateCourses(int count) {
        List<Course> courses = new ArrayList<>();
        String[] courseNames = {
            "Mathematik", "Physik", "Chemie", "Biologie", 
            "Informatik", "Geschichte", "Literatur", "Kunst", "Musik", "Sport"
        };
        
        for (int i = 0; i < count; i++) {
            Course course = new Course();
            course.setCourseId("K" + (i + 1));
            course.setName(courseNames[i % courseNames.length]);
            course.setInstructor("Prof. " + courseNames[i % courseNames.length]);
            course.setStartTime(LocalDateTime.now().plusDays(i));
            course.setMaxStudents(30);
            course.setEnrolledStudents(new ArrayList<>());
            course.setIsActive(true);
            courses.add(course);
        }
        return courses;
    }

    private static List<Student> generateStudents(int count, List<Course> courses) {
        List<Student> students = new ArrayList<>();
        
        for (int i = 0; i < count; i++) {
            Student student = new Student();
            student.setFirstName(getRandomName());
            student.setLastName(getRandomLastName());
            student.setBirthDate(LocalDate.of(1990, 1, 1).plusDays((long) (Math.random() * 5844)));
            student.setCourses(new ArrayList<>());
            student.setGradePointAverage(1.0 + (Math.random() * 5.0));
            student.setSemester((int) (Math.random() * 8) + 1);
            student.setIsActive(Math.random() > 0.5);
            
            // zufällige kurseinschreibungen
            List<Course> enrolledCourses = new ArrayList<>();
            int numCourses = (int) (Math.random() * 5) + 1;
            for (int j = 0; j < numCourses; j++) {
                Course course = courses.get((int) (Math.random() * courses.size()));
                if (!enrolledCourses.contains(course)) {
                    enrolledCourses.add(course);
                    course.getEnrolledStudents().add(student);
                }
            }
            student.setEnrolledCourses(enrolledCourses);
            
            students.add(student);
        }
        return students;
    }

    private static String getRandomName() {
        String[] names = {"Max", "Anna", "Lukas", "Sophie", "David", "Emma", "Tim", "Laura", "Felix", "Sarah"};
        return names[(int) (Math.random() * names.length)];
    }

    private static String getRandomLastName() {
        String[] lastNames = {"Müller", "Schmidt", "Schneider", "Fischer", "Weber", "Meyer", "Wagner", "Becker", "Schulz", "Hoffmann"};
        return lastNames[(int) (Math.random() * lastNames.length)];
    }
} 
