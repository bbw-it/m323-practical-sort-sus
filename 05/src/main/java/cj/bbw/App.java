package cj.bbw;

import cj.bbw.models.Movie;
import cj.bbw.services.JsonMovieImporter;
import java.util.Collections;
import java.util.Comparator;

import java.util.List;

/*
* MAIN
* @author ChaimaaJ
* */

public class App {
    public static void main(String[] args) {
        List<Movie> movies = JsonMovieImporter.loadMoviesFromJson("src/main/resources/movies.json");

        // anonyme klasse. vergleicht-sortiert movies nach direktor a-z (ignoriert gross kleinschreibung)
        Collections.sort(movies, new Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                return m1.getDirector().getName().compareToIgnoreCase(m2.getDirector().getName());
            }
        });

        // druckt 5 movies
        System.out.println("\nSortiert nach Titel A-Z:");
        for (int i = 0; i < 5; i++) {
            System.out.println(movies.get(i));
        }

        // lambda comparator sortiert sauer kurz bis lang
        movies.sort((m1, m2) -> Integer.compare(m1.getDurationMinutes(), m2.getDurationMinutes()));

        // gibt 5 raus
        System.out.println("\nSortiert nach Dauer Kurz-Lang:");
        for (int i = 0; i < 5; i++) {
            System.out.println(movies.get(i));
        }

        // comparaTo chain nach jahr und nach titel
        movies.sort(Comparator
                .comparing((Movie m) -> m.getReleaseDate().getYear())
                .thenComparing(Movie::getTitle));

        // 5 Stück printen
        System.out.println("\nSortiert nach Jahr, dann Titel:");
        for (int i = 0; i < 5; i++) {
            System.out.println(movies.get(i));
        }

        // compare to chain  – lang zu kurz (Reverse )
        movies.sort(Comparator.comparingInt(Movie::getDurationMinutes).reversed());

        System.out.println("\nSortiert nach Länge (lang → kurz):");
        for (int i = 0; i < 5; i++) {
            System.out.println(movies.get(i));
        }


// print line
        System.out.println("\nTop 5 Filme nach Bewertung (Stream sortiert):");
        // cmparator chain sortier nach bewertung
        movies.stream()
                .sorted(Comparator.comparingDouble(Movie::getRating).reversed())
                .limit(5)
                .forEach(System.out::println);





    }
}
