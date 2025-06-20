package ch.bbw.pc;

import java.util.*;

public class Sortierer {
    public static void sortiere(List<Person> personen) {
        // 1. Comparator-Klasse
        personen.sort(new PersonAlterComparator());

        // 2. Anonyme Klasse
        personen.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getGeburtstag().compareTo(o2.getGeburtstag());
            }
        });

        // 3. Lambda
        personen.sort((p1, p2) -> p1.getAdresse().getStadt().compareTo(p2.getAdresse().getStadt()));

        // 4. Comparator-Chain
        personen.sort(Comparator.comparing(Person::getName)
                .thenComparing(Person::getAlter)); // //Comparator-Chain

        // 5. Natural Order
        Collections.sort(personen); // verwendet Comparable

        // 6. Reverse Order
        personen.sort(Comparator.comparing(Person::getName).reversed());
    }
}
