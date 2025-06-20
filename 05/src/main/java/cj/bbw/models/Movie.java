package cj.bbw.models;

import java.time.LocalDate;

public class Movie implements Comparable<Movie> {
    private String title;
    private LocalDate releaseDate;
    private double rating;
    private int durationMinutes;
    private Director director;

    public Movie(String title, LocalDate releaseDate, double rating, int durationMinutes, Director director) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.durationMinutes = durationMinutes;
        this.director = director;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public double getRating() {
        return rating;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public Director getDirector() {
        return director;
    }

    @Override
    public int compareTo(Movie other) {
        // Natural Order: Alphabetisch nach Titel
        return this.title.compareToIgnoreCase(other.title);
    }

    @Override
    public String toString() {
        return "\"" + title + "\" (" + releaseDate.getYear() + ") – "
                + rating + "/5 – " + durationMinutes + " min – Regie: " + director;
    }
}
