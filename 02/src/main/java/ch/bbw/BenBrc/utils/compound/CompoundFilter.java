package ch.bbw.BenBrc.utils.compound;

import ch.bbw.BenBrc.models.Compounds;

import java.util.*;
import java.util.stream.*;

/**
 * Die Klasse CompoundFilter bietet statische Methoden zur Filterung von {@link Compounds}-Objekten
 * basierend auf bestimmten Kriterien (z.B. Namensinhalt oder IUPAC-Übereinstimmung).
 * verwendet Java Stream-APIs zur eleganten und funktionalen Filterung.
 * Autor: Benedict Brück
 * Version: 1.0
 * Datum: 19.06.2025
 */
public class CompoundFilter {

    /**
     * Filtert eine Liste von Compounds nach einem Teilstring im Namen.
     * @param list die Liste von Compounds
     * @param keyword der Suchbegriff, der im Namen enthalten sein soll
     * @return eine gefilterte Liste von Compounds, deren Namen den Suchbegriff enthalten
     */
    public static List<Compounds> byNameContains(List<Compounds> list, String keyword) {
        return list.stream()
                .filter(c -> c.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Filtert eine Liste von Compounds nach einem Teilstring im IUPAC-Namen.
     * @param list die Liste von Compounds
     * @param formula der Suchbegriff, der im IUPAC-Namen enthalten sein soll
     * @return eine gefilterte Liste von Compounds, deren IUPAC-Namen den Suchbegriff enthalten
     */
    public static List<Compounds> byFormula(List<Compounds> list, String formula) {
        return list.stream()
                .filter(c -> c.getFormula().equalsIgnoreCase(formula))
                .limit(5)
                .collect(Collectors.toList());
    }

    /**
     * Filtert eine Liste von Compounds nach einem CAS-Nummer-Präfix.
     * @param list die Liste von Compounds
     * @param casPrefix das Präfix der CAS-Nummer, nach dem gefiltert werden soll
     * @return eine gefilterte Liste von Compounds, deren CAS-Nummer mit dem Präfix beginnt
     */
    public static List<Compounds> byCAS(List<Compounds> list, String casPrefix) {
        return list.stream()
                .filter(c -> c.getCasNumber().startsWith(casPrefix))
                .limit(5)
                .collect(Collectors.toList());
    }

    /**
     * Filtert eine Liste von Compounds nach einem IUPAC-Namen.
     * @param list die Liste von Compounds
     * @param iupac der IUPAC-Name, nach dem gefiltert werden soll
     * @return eine gefilterte Liste von Compounds, deren IUPAC-Namen den Suchbegriff enthalten
     */
    public static List<Compounds> byIUPAC(List<Compounds> list, String iupac) {
        return list.stream()
                .filter(c -> c.getIUPACName().toLowerCase().contains(iupac.toLowerCase()))
                .limit(5)
                .collect(Collectors.toList());
    }

    /**
     * Filtert eine Liste von Compounds nach einer CID (Compound ID).
     * @param list die Liste von Compounds
     * @param minCID die minimale CID, ab der gefiltert werden soll
     * @return eine gefilterte Liste von Compounds, deren CID größer oder gleich minCID ist
     */
    public static List<Compounds> byCID(List<Compounds> list, int minCID) {
        return list.stream()
                .filter(c -> c.getCompound_CID() >= minCID)
                .limit(5)
                .collect(Collectors.toList());
    }

    /**
     * Filtert eine Liste von Compounds nach einem Teilstring in der SMILES-Darstellung.
     * @param list die Liste von Compounds
     * @param smilesPart der Teilstring, der in der SMILES-Darstellung enthalten sein soll
     * @return eine gefilterte Liste von Compounds, deren SMILES-Darstellung den Teilstring enthält
     */
    public static List<Compounds> bySMILES(List<Compounds> list, String smilesPart) {
        return list.stream()
                .filter(c -> c.getSMILES().contains(smilesPart))
                .limit(5)
                .collect(Collectors.toList());
    }
}
