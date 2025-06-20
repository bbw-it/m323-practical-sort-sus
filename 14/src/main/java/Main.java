import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import org.instancio.*;

public class Main {
    public static void main(String[] args) {
        // Seed f�r reproduzierbare Daten
        InstancioApi.setSeed(42);

        // Datengenerator
        List<Person> people = Instancio.ofList(Person.class)
                .size(100)
                .supply(field(Person::getName), gen -> gen.names().fullName())
                .generate(field(Person::getAge), gen -> gen.ints().range(18, 65))
                .generate(field(Person::getSalary), gen -> gen.doubles().range(30000, 120000))
                .generate(field(Person::getBirthDate), gen -> gen.temporal().localDate().past(50, ChronoUnit.YEARS))
                .supply(field(Person::getAddress), () -> {
                    String street = "Street " + new Random().nextInt(100);
                    String city = "City" + new Random().nextInt(10);
                    String zip = "100" + new Random().nextInt(90);
                    return new Address(street, city, zip);
                })
                .create();

        // Comparator abgeleitete Klasse
        people.sort(new AgeComparator());
        System.out.println("Nach Alter sortiert:");
        people.stream().limit(5).forEach(System.out::println);

        // Anonyme Klasse
        people.sort(new Comparator<>() {
            @Override
            public int compare(Person p1, Person p2) {
                return Double.compare(p1.getSalary(), p2.getSalary());
            }
        });
        System.out.println("\nNach Gehalt sortiert (anonyme Klasse):");
        people.stream().limit(5).forEach(System.out::println);

        // Lambda Expression
        people.sort((p1, p2) -> p1.getBirthDate().compareTo(p2.getBirthDate()));
        System.out.println("\nNach Geburtsdatum sortiert (Lambda):");
        people.stream().limit(5).forEach(System.out::println);

        // Comparator Chain
        Comparator<Person> chain = Comparator.comparing(Person::getCity)
                .thenComparing(Person::getAge)
                .thenComparing(Person::getSalary); // Comparator-Chain
        people.sort(chain);
        System.out.println("\nNach Stadt, Alter, Gehalt sortiert (Comparator-Chain):");
        people.stream().limit(5).forEach(System.out::println);

        // Natural Order
        Collections.sort(people); // Natural Order by Name (Comparable)
        System.out.println("\nNatural Order (nach Name):");
        people.stream().limit(5).forEach(System.out::println);

        // Reverse Order
        List<Person> reversed = people.stream()
                .sorted(Comparator.reverseOrder()) // Reverse Order
                .collect(Collectors.toList());
        System.out.println("\nReverse Order (nach Name r�ckw�rts):");
        reversed.stream().limit(5).forEach(System.out::println);

        // Sortieren nach Attribut in der Assoziation
        Comparator<Person> cityComparator = Comparator.comparing(p -> p.getAddress().getCity());
        people.sort(cityComparator);
        System.out.println("\nNach Stadt (Assoziation) sortiert:");
        people.stream().limit(5).forEach(System.out::println);
    }
}
