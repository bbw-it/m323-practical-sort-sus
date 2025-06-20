package ch.bbw.BenBrc.factories;

import ch.bbw.BenBrc.models.*;

import java.util.*;
/**
 * Factory-Klasse zur Generierung zufälliger Solutions-Objekte.
 * Diese Klasse erzeugt realistische Testdaten für Lösungen,
 * basierend auf Compound-Daten. Sie ergänzt die Verbindungen mit weiteren
 * chemischen Informationen wie Klassifikation oder SMILES.
 * author: Benedict Brück
 * version: 1.0
 * date: 19.06.25
 */
public class SolutionFactory {
    private static final Random random = new Random();

    /**
     * Generiert eine Liste von Solution-Objekten aus einer gegebenen Compound-Liste.
     * @param "compounds" Liste von Basisverbindungen (Compounds)
     * @return Liste mit zugehörigen Solutions
     */
    public static List<Solutions> generateSolutionsFrom(List<Compounds> baseCompounds) {
        List<Solutions> list = new ArrayList<>();
        for (Compounds c : baseCompounds) {
            double weight = c.getMolecularWeight();
            double mass = weight + (random.nextDouble() * 0.02 - 0.01); // ±0.01 variation
            Calendar cal = Calendar.getInstance();
            cal.set(2025, Calendar.JUNE, random.nextInt(30) + 1);

            list.add(new Solutions(
                    c.getCompound_CID(),                              // CID bleibt identisch
                    c.getName() + "_Sol",                             // Name wird als Lösung gekennzeichnet
                    c.getFormula(),                                   // Molekülformel übernehmen
                    c.getMolecularWeight(),                           // Molekulargewicht
                    c.getMolecularMass() + 0.01,                      // kleine Variation zur Masse
                    c.getSMILES(),                                    // SMILES aus Compound übernehmen
                    c.getIUPACName(),                                 // IUPAC
                    List.of(c.getName() + "_alias"),            // Synonyme als Liste
                    c.getInChIKey(),                                  // InChIKey
                    generateClassification(),                         // Klassifikation (organisch/anorganisch)
                    randomDateBetween(2025, 2025)               // Erzeugungsdatum
            ));
        }
        return list;
    }

    /**
     * Generiert ein zufälliges Datum zwischen zwei Jahren.
     * @param "startYear" Startjahr (inklusive)
     * @param "endYear" Endjahr (inklusive)
     * @return Zufälliges Datum innerhalb des angegebenen Jahresbereichs
     */
    private static Date randomDateBetween(int i, int i1) {
        Calendar cal = Calendar.getInstance();
        cal.set(i, Calendar.JANUARY, 1);
        Date startDate = cal.getTime();
        cal.set(i1, Calendar.DECEMBER, 31);
        Date endDate = cal.getTime();

        long randomMillis = startDate.getTime() + (long) (Math.random() * (endDate.getTime() - startDate.getTime()));
        return new Date(randomMillis);
    }

    /**
     * Generiert eine zufällige Klassifikation für chemische Lösungen.
     * Diese Methode simuliert eine 50/50-Verteilung zwischen organischen und anorganischen Verbindungen.
     * @return "organisch" oder "anorganisch"
     */
    private static String generateClassification() {
        return random.nextBoolean() ? "organisch" : "anorganisch"; // 50/50 Chance für organisch/anorganisch
    }
}

