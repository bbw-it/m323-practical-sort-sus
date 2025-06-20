package Comparator;

import Model.Fahrzeug;
import Model.Person;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;

/**
 * Komparatoren für die Assoziation zwischen Person und Fahrzeug
 * Erfüllt Anforderung: Sortierung mit Bezug auf Assoziation
 */
public class FahrzeugAssoziationsComparator {
    
    /**
     * Vergleicht Personen anhand des teuersten Fahrzeugs
     * @return Comparator für den Vergleich
     */
    public static Comparator<Person> byTeuerstenFahrzeug() {
        return (p1, p2) -> {
            BigDecimal max1 = p1.getFahrzeuge().stream()
                    .map(Fahrzeug::getPreis)
                    .max(BigDecimal::compareTo)
                    .orElse(BigDecimal.ZERO);
                    
            BigDecimal max2 = p2.getFahrzeuge().stream()
                    .map(Fahrzeug::getPreis)
                    .max(BigDecimal::compareTo)
                    .orElse(BigDecimal.ZERO);
                    
            return max1.compareTo(max2);
        };
    }
    
    /**
     * Vergleicht Personen anhand des durchschnittlichen Fahrzeugalters
     * @return Comparator für den Vergleich
     */
    public static Comparator<Person> byDurchschnittlichesFahrzeugalter() {
        return (p1, p2) -> {
            double avg1 = p1.getFahrzeuge().stream()
                    .mapToInt(f -> LocalDate.now().getYear() - f.getBaujahr())
                    .average()
                    .orElse(0.0);
                    
            double avg2 = p2.getFahrzeuge().stream()
                    .mapToInt(f -> LocalDate.now().getYear() - f.getBaujahr())
                    .average()
                    .orElse(0.0);
                    
            return Double.compare(avg1, avg2);
        };
    }
}
