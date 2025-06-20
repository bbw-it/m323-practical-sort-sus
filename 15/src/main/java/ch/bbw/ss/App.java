package ch.bbw.ss;

import ch.bbw.ss.Model.Person;
import ch.bbw.ss.TestData.DataProvider;

import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        var data = new java.util.ArrayList<>(Objects.requireNonNull(DataProvider.GetData()));

        System.out.println("\n---------------Natural Order (Name)---------------");
        Collections.sort(data);
        data.forEach(System.out::println);
        
        System.out.println("\n---------------Reverse Order (Name)---------------");
        data.sort(Comparator.reverseOrder());
        data.forEach(System.out::println);

        System.out.println("\n---------------normale Sortierung (Geburtsdatum)---------------");
        data.sort(Comparator.comparing(Person::birthDate));
        data.forEach(System.out::println);

        System.out.println("\n---------------Sortierung mit anonymer Klasse (Ort)---------------");
        data.sort(new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.location().city().compareTo(p2.location().city());
            }
        });
        data.forEach(System.out::println);

        System.out.println("\n---------------Sortierung Lambda (Kontakt)---------------");
        data.sort((p1, p2) -> p1.contact().email().compareTo(p2.contact().email()));
        data.forEach(System.out::println);

        System.out.println("\n---------------Mehrstufige Sortierung (Ort, Name)---------------");
        data.sort(Comparator
                .comparing((Person p) -> p.location().city())
                .thenComparing(Person::name));
        data.forEach(System.out::println);

        System.out.println("\n---------------Sortierung nach Anzahl Freunde (Comparator-Klasse)---------------");
        data.sort(new PersonFirstFriendComparator());
        data.forEach(System.out::println);
    }

    public static class PersonFirstFriendComparator implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            return p1.friends().get(0).compareTo(p2.friends().get(0));
        }
    }
}
