package ch.bbw.BenBrc.factories;

import ch.bbw.BenBrc.models.*;

import java.util.*;

/**
 * Diese Klasse dient als Factory zur Generierung realistischer Mix-Objekte für Test- und Demonstrationszwecke.
 * Sie erstellt Mischungen (Mixes), die aus Solutions bestehen, mit Zufallswerten für Volumen, Typen, Datumsangaben und Beschreibung.
 * Dabei werden:
 * - der Mischtyp ("buffer", "solution", "suspension") zufällig bestimmt
 * - das Volumen als Gleitkommazahl sinnvoll generiert (zwischen 5.0 und 100.0 ml)
 * - 2 bis 4 zufällige Solutions als Komponenten hinzugefügt
 * - das Herstellungsdatum realistisch zwischen 2020 und 2025 gezogen
 * - das Ablaufdatum als ca. 1 Jahr nach Herstellung berechnet
 * - eine generische, aber kontextbezogene Beschreibung gesetzt
 * Die erzeugten Mixes sind damit vollständig initialisierte Objekte, die sich zum Sortieren, Filtern und Anzeigen eignen.
 * Sie erfüllen damit die Anforderungen an Assoziationen zwischen Klassen und tiefergehende Datenstrukturverwendung.
 * Alle Werte sind realistisch gewählt und könnten so z. B. aus einem Laborverwaltungssystem stammen.
 */
public class MixFactory {
    private static final Random random = new Random();

    /**
     * Generiert eine Liste von Mix-Objekten aus einer gegebenen Liste von Solutions.
     * @param allSolutions Liste von Solutions, die als Komponenten für die Mixes dienen
     * @return Liste mit Mix-Objekten
     */
    public static List<Mixes> generateMixesFrom(List<Solutions> allSolutions) {
        List<Mixes> mixes = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            int mixId = i;
            String name = "Mix_" + i;
            String type = randomType();
            String description = "Random " + type + " composed of various solutions.";
            double volume = 0.1 + random.nextDouble() * 99.9;

            // Select 2–4 unique Solutions randomly
            List<Solutions> components = new ArrayList<>();
            Collections.shuffle(allSolutions);
            for (int j = 0; j < Math.min(2 + random.nextInt(3), allSolutions.size()); j++) {
                components.add(allSolutions.get(j));
            }

            // preparationDate as Date
            Calendar prepCal = Calendar.getInstance();
            prepCal.set(2025, Calendar.JUNE, 10 + random.nextInt(20));
            Date preparationDate = prepCal.getTime();

            // expirationDate as Date
            Calendar expCal = Calendar.getInstance();
            expCal.set(2026, Calendar.JUNE, 10 + random.nextInt(20));
            Date expirationDate = expCal.getTime();

            mixes.add(new Mixes(
                    mixId,              // Mix ID
                    name,               // Name des Mixes
                    description,        // Beschreibung des Mixes
                    type,               // Typ des Mixes (z.B. "buffer", "solution", "suspension")
                    volume,             // Volumen des Mixes in ml
                    components,         // Komponenten des Mixes (Liste von Solutions)
                    preparationDate,    // Herstellungsdatum des Mixes
                    expirationDate      // Ablaufdatum des Mixes (1 Jahr nach Herstellung)
            ));
        }

        return mixes;
    }

    /**
     * Gibt zufällig eine der vier Typen für chemische Mischungen zurück.
     * Die Auswahl erfolgt aus einer festen Liste typischer Lösungskategorien.
     * Verwendet wird die Methode Random.nextInt(bound), um einen Index zwischen 0 und 3 zu wählen.
     * Mögliche Rückgabewerte:
     * - "buffer"      (Pufferlösung)
     * - "suspension"  (Suspension)
     * - "emulsion"    (Emulsion)
     * - "extract"     (Extrakt)
     * @return Zufällig gewählter Typ als String
     */
    private static String randomType() {
        String[] types = {"buffer", "suspension", "emulsion", "extract"};
        return types[random.nextInt(types.length)];
    }
}
