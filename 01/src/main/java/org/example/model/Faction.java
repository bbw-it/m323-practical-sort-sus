package org.example.model;

public class Faction {
    private String name;
    private String homeWorld;

    public Faction(String name, String homeWorld) {
        this.name = name;
        this.homeWorld = homeWorld;
    }

    public String getName() { return name; }
    public String getHomeWorld() { return homeWorld; }

    @Override
    public String toString() {
        return String.format("Faction{name='%s', homeWorld='%s'}", name, homeWorld);
    }
}