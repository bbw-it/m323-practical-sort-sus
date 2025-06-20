package Comparator;

import Model.Person;
import java.util.Comparator;

/**
 * Comparator als abgeleitete Klasse für Einkommensvergleich
 * Erfüllt Anforderung: Comparator-Attribute in der Datenklasse
 */
public class EinkommenComparator implements Comparator<Person> {
    
    private final boolean reverse;
    
    /**
     * Konstruktor mit Option zur Umkehrung der Sortierreihenfolge
     * @param reverse Wenn true, wird die Sortierreihenfolge umgekehrt
     */
    public EinkommenComparator(boolean reverse) {
        this.reverse = reverse;
    }
    
    /**
     * Standardkonstruktor für aufsteigende Sortierung
     */
    public EinkommenComparator() {
        this(false);
    }
    
    @Override
    public int compare(Person p1, Person p2) {
        int result = p1.getMonatsEinkommen().compareTo(p2.getMonatsEinkommen());
        return reverse ? -result : result;
    }
}
