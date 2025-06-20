package ch.bbw.BenBrc.utils.mix;

import ch.bbw.BenBrc.models.Mixes;

import java.util.*;
import java.util.stream.*;

/**
 * Die Klasse MixSorter bietet statische Methoden zur Sortierung von Mixes anhand verschiedener Kriterien.
 * Es wird Comparator-Chaining verwendet, um mehrstufige Sortierungen durchzuführen.
 * Die Ergebnisse sind auf maximal 5 Elemente begrenzt.
 * Autor: Benedict Brück
 * Version: 1.0
 * Datum: 19.06.2025
 */
public class MixSorter {

    /**
     * Sortiert eine Liste von Mixes nach dem Namen.
     * @param list die Liste von Mixes
     * @param ascending true für aufsteigende Sortierung, false für absteigende Sortierung
     * @return eine sortierte Liste der ersten 5 Mixes
     */
    public static List<Mixes> byPreparationDate(List<Mixes> list, boolean ascending) {
        Comparator<Mixes> comparator = Comparator
                .comparing(Mixes::getPreparationDate);
        if (!ascending) comparator = comparator.reversed();
        return list.stream()
                .sorted(comparator)
                .limit(5)
                .collect(Collectors.toList());
    }

    /**
     * Sortiert eine Liste von Mixes nach dem Ablaufdatum.
     * @param list die Liste von Mixes
     * @param ascending true für aufsteigende Sortierung, false für absteigende Sortierung
     * @return eine sortierte Liste der ersten 5 Mixes
     */
    public static List<Mixes> byExpirationDate(List<Mixes> list, boolean ascending) {
        Comparator<Mixes> comparator = Comparator
                .comparing(Mixes::getExpirationDate);
        if (!ascending) comparator = comparator.reversed();
        return list.stream()
                .sorted(comparator)
                .limit(5)
                .collect(Collectors.toList());
    }

    /**
     * Sortiert eine Liste von Mixes nach dem Volumen.
     * @param list die Liste von Mixes
     * @param ascending true für aufsteigende Sortierung, false für absteigende Sortierung
     * @return eine sortierte Liste der ersten 5 Mixes
     */
    public static List<Mixes> byVolume(List<Mixes> list, boolean ascending) {
        Comparator<Mixes> comparator = Comparator
                .comparingDouble(Mixes::getVolume);
        if (!ascending) comparator = comparator.reversed();
        return list.stream()
                .sorted(comparator)
                .limit(5)
                .collect(Collectors.toList());
    }

    /**
     * Sortiert eine Liste von Mixes nach der Anzahl der Komponenten.
     * @param list die Liste von Mixes
     * @param ascending true für aufsteigende Sortierung, false für absteigende Sortierung
     * @return eine sortierte Liste der ersten 5 Mixes
     */
    public static List<Mixes> byComponentCount(List<Mixes> list, boolean ascending) {
        Comparator<Mixes> comparator = Comparator
                .comparingInt(m -> m.getComponents().size());
        if (!ascending) comparator = comparator.reversed();
        return list.stream()
                .sorted(comparator)
                .limit(5)
                .collect(Collectors.toList());
    }

    /**
     * Sortiert eine Liste von Mixes nach dem Typ und dann nach dem Volumen.
     * @param list die Liste von Mixes
     * @param ascending true für aufsteigende Sortierung, false für absteigende Sortierung
     * @return eine sortierte Liste der ersten 5 Mixes
     */
    public static List<Mixes> byTypeThenVolume(List<Mixes> list, boolean ascending) {
        Comparator<Mixes> comparator = Comparator
                .comparing(Mixes::getType)
                .thenComparingDouble(Mixes::getVolume);
        if (!ascending) comparator = comparator.reversed();
        return list.stream()
                .sorted(comparator)
                .limit(5)
                .collect(Collectors.toList());
    }

    /**
     * Sortiert eine Liste von Mixes nach der Gesamtmasse.
     * @param list die Liste von Mixes
     * @param ascending true für aufsteigende Sortierung, false für absteigende Sortierung
     * @return eine sortierte Liste der ersten 5 Mixes
     */
    public static List<Mixes> byTotalMass(List<Mixes> list, boolean ascending) {
        Comparator<Mixes> comparator = Comparator
                .comparingDouble(MixUtils::getTotalMass);
        if (!ascending) comparator = comparator.reversed();
        return list.stream()
                .sorted(comparator)
                .limit(5)
                .collect(Collectors.toList());
    }
}