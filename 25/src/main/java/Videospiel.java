import java.util.*;
import java.time.LocalDate;

// Datenklasse mit 5 Attributen, davon 4 unterschiedliche Typen inkl. 2 komplexe
class Videospiel implements Comparable<Videospiel> {
    String titel;
    double bewertung;
    LocalDate releaseDatum;
    Entwickler entwickler; // nicht-primitiv
    List<Plattform> plattformen; // nicht-primitiv, Assoziation

    public Videospiel(String titel, double bewertung, LocalDate releaseDatum, Entwickler entwickler, List<Plattform> plattformen) {
        this.titel = titel;
        this.bewertung = bewertung;
        this.releaseDatum = releaseDatum;
        this.entwickler = entwickler;
        this.plattformen = plattformen;
    }

    // Natural Order: alphabetisch nach Titel
    @Override
    public int compareTo(Videospiel o) {
        return this.titel.compareTo(o.titel);
    }

    @Override
    public String toString() {
        return titel + ", Bewertung: " + bewertung + ", Release: " + releaseDatum +
                ", Entwickler: " + entwickler + ", Plattformen: " + plattformen;
    }
}