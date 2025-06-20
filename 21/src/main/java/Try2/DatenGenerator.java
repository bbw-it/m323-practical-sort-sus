package Try2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class DatenGenerator {
    private static final String[] NAMEN = {"Alpha", "Beta", "Gamma", "Delta", "Epsilon"};
    private static final String[] KATEGORIEN = {"Elektronik", "Haushalt", "Sport", "Bücher", "Kleidung"};
    private static final String[] HERSTELLER_NAMEN = {"HerstellerA", "HerstellerB", "HerstellerC"};
    private static final String[] HERSTELLER_LAENDER = {"Schweiz", "Deutschland", "USA"};
    private static final Random RAND = new Random(42);  // festes Seed für Reproduzierbarkeit

    public static List<Produkt> generiereProdukte(int anzahl) {
        List<Produkt> produkte = new ArrayList<>();
        for (int i = 0; i < anzahl; i++) {
            String name = NAMEN[RAND.nextInt(NAMEN.length)] + " " + (i + 1);
            double preis = 10 + RAND.nextDouble() * 490;  // Preis zwischen 10 und 500
            String kategorie = KATEGORIEN[RAND.nextInt(KATEGORIEN.length)];
            Hersteller hersteller = new Hersteller(
                HERSTELLER_NAMEN[RAND.nextInt(HERSTELLER_NAMEN.length)],
                HERSTELLER_LAENDER[RAND.nextInt(HERSTELLER_LAENDER.length)]
            );
            Date erscheinungsdatum = new Date(System.currentTimeMillis() - RAND.nextInt(1000*60*60*24*365)); // letztes Jahr

            produkte.add(new Produkt(name, preis, kategorie, hersteller, erscheinungsdatum));
        }
        return produkte;
    }
}


