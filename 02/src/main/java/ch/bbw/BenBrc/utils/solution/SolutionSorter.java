package ch.bbw.BenBrc.utils.solution;

import ch.bbw.BenBrc.models.Solutions;

import java.util.*;
import java.util.stream.*;

/**
 * Die Klasse SolutionSorter bietet statische Methoden zur Sortierung von Lösungen anhand verschiedener Kriterien.
 * Es wird Comparator-Chaining verwendet, um mehrstufige Sortierungen durchzuführen.
 * Die Ergebnisse sind auf maximal 5 Elemente begrenzt.
 * Autor: Benedict Brück
 * Version: 1.0
 * Datum: 19.06.2025
 */
public class SolutionSorter {
    public static List<Solutions> byNameThenMass(List<Solutions> list, boolean ascending) {
        Comparator<Solutions> comparator = Comparator
                .comparing(Solutions::getName)
                .thenComparingDouble(Solutions::getMolecularMass);
        if (!ascending) comparator = comparator.reversed();
        return list.stream()
                .sorted(comparator)
                .limit(5)
                .collect(Collectors.toList());
    }

    /**
     * Sortiert eine Liste von Lösungen nach dem Namen.
     * @param list die Liste von Lösungen
     * @param ascending true für aufsteigende Sortierung, false für absteigende Sortierung
     * @return eine sortierte Liste der ersten 5 Lösungen
     */
    public static List<Solutions> byMolecularWeight(List<Solutions> list, boolean ascending) {
        return list.stream()
                .sorted((a, b) -> ascending
                        ? Double.compare(a.getMolecularWeight(), b.getMolecularWeight())
                        : Double.compare(b.getMolecularWeight(), a.getMolecularWeight()))
                .limit(5)
                .collect(Collectors.toList());
    }

    /**
     * Sortiert eine Liste von Lösungen nach der Molekularmasse.
     * @param list die Liste von Lösungen
     * @param ascending true für aufsteigende Sortierung, false für absteigende Sortierung
     * @return eine sortierte Liste der ersten 5 Lösungen
     */
    public static List<Solutions> byMass(List<Solutions> list, boolean ascending) {
        return list.stream()
                .sorted((a, b) -> ascending
                        ? Double.compare(a.getMolecularMass(), b.getMolecularMass())
                        : Double.compare(b.getMolecularMass(), a.getMolecularMass()))
                .limit(5)
                .collect(Collectors.toList());
    }
}
