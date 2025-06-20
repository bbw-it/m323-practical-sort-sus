package ch.bbw.vn.services;

import ch.bbw.vn.models.Haustier;
import ch.bbw.vn.models.Person;

import java.time.LocalDate;
import java.util.*;

public class DataGenerator {

    private static final List<String> namen = Arrays.asList("Lina", "Noah", "Mila", "Luca", "Lea", "Tim");
    private static final List<String> hobbyListe = Arrays.asList("Lesen", "Schwimmen", "Reiten", "Programmieren", "Musik");

    public static List<Person> generatePersonen(int anzahl) {
        List<Person> personen = new ArrayList<>();
        Random r = new Random();

        for (int i = 0; i < anzahl; i++) {
            String name = namen.get(r.nextInt(namen.size())) + i;
            int alter = 15 + r.nextInt(30);
            LocalDate geburt = LocalDate.of(1990 + r.nextInt(20), 1 + r.nextInt(12), 1 + r.nextInt(28));
            List<String> hobbys = Arrays.asList(hobbyListe.get(r.nextInt(hobbyListe.size())));
            boolean istStudent = r.nextBoolean();
            List<Haustier> haustiere = generiereHaustiere(r);

            personen.add(new Person(name, alter, geburt, hobbys, istStudent, haustiere));
        }
        return personen;
    }

    private static List<Haustier> generiereHaustiere(Random r) {
        List<Haustier> haustiere = new ArrayList<>();
        String[] tiernamen = {"Bello", "Miezi", "Luna", "Rocky", "Nemo"};
        String[] tierarten = {"Hund", "Katze", "Hamster", "Fisch", "Vogel"};

        int anzahl = r.nextInt(3); // max. 2 Haustiere
        for (int i = 0; i < anzahl; i++) {
            String name = tiernamen[r.nextInt(tiernamen.length)];
            String art = tierarten[r.nextInt(tierarten.length)];
            haustiere.add(new Haustier(name, art));
        }
        return haustiere;
    }
}
