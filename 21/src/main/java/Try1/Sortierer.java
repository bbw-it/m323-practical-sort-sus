package Try1;

import java.util.Comparator;
import java.util.List;

public class Sortierer {
    public static void sortiere(List<Produkt> produkte) {
        // Comparator-Klasse
        class PreisVergleicher implements Comparator<Produkt> {
            public int compare(Produkt p1, Produkt p2) {
                return Double.compare(p1.getPreis(), p2.getPreis());
            }
        }
        produkte.sort(new PreisVergleicher());

        // Anonyme Klasse
        produkte.sort(new Comparator<Produkt>() {
            public int compare(Produkt p1, Produkt p2) {
                return p1.getHerstellungsdatum().compareTo(p2.getHerstellungsdatum());
            }
        });

        // Lambda
        produkte.sort((p1, p2) -> p1.getKategorie().compareTo(p2.getKategorie()));

        // Comparator-Chain
        produkte.sort(Comparator.comparing(Produkt::getKategorie)
                .thenComparing(Produkt::getPreis));

        // Reverse Order
        produkte.sort(Comparator.comparing(Produkt::getName).reversed());
    }
}
