package org.example.model;

import java.time.LocalDate;

public class Unit implements Comparable<Unit> {
    private String name;
    private int firepower;
    private double armorRating;
    private LocalDate deploymentDate;
    private UnitType unitType;
    private Faction faction;

    public Unit(String name, int firepower, double armorRating, LocalDate deploymentDate, UnitType unitType, Faction faction) {
        this.name = name;
        this.firepower = firepower;
        this.armorRating = armorRating;
        this.deploymentDate = deploymentDate;
        this.unitType = unitType;
        this.faction = faction;
    }

    public String getName() { return name; }
    public int getFirepower() { return firepower; }
    public double getArmorRating() { return armorRating; }
    public LocalDate getDeploymentDate() { return deploymentDate; }
    public UnitType getUnitType() { return unitType; }
    public Faction getFaction() { return faction; }

    @Override
    public String toString() {
        return String.format("Unit{name='%s', FP=%d, armor=%.1f, deployed=%s, type=%s, faction=%s}",
                name, firepower, armorRating, deploymentDate, unitType, faction.getName());
    }

    @Override
    public int compareTo(Unit other) {
        return this.name.compareTo(other.name);
    }
}
