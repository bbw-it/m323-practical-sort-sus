package data;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Film;
import model.Studio;

public class FilmDataGenerator {
    public static List<Film> generate(int count) {
        Random rand = new Random(42); // fester seed für reproduzierbarkeit
        String[] sampleTitles = {
                "Sternenreise", "Abenteuerwald", "Die Verlorenen", "Zeitreisende", "Nacht der Helden",
                "Magische Insel", "Letzte Mission", "Traumfänger", "Ewige Liebe", "Schattenläufer"
        };
        String[] sampleDirectors = {
                "Müller", "Schmidt", "Meier", "Weber", "Fischer",
                "Schneider", "Wagner", "Becker", "Hoffmann", "Schulz"
        };
        String[] sampleGenres = {
                "Drama", "Komödie", "Action", "Thriller", "Sci-Fi", "Fantasy", "Horror", "Romance"
        };

        List<Film> films = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String title = sampleTitles[rand.nextInt(sampleTitles.length)] + " " + (rand.nextInt(100) + 1);
            String director = sampleDirectors[rand.nextInt(sampleDirectors.length)];

            int year = 1980 + rand.nextInt(41); // zufälliges Datum
            int month = 1 + rand.nextInt(12);
            int day = 1 + rand.nextInt(28);
            LocalDate releaseDate = LocalDate.of(year, month, day);
            double rating = Math.round((rand.nextDouble() * 10.0) * 10.0) / 10.0;

            int genreCount = 1 + rand.nextInt(3); // 1–3 Genres zufällig auswählen
            List<String> genres = new ArrayList<>();
            for (int j = 0; j < genreCount; j++) {
                String g = sampleGenres[rand.nextInt(sampleGenres.length)];
                if (!genres.contains(g)) {
                    genres.add(g);
                }
            }
            films.add(new Film(title, director, releaseDate, rating, genres));
        }
        return films;
    }
    // main-Methode = 100 Datensätze in films.csv
    public static void main(String[] args) {
        List<Film> films = generate(100);
        try (PrintWriter pw = new PrintWriter(new FileWriter("films.csv"))) {
            pw.println("title,director,releaseDate,rating,genres");
            for (Film f : films) {
                String line = String.format(
                        "\"%s\",\"%s\",%s,%.1f,\"%s\"",
                        f.getTitle(),
                        f.getDirector(),
                        f.getReleaseDate(),
                        f.getRating(),
                        String.join(";", f.getGenres())
                );
                pw.println(line);
            }
            System.out.println("films.csv wurde erzeugt.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<Studio> generateStudios(int studioCount, int filmsPerStudio) {
        List<Studio> studios = new ArrayList<>();
        for (int i = 1; i <= studioCount; i++) {
            List<Film> filme = generate(filmsPerStudio);
            Studio s = new Studio("Studio " + i, filme);
            studios.add(s);
        }
        return studios;
    }
}