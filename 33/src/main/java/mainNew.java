import model.Person;
import sort.NachnameComparator;
import util.TestdatenGenerator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class mainNew {
  public static void main(String[] args) {
    List<Person> personen = TestdatenGenerator.generiereTestdaten();

    // Natural Order
    Collections.sort(personen); // nutzt Comparable
    System.out.println("Natural Order:");
    personen.subList(0, 5).forEach(System.out::println);

    // Reverse Order
    personen.sort(Comparator.reverseOrder());
    System.out.println("\nReverse Order:");
    personen.subList(0, 5).forEach(System.out::println);

    // Comparator abgeleitete Klasse
    personen.sort(new NachnameComparator());
    System.out.println("\nNachnameComparator:");
    personen.subList(0, 5).forEach(System.out::println);

    // Anonyme Klasse
    personen.sort(new Comparator<>() {
      @Override
      public int compare(Person p1, Person p2) {
        return Integer.compare(p1.getAlter(), p2.getAlter());
      }
    });
    System.out.println("\nAlter (anonyme Klasse):");
    personen.subList(0, 5).forEach(System.out::println);

    // Lambda Expression
    personen.sort((p1, p2) -> p1.getGeburtsdatum().compareTo(p2.getGeburtsdatum()));
    System.out.println("\nGeburtsdatum (Lambda):");
    personen.subList(0, 5).forEach(System.out::println);

    // Comparator Chain
    personen.sort(Comparator.comparing(Person::getNachname)
      .thenComparing(Person::getVorname)
      .thenComparing(Person::getAlter)); // //Comparator-Chain
    System.out.println("\nComparator Chain:");
    personen.subList(0, 5).forEach(System.out::println);

    // Sortieren mit Assoziation
    personen.sort(Comparator.comparing(p ->
      p.getHaustiere().isEmpty() ? "" : p.getHaustiere().get(0).getName()));
    System.out.println("\nNach Haustiername (wenn vorhanden):");
    personen.subList(0, 5).forEach(System.out::println);
  }
}
