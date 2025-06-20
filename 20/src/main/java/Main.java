package ch.bbw.sortieren;

import ch.bbw.sortieren.comparators.AgeComparator;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Student> students = DataGenerator.generateStudents(100);

        System.out.println("🔢 Sortiert nach Alter (eigene Comparator-Klasse):");
        students.sort(new AgeComparator());
        students.stream().limit(5).forEach(System.out::println);

        System.out.println("\n🗓️ Sortiert nach Einschreibedatum (anonyme Klasse):");
        students.sort(new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                return s1.getEnrollmentDate().compareTo(s2.getEnrollmentDate());
            }
        });
        students.stream().limit(5).forEach(System.out::println);

        System.out.println("\n💬 Sortiert nach GPA (Lambda):");
        students.sort((s1, s2) -> Double.compare(s1.getGpa(), s2.getGpa()));
        students.stream().limit(5).forEach(System.out::println);

        System.out.println("\n📚 Sortiert nach Kursanzahl (Lambda):");
        students.sort(Comparator.comparing(s -> s.getCourses().size()));
        students.stream().limit(5).forEach(System.out::println);

        System.out.println("\n🧠 Mehrstufige Sortierung: GPA → Name:");
        students.sort(Comparator.comparing(Student::getGpa).thenComparing(Student::getName));
        students.stream().limit(5).forEach(System.out::println);

        System.out.println("\n🔤 Natural Order (Name):");
        Collections.sort(students);
        students.stream().limit(5).forEach(System.out::println);

        System.out.println("\n🔁 Reverse Order (Name):");
        Collections.sort(students, Comparator.reverseOrder());
        students.stream().limit(5).forEach(System.out::println);
    }
}