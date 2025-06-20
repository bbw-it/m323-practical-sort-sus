package ch.bbw.sven_weber.model;

/**
 * @author Sven Weebr
 */
public class Country {

    private final String name;


    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Country{" +
                ", name='" + name + '\'' +
                '}';
    }
}
