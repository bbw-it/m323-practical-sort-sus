package util;

import model.Haustier;
import model.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestdatenGenerator {
  public static List<Person> generiereTestdaten() {
    List<Person> personen = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      List<Haustier> haustiere = new ArrayList<>();
      if (i % 2 == 0) {
        haustiere.add(new Haustier(UUID.randomUUID(), "Bello" + i, "Hund"));
      }
      personen.add(new Person(
        "Vorname" + i,
        "Nachname" + (100 - i),
        20 + (i % 30),
        LocalDate.of(1990 + (i % 20), (i % 12) + 1, (i % 28) + 1),
        haustiere
      ));
    }
    return personen;
  }
}
