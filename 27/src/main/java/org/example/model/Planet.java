package org.example.model;

public class Planet {
    private String name;
    private int distanceFromEarth;
    private AtmosphereType atmosphereType;

    public Planet(String name, int distanceFromEarth, AtmosphereType atmosphereType) {
        this.name = name;
        this.distanceFromEarth = distanceFromEarth;
        this.atmosphereType = atmosphereType;
    }

    // Getter
    public String getName() { return name; }
    public int getDistanceFromEarth() { return distanceFromEarth; }
    public AtmosphereType getAtmosphereType() { return atmosphereType; }

    @Override
    public String toString() {
        return name + " (Atmosphere: " + atmosphereType + ", " + distanceFromEarth + " ly)";
    }
}