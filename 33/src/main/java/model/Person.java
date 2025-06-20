package model;

import java.time.LocalDate;
import java.util.List;

public class Person implements Comparable<Person> {
  private String vorname;
  private String nachname;
  private int alter;
  private LocalDate geburtsdatum;
  private List<Haustier> haustiere; // Assoziation

  public Person(String vorname, String nachname, int alter, LocalDate geburtsdatum, List<Haustier> haustiere) {
    this.vorname = vorname;
    this.nachname = nachname;
    this.alter = alter;
    this.geburtsdatum = geburtsdatum;
    this.haustiere = haustiere;
  }

  // Getter
  public String getVorname() {
    return vorname;
  }

  public String getNachname() {
    return nachname;
  }

  public int getAlter() {
    return alter;
  }

  public LocalDate getGeburtsdatum() {
    return geburtsdatum;
  }

  public List<Haustier> getHaustiere() {
    return haustiere;
  }

  // Setter
  public void setVorname(String vorname) {
    this.vorname = vorname;
  }

  public void setNachname(String nachname) {
    this.nachname = nachname;
  }

  public void setAlter(int alter) {
    this.alter = alter;
  }

  public void setGeburtsdatum(LocalDate geburtsdatum) {
    this.geburtsdatum = geburtsdatum;
  }

  public void setHaustiere(List<Haustier> haustiere) {
    this.haustiere = haustiere;
  }

  @Override
  public int compareTo(Person other) {
    // Natural Order: Nachname
    return this.nachname.compareTo(other.nachname);
  }

  @Override
  public String toString() {
    return vorname + " " + nachname + ", Alter: " + alter + ", Geb.: " + geburtsdatum;
  }
}
