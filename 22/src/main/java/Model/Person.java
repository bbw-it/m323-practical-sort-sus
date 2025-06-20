package Model;

import java.time.LocalDate;
import java.time.Period;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Hauptdatenklasse - erfüllt alle Anforderungen:
 * - 5 Attribute: name, geburtsdatum, monatsEinkommen, berufsStatus, fahrzeuge
 * - 4 verschiedene Datentypen: String, LocalDate, BigDecimal, BerufsStatus(Enum), List<Fahrzeug>
 * - 2 nicht-primitive: BigDecimal, List<Fahrzeug>
 * 
 * Implements Comparable für Natural Order (Requirement: Natural Order)
 */
public class Person implements Comparable<Person> {
    private String name;                    // String (primitiv)
    private LocalDate geburtsdatum;         // LocalDate (nicht-primitiv)  
    private BigDecimal monatsEinkommen;     // BigDecimal (nicht-primitiv)
    private BerufsStatus berufsStatus;      // Enum (nicht-primitiv)
    private List<Fahrzeug> fahrzeuge;       // List<Fahrzeug> (nicht-primitiv)

    public Person(String name, LocalDate geburtsdatum, BigDecimal monatsEinkommen,
                  BerufsStatus berufsStatus, List<Fahrzeug> fahrzeuge) {
        this.name = name;
        this.geburtsdatum = geburtsdatum;
        this.monatsEinkommen = monatsEinkommen;
        this.berufsStatus = berufsStatus;
        this.fahrzeuge = fahrzeuge != null ? fahrzeuge : new ArrayList<>();
    }

    // Natural Order Implementation - sortiert nach Name
    @Override
    public int compareTo(Person other) {
        // Requirement: Natural Order Implementation
        return this.name.compareTo(other.name);
    }

    // Getters
    public String getName() { return name; }
    public LocalDate getGeburtsdatum() { return geburtsdatum; }
    public BigDecimal getMonatsEinkommen() { return monatsEinkommen; }
    public BerufsStatus getBerufsStatus() { return berufsStatus; }
    public List<Fahrzeug> getFahrzeuge() { return fahrzeuge; }

    public int getAlter() {
        return Period.between(geburtsdatum, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return String.format("Person{name='%s', alter=%d, einkommen=%s, status=%s, fahrzeuge=%d}",
                name, getAlter(), monatsEinkommen, berufsStatus, fahrzeuge.size());
    }
}
