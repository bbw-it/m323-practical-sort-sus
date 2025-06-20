package org.example;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        List<Person> people = DataGenerator.generatePeople(100);
        SortingExamples.sortAll(people);
    }
}
