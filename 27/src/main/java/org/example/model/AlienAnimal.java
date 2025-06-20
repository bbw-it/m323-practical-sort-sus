package org.example.model;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Locale;

public class AlienAnimal implements Comparable<AlienAnimal> {
    private String speciesName;
    private Planet originPlanet;
    private double weightInTons;
    private LocalDate dateDiscovered;
    private DangerLevel dangerLevel;
    private IntelligenceProfile brainProfile;

    public AlienAnimal(String speciesName, Planet originPlanet, double weightInTons, LocalDate dateDiscovered, DangerLevel dangerLevel, IntelligenceProfile brainProfile) {
        this.speciesName = speciesName;
        this.originPlanet = originPlanet;
        this.weightInTons = weightInTons;
        this.dateDiscovered = dateDiscovered;
        this.dangerLevel = dangerLevel;
        this.brainProfile = brainProfile;
    }

    // Getter
    public String getSpeciesName() { return speciesName; }
    public Planet getOriginPlanet() { return originPlanet; }
    public double getWeightInTons() { return weightInTons; }
    public LocalDate getDateDiscovered() { return dateDiscovered; }
    public DangerLevel getDangerLevel() { return dangerLevel; }
    public IntelligenceProfile getBrainProfile() { return brainProfile; }

    // Natural Order: Sortierung nach Speziesnamen (alphabetisch)
    @Override
    public int compareTo(AlienAnimal other) {
        return this.speciesName.compareTo(other.speciesName);
    }

    @Override
    public String toString() {
        return String.format(Locale.GERMAN,
                "Species: %-22s | Planet: %-25s | Weight: %7.2f T | Discovered: %s | Danger: %-15s | Intel: %s",
                speciesName, originPlanet.getName(), weightInTons, dateDiscovered, dangerLevel, brainProfile
        );
    }

    // --- Comparator-Attribute in der Datenklasse ---
    public static final Comparator<AlienAnimal> BY_WEIGHT_ASC = Comparator.comparingDouble(AlienAnimal::getWeightInTons);
    public static final Comparator<AlienAnimal> BY_WEIGHT_DESC = Comparator.comparingDouble(AlienAnimal::getWeightInTons).reversed();
    public static final Comparator<AlienAnimal> BY_DATE_DISCOVERED_ASC = Comparator.comparing(AlienAnimal::getDateDiscovered);
}