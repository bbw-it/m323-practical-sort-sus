package org.example.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Zookeeper {
    private String name;
    private int experienceYears;
    private List<AlienAnimal> assignedCreatures;

    public Zookeeper(String name, int experienceYears) {
        this.name = name;
        this.experienceYears = experienceYears;
        this.assignedCreatures = new ArrayList<>();
    }

    // Getter
    public String getName() { return name; }
    public int getExperienceYears() { return experienceYears; }
    public List<AlienAnimal> getAssignedCreatures() { return new ArrayList<>(assignedCreatures); } // Defensive copy

    public void assignCreature(AlienAnimal creature) {
        this.assignedCreatures.add(creature);
    }

    public void assignCreatures(List<AlienAnimal> creatures) {
        this.assignedCreatures.addAll(creatures);
    }

    public void sortAssignedCreaturesByDateDiscovered() {
        // Verwendet den statischen Comparator von AlienAnimal
        this.assignedCreatures.sort(AlienAnimal.BY_DATE_DISCOVERED_ASC);
    }

    public double getAverageIQOfAssignedCreatures() {
        if (assignedCreatures.isEmpty()) {
            return 0;
        }
        return assignedCreatures.stream()
                .mapToDouble(animal -> animal.getBrainProfile().getIqLevel())
                .average()
                .orElse(0.0);
    }

    public DangerLevel getMaxDangerLevelOfAssignedCreatures() {
        if (assignedCreatures.isEmpty()) {
            return DangerLevel.HARMLESS;
        }
        return assignedCreatures.stream()
                .map(AlienAnimal::getDangerLevel)
                .max(Comparator.naturalOrder()) // Enum implements Comparable
                .orElse(DangerLevel.HARMLESS);
    }

    @Override
    public String toString() {
        return "Zookeeper: " + name + " (" + experienceYears + " yrs exp.) - Manages " + assignedCreatures.size() + " creatures.";
    }
}