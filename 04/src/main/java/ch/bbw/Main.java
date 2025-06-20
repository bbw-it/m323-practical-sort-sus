package ch.bbw;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import ch.bbw.model.*;
import ch.bbw.util.DataGenerator;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Daten generieren
        List<Person> persons = DataGenerator.generatePersons(100);
        List<Dog> dogs = DataGenerator.generateDogs(100, persons);

        // --- Natural Order (Comparable: Name) ---
        persons.sort(null); // Natural Order
        System.out.println("--- Persons nach Name (Natural Order, Comparable) ---");
        persons.stream().limit(5).forEach(System.out::println);

        // --- Reverse Order (Comparable: Name) ---
        persons.sort(Collections.reverseOrder()); // Reverse Natural Order
        System.out.println("--- Persons nach Name (Reverse Order) ---");
        persons.stream().limit(5).forEach(System.out::println);

        // --- Comparator als separate Klasse: nach Alter ---
        persons.sort(new PersonAgeComparator()); // Comparator-Klasse
        System.out.println("--- Persons nach Alter (Comparator-Class) ---");
        persons.stream().limit(5).forEach(System.out::println);

        // --- Anonyme Klasse: nach Größe ---
        persons.sort(new Comparator<Person>() { // Anonyme Klasse
            @Override
            public int compare(Person o1, Person o2) {
                return Double.compare(o1.getHeight(), o2.getHeight());
            }
        });
        System.out.println("--- Persons nach Größe (Anonymous Comparator) ---");
        persons.stream().limit(5).forEach(System.out::println);

        // --- Lambda: nach Geburtstag ---
        persons.sort((a, b) -> a.getBirthday().compareTo(b.getBirthday())); // Lambda
        System.out.println("--- Persons nach Geburtstag (Lambda Comparator) ---");
        persons.stream().limit(5).forEach(System.out::println);

        // --- Comparator-Chain: nach Stadt, dann Name ---
        persons.sort(Comparator.comparing((Person p) -> p.getAddress().getCity())
                .thenComparing(Person::getName)); // Comparator-Chain
        System.out.println("--- Persons nach Stadt, dann Name (Comparator-Chain) ---");
        persons.stream().limit(5).forEach(System.out::println);

        // --- Mehrstufige Sortierung: nach Land, dann Alter, dann Größe ---
        persons.sort(Comparator.comparing((Person p) -> p.getAddress().getCountry())
                .thenComparing(Person::getAge)
                .thenComparing(Person::getHeight)); // Comparator-Chain
        System.out.println("--- Persons nach Land, Alter, Größe (Mehrstufig) ---");
        persons.stream().limit(5).forEach(System.out::println);

        // --- Dog: nach Name (Lambda) ---
        dogs.sort(Comparator.comparing(Dog::getName)); // Lambda
        System.out.println("--- Dogs nach Name (Lambda Comparator) ---");
        dogs.stream().limit(5).forEach(System.out::println);

        // --- Dog: nach Gewicht (Reverse Order) ---
        dogs.sort(Comparator.comparing(Dog::getWeight).reversed()); // Reverse Order
        System.out.println("--- Dogs nach Gewicht (Reverse Order) ---");
        dogs.stream().limit(5).forEach(System.out::println);

        // --- Dog: nach Owner-Name, dann nach Geburtsdatum des Hundes ---
        dogs.sort(Comparator.comparing((Dog d) -> d.getOwner().getName())
                .thenComparing(Dog::getBirthDate)); // Comparator-Chain
        System.out.println("--- Dogs nach Owner-Name, dann Geburtsdatum (Comparator-Chain) ---");
        dogs.stream().limit(5).forEach(System.out::println);
    }
}

// Comparator-Klasse für Alter
class PersonAgeComparator implements Comparator<Person> { // Comparator-Class
    @Override
    public int compare(Person o1, Person o2) {
        return Integer.compare(o1.getAge(), o2.getAge());
    }
}