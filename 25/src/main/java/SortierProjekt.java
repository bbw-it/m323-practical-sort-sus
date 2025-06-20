import java.util.*;
import java.time.LocalDate;

public class SortierProjekt {
    public static void main(String[] args) {
        List<Videospiel> spiele = generiereTestDaten();

        // --- Comparator über eigene Klasse ---
        spiele.sort(new BewertungComparator());

        // --- Comparator: anonyme Klasse ---
        spiele.sort(new Comparator<Videospiel>() {
            public int compare(Videospiel v1, Videospiel v2) {
                return v1.releaseDatum.compareTo(v2.releaseDatum);
            }
        });

        // --- Lambda Expression ---
        spiele.sort((v1, v2) -> v1.entwickler.land.compareTo(v2.entwickler.land));

        // --- Comparator Chain / Mehrstufige Sortierung ---
        spiele.sort(Comparator.comparing((Videospiel v) -> v.bewertung)
                .thenComparing(v -> v.titel));

        // --- Natural Order ---
        Collections.sort(spiele);

        // --- Reverse Order ---
        spiele.sort(Comparator.reverseOrder());

        // --- Sortieren nach Plattformbezeichnung (Assoziation) ---
        spiele.sort(Comparator.comparing(v -> v.plattformen.get(0).bezeichnung));

        // Ausgabe
        spiele.forEach(System.out::println);
    }

    // --- Datengenerierung ---
    private static List<Videospiel> generiereTestDaten() {
        List<Videospiel> daten = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            List<Plattform> plattformen = new ArrayList<>();
            plattformen.add(new Plattform("Konsole" + (i % 5), 2000 + (i % 20)));

            daten.add(new Videospiel(
                    "Spiel" + i,
                    Math.round((Math.random() * 10) * 10.0) / 10.0,
                    LocalDate.of(2000 + (int)(Math.random()*24), 1 + (int)(Math.random()*12), 1 + (int)(Math.random()*28)),
                    new Entwickler("Studio" + (i % 10), "Land" + (i % 5)),
                    plattformen
            ));
        }
        return daten;
    }
}
