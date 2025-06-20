package ch.bbw.BenBrc.utils.mix;

import ch.bbw.BenBrc.models.Mixes;

import java.util.*;
import java.util.stream.*;

/**
 * Die Klasse CompoundSorter enthält Methoden zur Sortierung von Compound-Listen anhand verschiedener Kriterien.
 * Verwendet Comparator-Chaining zur flexiblen und mehrstufigen Sortierung.
 * Autor: Benedict Brück
 * Version: 1.0
 * Datum: 19.06.2025
 */
public class MixFilter {

    /**
     * Sortiert eine Liste von Compounds zuerst nach Name, dann CAS-Nummer und schließlich Erstellungsdatum.
     * @param list Die Liste von Compounds, die sortiert werden soll
     * @param keyword Das Schlüsselwort, nach dem gefiltert werden soll
     * @return Eine neue sortierte Liste
     */
    public static List<Mixes> byType(List<Mixes> list, String keyword) {
        return list.stream()
                .filter(m -> m.getType().equalsIgnoreCase(keyword))
                .limit(5)
                .collect(Collectors.toList());
    }

    /**
     * Filtert eine Liste von Mixes nach einem bestimmten Typ.
     * @param list Die Liste von Mixes, die gefiltert werden soll
     * @param type Der Typ, nach dem gefiltert werden soll
     * @return Eine neue Liste von Mixes, die dem angegebenen Typ entsprechen
     */
    public static List<Mixes> byTypeEquals(List<Mixes> list, String type) {
        return list.stream()
                .filter(m -> m.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    /**
     * Filtert eine Liste von Mixes nach einem Schlüsselwort in der Beschreibung.
     * @param list Die Liste von Mixes, die gefiltert werden soll
     * @param keyword Das Schlüsselwort, nach dem in der Beschreibung gefiltert werden soll
     * @return Eine neue Liste von Mixes, deren Beschreibung das Schlüsselwort enthält
     */
    public static List<Mixes> byDescriptionKeyword(List<Mixes> list, String keyword) {
        return list.stream()
                .filter(m -> m.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .limit(5)
                .collect(Collectors.toList());
    }

    /**
     * Filtert eine Liste von Mixes nach einem Schlüsselwort im Namen.
     * @param list Die Liste von Mixes, die gefiltert werden soll
     * @param min Der minimale Volumenwert
     * @param max Der maximale Volumenwert
     * @return Eine neue Liste von Mixes, deren Name das Schlüsselwort enthält
     */
    public static List<Mixes> byVolumeRange(List<Mixes> list, double min, double max) {
        return list.stream()
                .filter(m -> m.getVolume() >= min && m.getVolume() <= max)
                .limit(5)
                .collect(Collectors.toList());
    }

    /**
     * Filtert eine Liste von Mixes nach einem Schlüsselwort im Namen.
     * @param list Die Liste von Mixes, die gefiltert werden soll
     * @param from Das Startdatum des Zeitraums
     * @param to Das Enddatum des Zeitraums
     * @return Eine neue Liste von Mixes, deren Name das Schlüsselwort enthält
     */
    public static List<Mixes> byPreparationDateRange(List<Mixes> list, Date from, Date to) {
        return list.stream()
                .filter(m -> !m.getPreparationDate().before(from) && !m.getPreparationDate().after(to))
                .limit(5)
                .collect(Collectors.toList());
    }

    /**
     * Filtert eine Liste von Mixes nach der Anzahl der Komponenten.
     * @param list Die Liste von Mixes, die gefiltert werden soll
     * @param minCount Die minimale Anzahl an Komponenten
     * @return Eine neue Liste von Mixes, die mindestens die angegebene Anzahl an Komponenten haben
     */
    public static List<Mixes> byMinComponentCount(List<Mixes> list, int minCount) {
        return list.stream()
                .filter(m -> m.getComponents().size() >= minCount)
                .limit(5)
                .collect(Collectors.toList());
    }

    /**
     * Filtert eine Liste von Mixes nach der minimalen Gesamtmasse.
     * @param list Die Liste von Mixes, die gefiltert werden soll
     * @param minMass Die minimale Gesamtmasse
     * @return Eine neue Liste von Mixes, deren Gesamtmasse mindestens die angegebene Masse hat
     */
    public static List<Mixes> byMinTotalMass(List<Mixes> list, double minMass) {
        return list.stream()
                .filter(m -> MixUtils.getTotalMass(m) >= minMass)
                .limit(5)
                .collect(Collectors.toList());
    }
}