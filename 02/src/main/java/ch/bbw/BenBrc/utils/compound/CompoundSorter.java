package ch.bbw.BenBrc.utils.compound;

import ch.bbw.BenBrc.models.Compounds;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.*;
import java.util.stream.*;

/**
 * Die Klasse CompoundSorter bietet statische Methoden zur Sortierung von {@link Compounds}-Objekten
 * basierend auf verschiedenen Kriterien (z.B. Name, CAS-Nummer, Erstellungsdatum).
 * Sie verwendet Java Stream-APIs zur eleganten und funktionalen Sortierung.
 * Autor: Benedict Brück
 * Version: 1.0
 * Datum: 19.06.2025
 */
public class CompoundSorter {
    public static List<Compounds> byNameThenCASThenDate(List<Compounds> list, boolean ascending) {
        Comparator<Compounds> comparator = Comparator
                .comparing(Compounds::getName)
                .thenComparing(Compounds::getCasNumber)
                .thenComparing(Compounds::getCreatedAt);
        if (!ascending) comparator = comparator.reversed();
        return list.stream()
                .sorted(comparator)
                .limit(5)
                .collect(Collectors.toList());
    }

    /**
     * Sortiert eine Liste von Compounds nach dem Namen und der CAS-Nummer.
     * @param list die Liste von Compounds
     * @param ascending ob die Sortierung aufsteigend oder absteigend sein soll
     * @return eine sortierte Liste von Compounds, begrenzt auf die ersten 5 Einträge
     */
    public static List<Compounds> byMolecularMassThenIUPAC(List<Compounds> list, boolean ascending) {
        Comparator<Compounds> comparator = Comparator
                .comparingDouble(Compounds::getMolecularMass)
                .thenComparing(Compounds::getIUPACName);
        if (!ascending) comparator = comparator.reversed();
        return list.stream()
                .sorted(comparator)
                .limit(5)
                .collect(Collectors.toList());
    }

    /**
     * Sortiert eine Liste von Compounds nach der CID und der SMILES-Darstellung.
     * @param list die Liste von Compounds
     * @param ascending ob die Sortierung aufsteigend oder absteigend sein soll
     * @return eine sortierte Liste von Compounds, begrenzt auf die ersten 5 Einträge
     */
    public static List<Compounds> byCIDThenSMILES(List<Compounds> list, boolean ascending) {
        Comparator<Compounds> comparator = Comparator
                .comparingInt(Compounds::getCompound_CID)
                .thenComparing(Compounds::getSMILES);
        if (!ascending) comparator = comparator.reversed();
        return list.stream()
                .sorted(comparator)
                .limit(5)
                .collect(Collectors.toList());
    }

    /**
     * Sortiert eine Liste von Compounds nach der molekularen Masse.
     * @param list die Liste von Compounds
     * @param ascending ob die Sortierung aufsteigend oder absteigend sein soll
     * @return eine sortierte Liste von Compounds, begrenzt auf die ersten 5 Einträge
     */
    public static List<Compounds> byMolecularWeight(List<Compounds> list, boolean ascending) {
        Comparator<Compounds> comparator = Comparator
                .comparingDouble(Compounds::getMolecularWeight);
        if (!ascending) comparator = comparator.reversed();
        return list.stream()
                .sorted(comparator)
                .limit(5)
                .collect(Collectors.toList());
    }

    /**
     * Sortiert eine Liste von Compounds nach dem Erstellungsdatum und dem Namen.
     * @param list die Liste von Compounds
     * @param ascending ob die Sortierung aufsteigend oder absteigend sein soll
     * @return eine sortierte Liste von Compounds, begrenzt auf die ersten 5 Einträge
     */
    public static List<Compounds> byCreatedAtThenName(List<Compounds> list, boolean ascending) {
        Comparator<Compounds> comparator = Comparator
                .comparing(Compounds::getCreatedAt)
                .thenComparing(Compounds::getName);
        if (!ascending) comparator = comparator.reversed();
        return list.stream()
                .sorted(comparator)
                .limit(5)
                .collect(Collectors.toList());
    }

    /**
     * Sortiert eine Liste von Compounds nach der InChI und der CAS-Nummer.
     * @param list die Liste von Compounds
     * @param ascending ob die Sortierung aufsteigend oder absteigend sein soll
     * @return eine sortierte Liste von Compounds, begrenzt auf die ersten 5 Einträge
     */
    public static List<Compounds> byInChIThenCAS(List<Compounds> list, boolean ascending) {
        Comparator<Compounds> comparator = Comparator
                .comparing(Compounds::getInChI)
                .thenComparing(Compounds::getCasNumber);
        if (!ascending) comparator = comparator.reversed();
        return list.stream()
                .sorted(comparator)
                .limit(5)
                .collect(Collectors.toList());
    }

    /**
     * Sortiert eine Liste von Compounds nach dem IUPAC-Namen und der CID.
     * @param list die Liste von Compounds
     * @param ascending ob die Sortierung aufsteigend oder absteigend sein soll
     * @return eine sortierte Liste von Compounds, begrenzt auf die ersten 5 Einträge
     */
    public static List<Compounds> byIUPACNameThenCID(List<Compounds> list, boolean ascending) {
        Comparator<Compounds> comparator = Comparator
                .comparing(Compounds::getIUPACName)
                .thenComparingInt(Compounds::getCompound_CID);
        if (!ascending) comparator = comparator.reversed();
        return list.stream()
                .sorted(comparator)
                .limit(5)
                .collect(Collectors.toList());
    }
}
