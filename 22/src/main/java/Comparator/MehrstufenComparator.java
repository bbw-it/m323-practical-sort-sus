package Comparator;

import Model.Person;
import java.util.Comparator;

/**
 * Mehrstufiger Comparator
 * Erfüllt Anforderung: Mehrstufige Sortierungen
 */
public class MehrstufenComparator implements Comparator<Person> {
    
    @Override
    public int compare(Person p1, Person p2) {
        // Erster Vergleich: Nach Berufsstatus
        int result = p1.getBerufsStatus().compareTo(p2.getBerufsStatus());
        if (result != 0) {
            return result;
        }
        
        // Zweiter Vergleich: Nach Einkommen (absteigend)
        result = p2.getMonatsEinkommen().compareTo(p1.getMonatsEinkommen());
        if (result != 0) {
            return result;
        }
        
        // Dritter Vergleich: Nach Name
        return p1.getName().compareTo(p2.getName());
    }
}
