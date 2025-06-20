package ch.bbw.BenBrc.services;

import ch.bbw.BenBrc.factories.*;
import ch.bbw.BenBrc.models.*;

import java.util.*;
/**
 * Die Klasse DataGenerator dient als zentrale Stelle zur Erzeugung von Beispieldaten für das CoSoMi-System.
 * nutzt jeweils die zugehörigen Factory-Klassen für:
 * - Compounds (über CompoundFactory)
 * - Solutions (basierend auf den erzeugten Compounds über SolutionFactory)
 * - Mixes (basierend auf den Solutions über MixFactory)
 * Diese Methoden ermöglichen eine vollständige und gekettete Datengenerierung für Tests oder Demonstrationen.
 * Autor: Benedict Brück
 * Version: 1.0
 * Datum: 19.06.2025
 */
public class DataGenerator {

    /**
     * Generiert eine Liste von chemischen Grundstoffen (Compounds) mit Hilfe der CompoundFactory.
     * @return Liste von Compounds (Standard: 100 Stück)
     */
    public static List<Compounds> generateCompounds() {
        return CompoundFactory.generateCompounds(100);
    }

    /**
     * Generiert eine Liste von Solutions (veränderte oder angereicherte Compounds).
     * Diese bauen auf bestehenden Compounds auf und erweitern deren Eigenschaften.
     * @param compounds Liste der Grundstoffe, aus denen Solutions erstellt werden sollen
     * @return Liste von Solutions
     */
    public static List<Solutions> generateSolutions(List<Compounds> compounds) {
        return SolutionFactory.generateSolutionsFrom(compounds);
    }

    /**
     * Generiert eine Liste von Mixes (chemische Mischungen aus Solutions).
     * Die Mischungen bestehen aus einer Kombination von Lösungen.
     * @param solutions Liste der Solutions, die für Mischungen verwendet werden sollen
     * @return Liste von Mixes
     */
    public static List<Mixes> generateMixes(List<Solutions> solutions) {
        return MixFactory.generateMixesFrom(solutions);
    }

    /**
     * Generiert alle Daten (Compounds, Solutions und Mixes) und gibt eine Zusammenfassung der generierten Daten aus.
     * Dient zum testen und demonstrieren der Funktionalität der DataGenerator-Klasse.
     */
    public static void generateAllAndPrint() {
        List<Compounds> compounds = generateCompounds();
        List<Solutions> solutions = generateSolutions(compounds);
        List<Mixes> mixes = generateMixes(solutions);

        System.out.println("✅ Generated " + compounds.size() + " compounds.");
        System.out.println("✅ Generated " + solutions.size() + " solutions.");
        System.out.println("✅ Generated " + mixes.size() + " mixes.");

        System.out.println("\\n📌 Example compound: " + compounds.get(0));
        System.out.println("📌 Example solution: " + solutions.get(0));
        System.out.println("📌 Example mix: " + mixes.get(0));
    }
}

