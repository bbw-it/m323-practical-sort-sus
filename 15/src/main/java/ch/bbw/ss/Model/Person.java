package ch.bbw.ss.Model;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

public record Person(String name, Contact contact, Location location, Date birthDate, List<Person> friends)
        implements Comparable {

    @Override
    public int compareTo(Object o) {
        var p = (Person)o;
        return birthDate.compareTo(p.birthDate);
    }

    static Comparator<Person> byName = new Comparator<Person>() {
        @Override
        public int compare(Person p1, Person p2) {
            return CharSequence.compare(p1.name, p2.name);
        }
    };

    static Comparator<Person> byContactByPhone = new Comparator<Person>() {
        @Override
        public int compare(Person p1, Person p2) {
            return CharSequence.compare(p1.contact.phone(), p2.contact.phone());
        }
    };

    static Comparator<Person> byContactByEmail = new Comparator<Person>() {
        @Override
        public int compare(Person p1, Person p2) {
            return CharSequence.compare(p1.contact.email(), p2.contact.email());
        }
    };

    static Comparator<Person> byLocation = new Comparator<Person>() {
        @Override
        public int compare(Person p1, Person p2) {
            return p1.location.compareTo(p2.location);
        }
    };

    static Comparator<Person> byBirthDate = new Comparator<Person>() {
        @Override
        public int compare(Person p1, Person p2) {
            return p1.birthDate.compareTo(p2.birthDate);
        }
    };

    static Comparator<Person> byFriends = new Comparator<Person>() {
        @Override
        public int compare(Person p1, Person p2) {
            return Integer.compare(p1.friends.size(), p2.friends.size());
        }
    };
}
