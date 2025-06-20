package ch.bbw.vn.models;

public class Haustier {
    private String name;
    private String tierart;

    public Haustier(String name, String tierart) {
        this.name = name;
        this.tierart = tierart;
    }

    public String getName() { return name; }
    public String getTierart() { return tierart; }

    @Override
    public String toString() {
        return tierart + ": " + name;
    }
}
