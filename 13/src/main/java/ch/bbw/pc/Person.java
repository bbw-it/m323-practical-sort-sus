package ch.bbw.pc;

import ch.bbw.pc.Adresse;

import java.time.LocalDate;
import java.util.List;

public class Person implements Comparable<Person> {
    private String name;
    private int alter;
    private LocalDate geburtstag;
    private List<String> hobbies;
    private Adresse adresse;

    public Person(String name, int alter, LocalDate geburtstag, List<String> hobbies, Adresse adresse) {
        this.name = name;
        this.alter = alter;
        this.geburtstag = geburtstag;
        this.hobbies = hobbies;
        this.adresse = adresse;
    }

    // Getter

    public String getName() { return name; }
    public int getAlter() { return alter; }
    public LocalDate getGeburtstag() { return geburtstag; }
    public List<String> getHobbies() { return hobbies; }
    public Adresse getAdresse() { return adresse; }

    // Natural Order nach Name
    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.name); // // Natural-Order
    }

    @Override
    public String toString() {
        return name + ", " + alter + ", " + geburtstag + ", " + hobbies + ", " + adresse;
    }
}
