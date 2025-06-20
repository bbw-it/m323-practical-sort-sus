package ch.bbw.vn.services;

import ch.bbw.vn.models.Person;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PersonSorter {

    // 1. Eigene Comparator-Klasse
    public static class SortByAlter implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            return Integer.compare(p1.getAlter(), p2.getAlter());
        }
    }

    // 2. Anonyme Klasse
    public static void sortByGeburtsdatum(List<Person> personen) {
        personen.sort(new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getGeburtsdatum().compareTo(p2.getGeburtsdatum()); //Anonyme Klasse
            }
        });
    }

    // 3. Lambda Expression
    public static void sortByNameLambda(List<Person> personen) {
        personen.sort((p1, p2) -> p1.getName().compareTo(p2.getName())); //Lambda Expression
    }

    // 4. Comparator Chain
    public static void sortByNameThenAlter(List<Person> personen) {
        personen.sort(
                Comparator.comparing(Person::getName)
                        .thenComparing(Person::getAlter) //Comparator-Chain
        );
    }

    // 5. Natural Order (compareTo in Person.java)
    public static void sortByNaturalOrder(List<Person> personen) {
        Collections.sort(personen); //Natural Order
    }

    // 6. Reverse Order
    public static void sortByNameReverse(List<Person> personen) {
        personen.sort(Comparator.comparing(Person::getName).reversed()); //Reverse Order
    }

    // 7. Sortieren nach Anzahl Haustiere
    public static void sortByAnzahlHaustiere(List<Person> personen) {
        personen.sort(Comparator.comparingInt(p -> p.getHaustiere().size())); //Sortieren mit Assoziation
    }

}
