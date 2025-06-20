package Generator;

import Model.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Datengenerator - erzeugt zufällige Testdaten für die Sortierdemonstration
 * Durch Seed-Verwendung reproduzierbar (Anforderung: mind. 100 Datensätze)
 */
public class DataGenerator {
    private final Random random;
    private final String[] vornamen = {"Anna", "Ben", "Clara", "David", "Emma", "Felix", "Greta", "Hans",
            "Iris", "Jan", "Karin", "Lars", "Maria", "Nils", "Olga", "Peter",
            "Quentin", "Rita", "Stefan", "Tina", "Ulrich", "Vera", "Werner", "Xaver"};
    private final String[] nachnamen = {"Müller", "Schmidt", "Schneider", "Fischer", "Weber", "Meyer",
            "Wagner", "Becker", "Schulz", "Hoffmann", "Koch", "Richter",
            "Klein", "Wolf", "Schröder", "Neumann", "Schwarz", "Zimmermann"};
    private final String[] autoMarken = {"VW", "BMW", "Mercedes", "Audi", "Opel", "Ford", "Toyota", "Honda"};
    private final String[] autoModelle = {"Golf", "3er", "C-Klasse", "A4", "Corsa", "Focus", "Corolla", "Civic"};

    public DataGenerator(long seed) {
        this.random = new Random(seed);
    }

    /**
     * Generiert eine Liste von Personen mit zufälligen Eigenschaften
     * Erfüllt Anforderung: mind. 100 zufällig generierte Datensätze
     * 
     * @param anzahl Anzahl zu generierender Personen
     * @return Liste mit Personenobjekten
     */
    public List<Person> generatePersonen(int anzahl) {
        List<Person> personen = new ArrayList<>();

        for (int i = 0; i < anzahl; i++) {
            String name = vornamen[random.nextInt(vornamen.length)] + " " +
                    nachnamen[random.nextInt(nachnamen.length)];

            LocalDate geburtsdatum = LocalDate.now().minusYears(18 + random.nextInt(60))
                    .minusDays(random.nextInt(365));

            BigDecimal einkommen = BigDecimal.valueOf(2000 + random.nextInt(8000))
                    .setScale(2, BigDecimal.ROUND_HALF_UP);

            BerufsStatus status = BerufsStatus.values()[random.nextInt(BerufsStatus.values().length)];

            List<Fahrzeug> fahrzeuge = generateFahrzeuge(random.nextInt(4)); // 0-3 Fahrzeuge

            personen.add(new Person(name, geburtsdatum, einkommen, status, fahrzeuge));
        }

        return personen;
    }

    /**
     * Generiert eine Liste von Fahrzeugen mit zufälligen Eigenschaften
     * 
     * @param anzahl Anzahl zu generierender Fahrzeuge
     * @return Liste mit Fahrzeugobjekten
     */
    private List<Fahrzeug> generateFahrzeuge(int anzahl) {
        List<Fahrzeug> fahrzeuge = new ArrayList<>();

        for (int i = 0; i < anzahl; i++) {
            String marke = autoMarken[random.nextInt(autoMarken.length)];
            String modell = autoModelle[random.nextInt(autoModelle.length)];
            int baujahr = 2000 + random.nextInt(24); // 2000-2023
            BigDecimal preis = BigDecimal.valueOf(10000 + random.nextInt(90000))
                    .setScale(2, BigDecimal.ROUND_HALF_UP);
            FahrzeugTyp typ = FahrzeugTyp.values()[random.nextInt(FahrzeugTyp.values().length)];

            fahrzeuge.add(new Fahrzeug(marke, modell, baujahr, preis, typ));
        }

        return fahrzeuge;
    }
}
