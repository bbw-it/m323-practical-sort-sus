package ch.bbw.vn;

import ch.bbw.vn.models.Person;
import ch.bbw.vn.services.DataGenerator;
import ch.bbw.vn.services.PersonSorter;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> personen = DataGenerator.generatePersonen(100);

        System.out.println("------ Nach Alter (eigene Comparator-Klasse) ------");
        personen.sort(new PersonSorter.SortByAlter());
        personen.forEach(System.out::println);

        System.out.println("\n------ Nach Geburtsdatum (anonyme Klasse) ------");
        PersonSorter.sortByGeburtsdatum(personen);
        personen.forEach(System.out::println);

        System.out.println("\n------ Nach Name (Lambda) ------");
        PersonSorter.sortByNameLambda(personen);
        personen.forEach(System.out::println);

        System.out.println("\n------ Nach Name + Alter (Comparator-Chain) ------");
        PersonSorter.sortByNameThenAlter(personen);
        personen.forEach(System.out::println);

        System.out.println("\n------ Nach Natural Order (Name) ------");
        PersonSorter.sortByNaturalOrder(personen);
        personen.forEach(System.out::println);

        System.out.println("\n------ Nach Name rückwärts (Reverse Order) ------");
        PersonSorter.sortByNameReverse(personen);
        personen.forEach(System.out::println);

        System.out.println("\n------ Nach Anzahl Haustiere (Sortieren mit Assoziation) ------");
        PersonSorter.sortByAnzahlHaustiere(personen);
        personen.forEach(System.out::println);

    }
}
