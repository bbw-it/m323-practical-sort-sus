package sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.Film;
import model.Studio;

public class FilmSortExamples {

    public static void applyAllSorts(List<Film> originalList) {
        List<Film> list1 = new ArrayList<>(originalList);
        Collections.sort(list1, new FilmTitleComparator());
        System.out.println("1) Sortiert nach Titel (FilmTitleComparator):");
        list1.forEach(System.out::println);
        System.out.println();

        List<Film> list2 = new ArrayList<>(originalList);
        Collections.sort(list2, new Comparator<Film>() { // anonyme Klasse
            @Override
            public int compare(Film f1, Film f2) {
                return f1.getDirector().compareToIgnoreCase(f2.getDirector());
            }
        });
        System.out.println("2) Sortiert nach Director (anonyme Klasse):");
        list2.forEach(System.out::println);
        System.out.println();

        List<Film> list3 = new ArrayList<>(originalList);
        list3.sort((f1, f2) -> f1.getReleaseDate().compareTo(f2.getReleaseDate())); // Lambda
        System.out.println("3) Sortiert nach ReleaseDate (Lambda):");
        list3.forEach(System.out::println);
        System.out.println();

        List<Film> list4 = new ArrayList<>(originalList);
        list4.sort(Comparator
                .comparingDouble(Film::getRating)
                .thenComparing(Film::getReleaseDate) // Comparator-Chain
        );
        System.out.println("4) Sortiert nach Rating, dann nach ReleaseDate (Comparator-Chain):");
        list4.forEach(System.out::println);
        System.out.println();

        List<Film> list5 = new ArrayList<>(originalList);
        Collections.sort(list5);
        System.out.println("5) Sortiert nach Titel (Natural Order, Comparable):");
        list5.forEach(System.out::println);
        System.out.println();

        List<Film> list6 = new ArrayList<>(originalList);
        list6.sort(Collections.reverseOrder()); // Reverse Order
        System.out.println("6) Sortiert nach Titel (Reverse Order):");
        list6.forEach(System.out::println);
        System.out.println();
    }
    public static void sortWithinStudiosByRating(List<Studio> studios) {
        for (Studio s : studios) {
            s.getFilme().sort(Comparator.comparingDouble(Film::getRating)); // Sortieren innerhalb der Assoziation
            System.out.println("Filme von " + s.getName() + " sortiert nach Rating:");
            s.getFilme().forEach(System.out::println);
            System.out.println();
        }
    }
}