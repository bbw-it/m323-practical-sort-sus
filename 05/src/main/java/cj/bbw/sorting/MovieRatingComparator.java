package cj.bbw.sorting;

import cj.bbw.models.Movie;
import java.util.Comparator;

public class MovieRatingComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie m1, Movie m2) {
        return Double.compare(m2.getRating(), m1.getRating()); // Absteigend
    }
}
