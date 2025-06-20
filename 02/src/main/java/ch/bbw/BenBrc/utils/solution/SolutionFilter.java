package ch.bbw.BenBrc.utils.solution;

import ch.bbw.BenBrc.models.Solutions;

import java.util.*;
import java.util.stream.*;

/**
 * Die Klasse SolutionFilter bietet statische Methoden zur Filterung von Lösungen anhand verschiedener Kriterien.
 * Es werden verschiedene Filtermethoden bereitgestellt, um Lösungen nach Name, Klassifikation, Synonymen und Molekularmasse zu filtern.
 * Autor: Benedict Brück
 * Version: 1.0
 * Datum: 19.06.2025
 */
public class SolutionFilter {
    public static List<Solutions> byName(List<Solutions> list, String keyword) {
        return list.stream()
                .filter(s -> s.getName().toLowerCase().contains(keyword.toLowerCase()))
                .limit(5)
                .collect(Collectors.toList());
    }

    /**
     * Filtert eine Liste von Lösungen nach Klassifikation, die den angegebenen Schlüsselwort enthält.
     * @param list die Liste von Lösungen
     * @param keyword das Schlüsselwort zur Filterung
     * @return eine gefilterte Liste der ersten 5 Lösungen, deren Klassifikation das Schlüsselwort enthält
     */
    public static List<Solutions> byClassificationContains(List<Solutions> list, String keyword) {
        return list.stream()
                .filter(s -> s.getClassification().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Filtert eine Liste von Lösungen nach Synonymen, die den angegebenen Schlüsselwort enthalten.
     * @param list die Liste von Lösungen
     * @param keyword das Schlüsselwort zur Filterung
     * @return eine gefilterte Liste der ersten 5 Lösungen, deren Synonyme das Schlüsselwort enthalten
     */
    public static List<Solutions> bySynonym(List<Solutions> list, String keyword) {
        return list.stream()
                .filter(s -> s.getSynonyms().stream()
                        .anyMatch(syn -> syn.toLowerCase().contains(keyword.toLowerCase())))
                .limit(5)
                .collect(Collectors.toList());
    }

    /**
     * Filtert eine Liste von Lösungen nach einem Bereich von Molekularmassen.
     * @param list die Liste von Lösungen
     * @param max die maximale Molekularmasse
     * @param min die minimale Molekularmasse
     * @return eine gefilterte Liste der ersten 5 Lösungen, deren Molekularmasse im angegebenen Bereich liegt
     */
    public static List<Solutions> byMassRange(List<Solutions> list, double min, double max) {
        return list.stream()
                .filter(s -> s.getMolecularMass() >= min && s.getMolecularMass() <= max)
                .limit(5)
                .collect(Collectors.toList());
    }
}
