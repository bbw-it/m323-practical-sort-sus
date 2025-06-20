package Model;

import java.math.BigDecimal;

/**
 * Zweite Datenklasse für Assoziation mit Person
 * Erfüllt Anforderung für Assoziation zwischen zwei Klassen
 * Implements Comparable für Natural Order (nach Preis)
 */
public class Fahrzeug implements Comparable<Fahrzeug> {
    private String marke;
    private String modell;
    private int baujahr;
    private BigDecimal preis;
    private FahrzeugTyp typ;

    public Fahrzeug(String marke, String modell, int baujahr, BigDecimal preis, FahrzeugTyp typ) {
        this.marke = marke;
        this.modell = modell;
        this.baujahr = baujahr;
        this.preis = preis;
        this.typ = typ;
    }

    @Override
    public int compareTo(Fahrzeug other) {
        // Requirement: Natural Order Implementation für zweite Klasse
        return this.preis.compareTo(other.preis);
    }

    // Getters
    public String getMarke() { return marke; }
    public String getModell() { return modell; }
    public int getBaujahr() { return baujahr; }
    public BigDecimal getPreis() { return preis; }
    public FahrzeugTyp getTyp() { return typ; }

    @Override
    public String toString() {
        return String.format("%s %s (%d) - %s", marke, modell, baujahr, preis);
    }
}
