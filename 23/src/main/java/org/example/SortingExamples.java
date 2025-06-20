package org.example;

import java.util.*;

public class SortingExamples {

    public static void sortAll(List<Person> people) {
        System.out.println("\nnatural Order (Comparable - Name):");
        people.stream().sorted().forEach(System.out::println);

        System.out.println("\nreverse Order:");
        people.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);

        System.out.println("\ncomparator class (nach Alter):");
        people.stream().sorted(new AgeComparator()).forEach(System.out::println);

        System.out.println("\nAnonyme Klasse (nach Stadt):");
        people.sort(new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getAddress().getCity().compareTo(p2.getAddress().getCity());
            }
        });
        people.forEach(System.out::println);

        System.out.println("\nLambda Expression (nach Straße):");
        people.sort((p1, p2) -> p1.getAddress().getStreet().compareTo(p2.getAddress().getStreet()));
        people.forEach(System.out::println);

        System.out.println("\nComparator Chain (nach Hundename, dann Hundealter):");
        people.sort(Comparator.comparing((Person p) -> p.getDog().getName())
                .thenComparing(p -> p.getDog().getAge()));
        people.forEach(System.out::println);


        System.out.println("\nach Hundenamen (assoziiertes Objekt):");
        people.sort(Comparator.comparing(p -> p.getDog().getName()));
        people.forEach(System.out::println);

    }
}

