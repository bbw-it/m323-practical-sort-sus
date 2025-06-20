package ch.bbw.sortieren;

import java.time.LocalDate;
import java.util.*;

public class DataGenerator {
    private static final Random rnd = new Random();

    public static List<Student> generateStudents(int count) {
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String name = "Student" + (i + 1);
            int age = rnd.nextInt(10) + 18;
            double gpa = Math.round((rnd.nextDouble() * 4) * 100.0) / 100.0;
            LocalDate date = LocalDate.of(2020 + rnd.nextInt(5), 1 + rnd.nextInt(12), 1 + rnd.nextInt(28));
            List<String> courses = List.of("Math", "Physics", "CS", "English", "History");
            // TODO: shuffle does not work here somehow
            //Collections.shuffle(courses);
            list.add(new Student(name, age, gpa, date, courses.subList(0, rnd.nextInt(3) + 1)));
        }
        return list;
    }
}