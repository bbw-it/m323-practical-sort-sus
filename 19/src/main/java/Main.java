import data.FilmDataGenerator;
import model.Studio;
import sort.FilmSortExamples;
import model.Film;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 100 Test-Filme
        List<Film> films = FilmDataGenerator.generate(100);
        FilmSortExamples.applyAllSorts(films);

        List<Studio> studios = FilmDataGenerator.generateStudios(3, 10); // z. B. 3 Studios à 10 Filme
        FilmSortExamples.sortWithinStudiosByRating(studios);
    }
}
