package Comparator;

import Model.Person;
import java.util.Comparator;

/**
 * Comparator als abgeleitete Klasse für Altersvergleich
 * Erfüllt Anforderung: Vom Comparator abgeleitete Klasse
 */
public class AlterComparator implements Comparator<Person> {
    
    @Override
    public int compare(Person p1, Person p2) {
        return Integer.compare(p1.getAlter(), p2.getAlter());
    }
}
