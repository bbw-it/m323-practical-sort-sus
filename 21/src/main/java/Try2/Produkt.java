package Try2;
import java.util.Date;

public class Produkt implements Comparable<Produkt> {
    String name;
    double preis;
    String kategorie;
    Hersteller hersteller;
    Date erscheinungsdatum;

    public Produkt(String name, double preis, String kategorie, Hersteller hersteller, Date erscheinungsdatum) {
        this.name = name;
        this.preis = preis;
        this.kategorie = kategorie;
        this.hersteller = hersteller;
        this.erscheinungsdatum = erscheinungsdatum;
    }

    @Override
    public int compareTo(Produkt o) {
        return Double.compare(this.preis, o.preis);
    }

    @Override
    public String toString() {
        return name + " (" + preis + " CHF)";
    }
}
