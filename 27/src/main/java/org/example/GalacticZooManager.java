package org.example;

import org.example.model.AlienAnimal;
import org.example.model.Zookeeper;
import org.example.util.DataGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class GalacticZooManager {

    private static void printFirstNAnimals(List<AlienAnimal> animals, int n, String message) {
        System.out.println(message);
        animals.stream().limit(n).forEach(System.out::println);
    }

    private static void printFirstNAnimals(List<AlienAnimal> animals, int n) {
        animals.stream().limit(n).forEach(System.out::println);
    }

    private static void printFirstNAnimalsWithIQ(List<AlienAnimal> animals, int n, String message) {
        System.out.println(message);
        animals.stream().limit(n).forEach(animal ->
                System.out.printf(Locale.GERMAN, "%s (IQ: %d)\n", animal.toString(), animal.getBrainProfile().getIqLevel()));
    }

    private static void printFirstNAnimalsWithWeight(List<AlienAnimal> animals, int n, String message) {
        System.out.println(message);
        animals.stream().limit(n).forEach(animal ->
                System.out.printf(Locale.GERMAN, "%s (Weight: %.2f T)\n", animal.toString(), animal.getWeightInTons()));
    }

    private static void printFirstNAnimalsWithPlanetAndDanger(List<AlienAnimal> animals, int n, String message) {
        System.out.println(message);
        animals.stream().limit(n).forEach(animal ->
                System.out.printf(Locale.GERMAN, "%s (Planet: %s, Danger: %s)\n", animal.toString(), animal.getOriginPlanet().getName(), animal.getDangerLevel()));
    }

    private static void printFirstNAnimalsWithDate(List<AlienAnimal> animals, int n, String message) {
        System.out.println(message);
        animals.stream().limit(n).forEach(animal ->
                System.out.printf(Locale.GERMAN, "%s (Discovered: %s)\n", animal.toString(), animal.getDateDiscovered()));
    }

    private static void printFirstNAnimalsWithPlanetDistance(List<AlienAnimal> animals, int n, String message) {
        System.out.println(message);
        animals.stream().limit(n).forEach(animal ->
                System.out.printf(Locale.GERMAN, "%s (Planet: %s, Distance: %d ly)\n", animal.toString(), animal.getOriginPlanet().getName(), animal.getOriginPlanet().getDistanceFromEarth()));
    }

    private static void printFirstNAnimalsWithAtmosphereAndTelepathy(List<AlienAnimal> animals, int n, String message) {
        System.out.println(message);
        animals.stream().limit(n).forEach(animal ->
                System.out.printf(Locale.GERMAN, "%s (Atmosphere: %s, Telepathic: %b)\n",
                        animal.toString(), animal.getOriginPlanet().getAtmosphereType(), animal.getBrainProfile().isTelepathic()));
    }

    private static void printFirstNAnimalsWithDangerWeightName(List<AlienAnimal> animals, int n, String message) {
        System.out.println(message);
        animals.stream().limit(n).forEach(animal ->
                System.out.printf(Locale.GERMAN, "%s (Danger: %s, Weight: %.2f T, Name: %s)\n",
                        animal.toString(), animal.getDangerLevel(), animal.getWeightInTons(), animal.getSpeciesName()));
    }


    public static void main(String[] args) {
        System.out.println("🌌 Welcome to the Galactic Zoo Sorter! 🪐🦄");
        System.out.println("==============================================\n");

        // 1. Datensätze generieren
        List<AlienAnimal> alienAnimals = DataGenerator.generateAlienAnimalList(100);
        System.out.println("Generated " + alienAnimals.size() + " Alien Animals. First 5 (unsorted):");
        printFirstNAnimals(alienAnimals, 5);

        // --- A. Comparator und Comparable ---
        System.out.println("\n--- A.1 Natural Order (Comparable by Species Name ASC) ---");
        List<AlienAnimal> sortedByName = new ArrayList<>(alienAnimals);
        Collections.sort(sortedByName);
        printFirstNAnimals(sortedByName, 5, "Sorted by Species Name (Natural Order):");

        System.out.println("\n--- B.1 Comparator: Anonyme Klasse (by IQ Level ASC) ---");
        List<AlienAnimal> sortedByIQAnon = new ArrayList<>(alienAnimals);
        Collections.sort(sortedByIQAnon, new Comparator<AlienAnimal>() {
            @Override
            public int compare(AlienAnimal a1, AlienAnimal a2) {
                return Integer.compare(a1.getBrainProfile().getIqLevel(), a2.getBrainProfile().getIqLevel());
            }
        });
        printFirstNAnimalsWithIQ(sortedByIQAnon, 5, "Sorted by IQ (Anonymous Class, ASC):");

        System.out.println("\n--- B.2 Comparator: Lambda Expression (by Weight ASC) ---");
        List<AlienAnimal> sortedByWeightLambda = new ArrayList<>(alienAnimals);
        sortedByWeightLambda.sort((a1, a2) -> Double.compare(a1.getWeightInTons(), a2.getWeightInTons()));
        printFirstNAnimalsWithWeight(sortedByWeightLambda, 5, "Sorted by Weight (Lambda, ASC):");

        System.out.println("\n--- B.3 Comparator: Comparator Chain (by Planet Name ASC, then by Danger Level DESC) ---");
        List<AlienAnimal> sortedByPlanetThenDanger = new ArrayList<>(alienAnimals);
        Comparator<AlienAnimal> byPlanetName = Comparator.comparing(animal -> animal.getOriginPlanet().getName());
        Comparator<AlienAnimal> byPlanetThenDangerChain = byPlanetName
                .thenComparing(AlienAnimal::getDangerLevel, Comparator.reverseOrder());
        sortedByPlanetThenDanger.sort(byPlanetThenDangerChain);
        printFirstNAnimalsWithPlanetAndDanger(sortedByPlanetThenDanger, 7, "Sorted by Planet Name (ASC) then Danger Level (DESC):");

        class LanguagesComparator implements Comparator<AlienAnimal> {
            @Override
            public int compare(AlienAnimal a1, AlienAnimal a2) {
                // DESC order for number of languages
                return Integer.compare(a2.getBrainProfile().getKnownLanguages().size(),
                        a1.getBrainProfile().getKnownLanguages().size());
            }
        }
        System.out.println("\n--- B.4 Comparator: Abgeleitete Klasse (by Number of Known Languages DESC) ---");
        List<AlienAnimal> sortedByLanguages = new ArrayList<>(alienAnimals);
        sortedByLanguages.sort(new LanguagesComparator());
        System.out.println("Sorted by Number of Known Languages (Derived Class, DESC):");
        sortedByLanguages.stream().limit(5).forEach(animal ->
                System.out.printf(Locale.GERMAN, "%s (Known Languages: %d)\n",
                        animal.toString(), animal.getBrainProfile().getKnownLanguages().size()));


        // --- C. Comparator-Attribute in der Datenklasse ---
        System.out.println("\n--- C.1 Comparator-Attribut in AlienAnimal (BY_WEIGHT_ASC) ---");
        List<AlienAnimal> sortedByWeightAttr = new ArrayList<>(alienAnimals);
        sortedByWeightAttr.sort(AlienAnimal.BY_WEIGHT_ASC);
        printFirstNAnimalsWithWeight(sortedByWeightAttr, 5, "Sorted by Weight (Class Attribute ASC):");

        // --- D. Verschiedene Sortierungen, so dass alle Attribute mindestens 1x verwendet wurden ---

        System.out.println("\n--- D.1 Sort by Date Discovered (ASC using class attribute) ---");
        List<AlienAnimal> sortedByDate = new ArrayList<>(alienAnimals);
        sortedByDate.sort(AlienAnimal.BY_DATE_DISCOVERED_ASC);
        printFirstNAnimalsWithDate(sortedByDate, 5, "Sorted by Date Discovered (Class Attribute ASC):");

        System.out.println("\n--- D.2 Sort by Origin Planet Distance From Earth (DESC) ---");
        List<AlienAnimal> sortedByPlanetDistance = new ArrayList<>(alienAnimals);
        sortedByPlanetDistance.sort(Comparator.comparingInt((AlienAnimal a) -> a.getOriginPlanet().getDistanceFromEarth()).reversed());
        printFirstNAnimalsWithPlanetDistance(sortedByPlanetDistance, 5, "Sorted by Planet Distance (DESC):");

        System.out.println("\n--- D.3 Sort by Origin Planet Atmosphere Type (ASC) then Telepathic (true first) ---");
        List<AlienAnimal> sortedByAtmosphereAndTelepathy = new ArrayList<>(alienAnimals);
        Comparator<AlienAnimal> byAtmosphereThenTelepathy = Comparator
                .comparing((AlienAnimal a) -> a.getOriginPlanet().getAtmosphereType()) // Enum natural order
                .thenComparing(Comparator.comparing((AlienAnimal a) -> a.getBrainProfile().isTelepathic()).reversed()); // true first
        sortedByAtmosphereAndTelepathy.sort(byAtmosphereThenTelepathy);
        printFirstNAnimalsWithAtmosphereAndTelepathy(sortedByAtmosphereAndTelepathy, 7, "Sorted by Planet Atmosphere (ASC) then Telepathic (true first):");

        // --- E. Mehrstufige Sortierungen ---
        System.out.println("\n--- E.1 Mehrstufig: Danger Level (DESC), then Weight (ASC), then Species Name (ASC) ---");
        List<AlienAnimal> multiSortedAnimals = new ArrayList<>(alienAnimals);
        multiSortedAnimals.sort(
                Comparator.comparing(AlienAnimal::getDangerLevel, Comparator.reverseOrder())
                        .thenComparingDouble(AlienAnimal::getWeightInTons)
                        .thenComparing(AlienAnimal::getSpeciesName)
        );
        printFirstNAnimalsWithDangerWeightName(multiSortedAnimals, 7, "Sorted by Danger (DESC), Weight (ASC), Name (ASC):");

        // --- F. Reverse Order ---
        System.out.println("\n--- F.1 Reverse Order for Natural Sort (Species Name DESC) ---");
        List<AlienAnimal> sortedByNameDesc = new ArrayList<>(alienAnimals);
        sortedByNameDesc.sort(Collections.reverseOrder()); // Uses Comparable and reverses
        printFirstNAnimals(sortedByNameDesc, 5, "Sorted by Species Name (Natural Order Reversed DESC):");

        System.out.println("\n--- F.2 Reverse Order for Custom Comparator (Weight DESC using AlienAnimal.BY_WEIGHT_DESC) ---");
        List<AlienAnimal> sortedByWeightDescAttr = new ArrayList<>(alienAnimals);
        sortedByWeightDescAttr.sort(AlienAnimal.BY_WEIGHT_DESC);
        printFirstNAnimalsWithWeight(sortedByWeightDescAttr, 5, "Sorted by Weight (Class Attribute DESC):");

        // --- Erweiterte Anforderung: Zookeepers ---
        System.out.println("\n\n--- Erweiterte Anforderungen: Zookeepers ---");
        System.out.println("===========================================");
        List<Zookeeper> zookeepers = DataGenerator.generateZookeepers(4, alienAnimals);
        System.out.println("Generated " + zookeepers.size() + " Zookeepers.");
        zookeepers.forEach(System.out::println);

        System.out.println("\n--- Ext.1 Sort Zookeepers by Experience (DESC) ---");
        List<Zookeeper> sortedKeepersByExp = new ArrayList<>(zookeepers);
        sortedKeepersByExp.sort(Comparator.comparingInt(Zookeeper::getExperienceYears).reversed());
        sortedKeepersByExp.forEach(System.out::println);

        System.out.println("\n--- Ext.2 Sort Zookeepers by Average IQ of their Animals (DESC) ---");
        List<Zookeeper> sortedKeepersByAvgIQ = new ArrayList<>(zookeepers);
        sortedKeepersByAvgIQ.sort(Comparator.comparingDouble(Zookeeper::getAverageIQOfAssignedCreatures).reversed());
        System.out.println("(Showing keeper name and average IQ of their creatures)");
        sortedKeepersByAvgIQ.forEach(k ->
                System.out.printf(Locale.GERMAN, "Zookeeper: %-20s | Avg Creature IQ: %5.2f\n", k.getName(), k.getAverageIQOfAssignedCreatures())
        );

        System.out.println("\n--- Ext.3 Sort Zookeepers by Max Danger Level of their Animals (DESC), then by Name (ASC) ---");
        List<Zookeeper> sortedKeepersByMaxDanger = new ArrayList<>(zookeepers);
        sortedKeepersByMaxDanger.sort(
                Comparator.comparing(Zookeeper::getMaxDangerLevelOfAssignedCreatures, Comparator.reverseOrder())
                        .thenComparing(Zookeeper::getName)
        );
        System.out.println("(Showing keeper name and max danger level of their creatures)");
        sortedKeepersByMaxDanger.forEach(k ->
                System.out.printf(Locale.GERMAN, "Zookeeper: %-20s | Max Danger: %-15s\n", k.getName(), k.getMaxDangerLevelOfAssignedCreatures())
        );

        System.out.println("\n--- Ext.4 Sortiere Tiere innerhalb eines Zookeepers nach Entdeckungsdatum (ASC) ---");
        if (!zookeepers.isEmpty()) {
            Zookeeper keeperToSortAnimals = zookeepers.stream()
                    .filter(zk -> !zk.getAssignedCreatures().isEmpty())
                    .findFirst()
                    .orElse(null);

            if (keeperToSortAnimals != null) {
                System.out.println("Animals for Zookeeper: " + keeperToSortAnimals.getName() + " (UNSORTED by discovery date):");
                List<AlienAnimal> creaturesBeforeSort = new ArrayList<>(keeperToSortAnimals.getAssignedCreatures());
                printFirstNAnimals(creaturesBeforeSort, creaturesBeforeSort.size());

                keeperToSortAnimals.sortAssignedCreaturesByDateDiscovered();

                System.out.println("\nAnimals for Zookeeper: " + keeperToSortAnimals.getName() + " (SORTED by discovery date ASC):");
                printFirstNAnimalsWithDate(keeperToSortAnimals.getAssignedCreatures(), keeperToSortAnimals.getAssignedCreatures().size(),
                        "Sorted Animals by Discovery Date for " + keeperToSortAnimals.getName() + ":");
            } else {
                System.out.println("No zookeeper with assigned creatures found to demonstrate internal sorting.");
            }
        } else {
            System.out.println("No zookeepers generated to demonstrate internal sorting of animals.");
        }

        System.out.println("\n\n--- Ende der Demonstration des Galactic Zoo Sorters ---");
    }
}