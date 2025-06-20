package ch.bbw.BenBrc.utils.mix;

import ch.bbw.BenBrc.models.Mixes;
import ch.bbw.BenBrc.models.Solutions;

/**
 * Die Klasse MixUtils bietet statische Methoden zur Berechnung von Eigenschaften von Mixes.
 * Diese Klasse enthält Methoden, die auf den Komponenten eines Mixes basieren.
 * Autor: Benedict Brück
 * Version: 1.0
 * Datum: 19.06.2025
 */
public class MixUtils {

    /**
     * Berechnet die Gesamtmasse eines Mixes, indem die Molekularmassen aller Komponenten summiert werden.
     * @param mix der Mix, dessen Gesamtmasse berechnet werden soll
     * @return die Gesamtmasse des Mixes
     */
    public static double getTotalMass(Mixes mix) {
        return mix.getComponents()
                .stream()
                .mapToDouble(Solutions::getMolecularMass)
                .sum();
    }
}