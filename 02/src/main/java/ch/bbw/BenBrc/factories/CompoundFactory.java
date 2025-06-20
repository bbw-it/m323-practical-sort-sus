package ch.bbw.BenBrc.factories;

import ch.bbw.BenBrc.models.Compounds;

import java.util.*;
/**
 * Factory-Klasse zur Generierung zufälliger Compound-Objekte.
 * Diese Klasse dient zur Erzeugung synthetischer Daten für Tests oder Demonstrationen.
 * Es werden realitätsnahe Werte erzeugt, inspiriert von chemischen Datenbanken wie PubChem.
 * author: Benedict Brück
 * version: 1.0
 * date: 19.06.25
 */
public class CompoundFactory {
    private static final Random random = new Random();

    /**
     * Generiert eine Liste zufälliger Compound-Objekte mit chemisch sinnvollen Daten.
     * @param count Die Anzahl der zu erzeugenden Compound-Objekte
     * @return Liste mit erzeugten {@link Compounds}-Objekten
     */
    public static List<Compounds> generateCompounds(int count) {
        List<Compounds> list = new ArrayList<>();

        for (int i = 1; i <= count; i++) {
            int cid = i;
            String name = "Compound_" + i;
            String formula = randomFormula();
            String casNumber = String.format("%03d-%02d-%1d", i, i % 100, i % 10);  // Beispiel-CAS-Nummer, hier stark vereinfacht
            String description = "Generated compound " + i;                         // Beispielbeschreibung, hier stark vereinfacht
            String pubChemId = "CID" + i;                                           // Beispiel-PubChem-ID, hier stark vereinfacht
            double molecularMass = 100 + random.nextDouble() * 200;                 // Beispiel-Molekülmasse, hier stark vereinfacht
            double molecularWeight = 90 + random.nextDouble() * 150;                // Beispiel-Molekulargewicht, hier stark vereinfacht
            String inchiKey = "InChIKey" + i;                                       // Beispiel-InChIKey, hier stark vereinfacht
            String inchi = "InChI=1S/C" + i + "Generated";                          // Beispiel-InChI-Notation, hier stark vereinfacht
            String smiles = "C1=CC=CC=" + i;                                        // Beispiel-SMILES-Notation
            Date createdAt = new Date();                                            // Aktuelles Datum als Erstellungsdatum
            String iupacName = "Generated IUPAC Name " + i;                         // Beispiel-IUPAC-Namen

            list.add(new Compounds(
                    cid,
                    name,
                    formula,
                    casNumber,
                    description,
                    pubChemId,
                    molecularMass,
                    molecularWeight,
                    inchiKey,
                    inchi,
                    smiles,
                    createdAt,
                    iupacName
            ));
        }

        return list;
    }

    /**
     * Erzeugt eine zufällige aber chemisch plausible Summenformel.
     * Format: CxHyOz mit zufälligen Werten für x, y, z (z optional).
     * @return Summenformel als String
     */
    private static String randomFormula() {
        return "C" + (5 + random.nextInt(10)) +
                "H" + (5 + random.nextInt(15)) +
                (random.nextBoolean() ? "O" + random.nextInt(5) : "");
    }
}

