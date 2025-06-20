package Try1;

import java.time.LocalDate;
import java.util.List;

public class Produkt implements Comparable<Produkt> {
    private String name;
    private double preis;
    private LocalDate herstellungsdatum;
    private Kategorie kategorie;
    private List<String> tags;

    public Produkt(String name, double preis, LocalDate herstellungsdatum, Kategorie kategorie, List<String> tags) {
        this.name = name;
        this.preis = preis;
        this.herstellungsdatum = herstellungsdatum;
        this.kategorie = kategorie;
        this.tags = tags;
    }

    // Getter, Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public LocalDate getHerstellungsdatum() {
        return herstellungsdatum;
    }

    public void setHerstellungsdatum(LocalDate herstellungsdatum) {
        this.herstellungsdatum = herstellungsdatum;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }

    public void setKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    // toString-Methode
    @Override
    public String toString() {
        return name + ", " + preis + ", " + herstellungsdatum + ", " + kategorie + ", " + tags;
    }


    // Natural Order: nach Name
    @Override
    public int compareTo(Produkt o) {
        return this.name.compareTo(o.name);
    }
}

enum Kategorie {
    ELEKTRONIK, HAUSHALT, SPIELZEUG, LEBENSMITTEL
}
