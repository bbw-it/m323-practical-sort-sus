package cj.bbw.models;

public class Director {
    private String name;
    private String country;
    private int birthYear;

    public Director(String name, String country, int birthYear) {
        this.name = name;
        this.country = country;
        this.birthYear = birthYear;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getBirthYear() {
        return birthYear;
    }

    @Override
    public String toString() {
        return name + " (" + country + ", " + birthYear + ")";
    }
}
