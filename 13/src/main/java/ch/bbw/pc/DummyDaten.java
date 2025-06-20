package ch.bbw.pc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DummyDaten {
    static String[] namen = {"Anna", "Ben", "Clara", "David", "Eva", "Felix", "Greta", "Hans", "Ida", "Jonas"};
    static String[] städte = {"Zürich", "Bern", "Basel", "Luzern", "Genf", "St. Gallen"};
    static String[] strassen = {"Bahnhofstrasse", "Hauptstrasse", "Dorfweg", "Sonnenweg", "Industriestrasse"};
    static String[] hobbies = {"Lesen", "Sport", "Musik", "Reisen", "Kochen", "Wandern", "Schwimmen"};

    public static List<Person> generierePersonen(int anzahl) {
        List<Person> personen = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < anzahl; i++) {
            String name = namen[rand.nextInt(namen.length)];
            int alter = 18 + rand.nextInt(60);
            LocalDate geburtstag = LocalDate.now().minusYears(alter).minusDays(rand.nextInt(365));
            List<String> hobbyListe = List.of(
                    hobbies[rand.nextInt(hobbies.length)],
                    hobbies[rand.nextInt(hobbies.length)]
            );
            Adresse adresse = new Adresse(
                    städte[rand.nextInt(städte.length)],
                    strassen[rand.nextInt(strassen.length)] + " " + (1 + rand.nextInt(99))
            );

            personen.add(new Person(name, alter, geburtstag, hobbyListe, adresse));
        }

        return personen;
    }
}
