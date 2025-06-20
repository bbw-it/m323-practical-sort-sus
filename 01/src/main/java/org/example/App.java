package org.example;

import org.example.model.Faction;
import org.example.model.Unit;
import org.example.util.UnitGeneratorInstancio;

import java.util.*;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        List<Unit> units = UnitGeneratorInstancio.generateUnits(100);

        System.out.println("=== ORIGINAL (not sorted) ===");
        units.stream().limit(5).forEach(System.out::println);

        System.out.println("\n=== Sort by Faction Name ===");
        units.stream().sorted(Comparator.comparing(u -> u.getFaction().getName())).limit(5).forEach(System.out::println);

        System.out.println("\n=== Sort by Homeworld then Unit Name ===");
        units.stream().sorted(Comparator.comparing((Unit u) -> u.getFaction().getHomeWorld()).thenComparing(Unit::getName)).limit(5).forEach(System.out::println);

        System.out.println("\n=== Sort by Armor Rating then Faction ===");
        units.stream().sorted(Comparator.comparing(Unit::getArmorRating).thenComparing(u -> u.getFaction().getName())).limit(5).forEach(System.out::println);

        System.out.println("\n=== Sort by Natural Order (Unit Name) ===");
        Collections.sort(units);
        units.stream().limit(5).forEach(System.out::println);

        System.out.println("\n=== Sort by Firepower (reverse) ===");
        units.stream().sorted(Comparator.comparing(Unit::getFirepower).reversed()).limit(5).forEach(System.out::println);

        System.out.println("\n=== Sort using Anonymous Class (by deployment date) ===");
        units.stream().sorted(new Comparator<Unit>() {
            @Override
            public int compare(Unit u1, Unit u2) {
                return u1.getDeploymentDate().compareTo(u2.getDeploymentDate());
            }
        }).limit(5).forEach(System.out::println);

        System.out.println("\n=== Factions sorted by number of units ===");
        Map<Faction, Long> factionCount = units.stream()
                .collect(Collectors.groupingBy(Unit::getFaction, Collectors.counting()));

        factionCount.entrySet().stream()
                .sorted(Map.Entry.<Faction, Long>comparingByValue().reversed())
                .limit(5)
                .forEach(e -> System.out.printf("%s has %d units%n", e.getKey(), e.getValue()));
    }
}
