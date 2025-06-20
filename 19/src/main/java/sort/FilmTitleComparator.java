package sort;

import java.util.Comparator;
import model.Film;

// comparator-klasse - sortiert nach titel
public class FilmTitleComparator implements Comparator<Film> {
    @Override
    public int compare(Film f1, Film f2) {
        return f1.getTitle().compareToIgnoreCase(f2.getTitle());
    }
}
