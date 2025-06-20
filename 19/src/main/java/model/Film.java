package model;

import java.time.LocalDate;
import java.util.List;

public class Film implements Comparable<Film> {
    private String title;
    private String director;
    private LocalDate releaseDate;
    private double rating;
    private List<String> genres;

    public Film(String title, String director, LocalDate releaseDate, double rating, List<String> genres) {
        this.title = title;
        this.director = director;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }
    public String getDirector() {
        return director;
    }
    public LocalDate getReleaseDate() {
        return releaseDate;
    }
    public double getRating() {
        return rating;
    }
    public List<String> getGenres() {
        return genres;
    }
    @Override
    public String toString() {
        return String.format(
                "Film{title='%s', director='%s', releaseDate=%s, rating=%.1f, genres=%s}",
                title, director, releaseDate, rating, genres
        );
    }
    // sortierung nach titel
    @Override
    public int compareTo(Film other) {
        return this.title.compareToIgnoreCase(other.title); // Natural Order
    }
}
