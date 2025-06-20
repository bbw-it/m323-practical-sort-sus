package cj.bbw.services;

import cj.bbw.models.Director;
import cj.bbw.models.Movie;
import com.google.gson.*;

import java.io.FileReader;
import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JsonMovieImporter {

    public static List<Movie> loadMoviesFromJson(String filePath) {
        List<Movie> movies = new ArrayList<>();

        try (Reader reader = new FileReader(filePath)) {
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();

            for (JsonElement element : jsonArray) {
                JsonObject obj = element.getAsJsonObject();

                // Pflichtfelder prüfen
                if (!obj.has("title") || !obj.has("year") || !obj.has("director")) continue;

                String title = obj.get("title").getAsString();
                int year = obj.get("year").getAsInt();
                double rating = obj.get("rating").getAsDouble();
                int duration = obj.get("duration").getAsInt();

                // Zugriff auf geschachteltes director-Objekt
                JsonObject directorObj = obj.getAsJsonObject("director");
                String directorName = directorObj.get("name").getAsString();
                String country = directorObj.get("country").getAsString();
                int birthYear = directorObj.get("birthYear").getAsInt();

                Director director = new Director(directorName, country, birthYear);
                Movie movie = new Movie(title, LocalDate.of(year, 1, 1), rating, duration, director);
                movies.add(movie);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return movies;
    }
}
