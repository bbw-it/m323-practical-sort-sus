package ch.bbw.pc;

import java.util.Comparator;

public class PersonAlterComparator implements Comparator<Person> { // Comparator-Klasse
    @Override
    public int compare(Person p1, Person p2) {
        return Integer.compare(p1.getAlter(), p2.getAlter());
    }
}
