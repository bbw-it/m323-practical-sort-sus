package ch.bbw.BenBrc.services;

import ch.bbw.BenBrc.models.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Die Klasse ConsolePrinter dient dazu, formatierte Informationen
 * über Compounds, Solutions und Mixes in der Konsole auszugeben.
 * verwendet Methoden zur strukturierten, gut lesbaren Darstellung
 * von Listenobjekten mit passenden Symbolen und Formatierungen.
 * Autor: Benedict Brück
 * Version: 1.0
 * Datum: 19.06.2025
 */
public class ConsolePrinter {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Gibt eine formatierte Liste von Compounds in der Konsole aus.
     * @param compounds Liste der darzustellenden Compounds
     * @param title Überschrift der Ausgabe
     */
    public static void prettyPrintCompounds(List<Compounds> compounds, String title) {
        System.out.println("\n " + title + " (" + compounds.size() + " Treffer)");
        System.out.println("--------------------------------------------------");

        for (Compounds c : compounds) {
            System.out.println("✓ " + c.getName());
            System.out.println("   - CID:     " + c.getCompound_CID());
            System.out.println("   - CAS:     " + c.getCasNumber());
            System.out.println("   - Mass:    " + format(c.getMolecularWeight()));
            System.out.println("   - IUPAC:   " + c.getIUPACName());
            System.out.println("   - Created: " + format(c.getCreatedAt()));
            System.out.println();
        }
    }

    /**
     * Gibt eine formatierte Liste von Solutions in der Konsole aus.
     * @param solutions Liste der darzustellenden Solutions
     * @param title Überschrift der Ausgabe
     */
    public static void prettyPrintSolutions(List<Solutions> solutions, String title) {
        System.out.println("\n " + title + " (" + solutions.size() + " Treffer)");
        System.out.println("--------------------------------------------------");

        for (Solutions s : solutions) {
            System.out.println("✓ " + s.getName());
            System.out.println("   - CID:       " + s.getCid());
            System.out.println("   - Formula:   " + s.getMolecularFormula());
            System.out.println("   - Weight:    " + format(s.getMolecularWeight()));
            System.out.println("   - Mass:      " + format(s.getMolecularMass()));
            System.out.println("   - IUPAC:     " + s.getIupacName());
            System.out.println("   - Class:     " + s.getClassification());
            System.out.println("   - Created:   " + format(s.getCreatedAt()));
            System.out.println();
        }
    }

    /**
     * Gibt eine formatierte Liste von Mixes (Mischungen) aus, inkl. deren Komponenten.
     * @param mixes Liste der darzustellenden Mischungen
     * @param title Überschrift der Ausgabe
     */
    public static void prettyPrintMixes(List<Mixes> mixes, String title) {
        System.out.println("\n " + title + " (" + mixes.size() + " Treffer)");
        System.out.println("--------------------------------------------------");

        for (Mixes m : mixes) {
            System.out.println("✓ " + m.getName());
            System.out.println("   - Type:        " + m.getType());
            System.out.println("   - Volume:      " + format(m.getVolume()));
            System.out.println("   - Components:  " + m.getComponents().size());
            System.out.println("   - Prepared:    " + format(m.getPreparationDate()));
            System.out.println("   - Expires:     " + format(m.getExpirationDate()));
            System.out.println("   - Description: " + m.getDescription());
            System.out.println();

            for (Solutions s : m.getComponents()) {
                System.out.println("     • " + s.getName() + " (" + s.getMolecularFormula() + ")");
            }
            System.out.println();
        }
    }

    /**
     * Formatierungsfunktion zur Darstellung von Objekten.
     * - Bei Datumsobjekten: Ausgabe als "yyyy-MM-dd"
     * - Bei Double-Werten: Ausgabe mit 2 Nachkommastellen
     * - Null-Werte werden als "-" dargestellt
     * @param obj beliebiges Objekt
     * @return formatierter String
     */
    private static String format(Object obj) {
        if (obj == null) return "-";
        if (obj instanceof java.util.Date) return DATE_FORMAT.format(obj);
        if (obj instanceof Double) return String.format("%.2f", (Double) obj);
        return obj.toString();
    }
}
