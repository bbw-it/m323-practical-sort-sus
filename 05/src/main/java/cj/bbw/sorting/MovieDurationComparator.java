package cj.bbw.sorting;

import cj.bbw.models.Movie;
import java.util.Comparator;

/**
 * Länge comparator
 * ChaimaaJ
 */
public class MovieDurationComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie m1, Movie m2) {
        return Integer.compare(m1.getDurationMinutes(), m2.getDurationMinutes());
    }
}
