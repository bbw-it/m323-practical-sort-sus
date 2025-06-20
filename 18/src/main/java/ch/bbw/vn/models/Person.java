package ch.bbw.vn.models;

import java.time.LocalDate;
import java.util.List;

public class Person implements Comparable<Person> {

    private String name;
    private int alter;
    private LocalDate geburtsdatum;
    private List<String> hobbys;
    private boolean istStudent;
    private List<Haustier> haustiere;

    public Person(String name, int alter, LocalDate geburtsdatum, List<String> hobbys, boolean istStudent, List<Haustier> haustiere) {
        this.name = name;
        this.alter = alter;
        this.geburtsdatum = geburtsdatum;
        this.hobbys = hobbys;
        this.istStudent = istStudent;
        this.haustiere = haustiere;
    }

    public String getName() { return name; }
    public int getAlter() { return alter; }
    public LocalDate getGeburtsdatum() { return geburtsdatum; }
    public List<String> getHobbys() { return hobbys; }
    public boolean isIstStudent() { return istStudent; }
    public List<Haustier> getHaustiere() { return haustiere; }

    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.name); //Natural Order
    }

    @Override
    public String toString() {
        return name + " (" + alter + ", " + geburtsdatum + ", " + hobbys + ", " + istStudent + ", Haustiere: " + haustiere + ")";
    }
}
