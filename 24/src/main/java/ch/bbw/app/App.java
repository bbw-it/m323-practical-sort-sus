package ch.bbw.app;

import ch.bbw.comparator.AuthorAttributeComparator;
import ch.bbw.model.Author;
import ch.bbw.util.DataGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

 // Sortiert Autoren nach verschiedenen Kriterien und speichert jede Sortierung in eine separate CSV-Datei.

public class App {

    public static void main(String[] args) {
        File dataDir = new File("testdata");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        // 1. Generiere 100 Autoren-Datensätze
        List<Author> originalAuthors = DataGenerator.generateAuthorsWithBooks();

        // 1. Comparable (Natural Order nach lastName)
        List<Author> list = new ArrayList<>(originalAuthors);
        Collections.sort(list);
        writeSortedAuthorsCsv(list, "testdata/sorted_1_lastname.csv");

        // 2. Externe Comparator-Klasse (lastName → bookCount)
        list = new ArrayList<>(originalAuthors);
        Collections.sort(list, new AuthorAttributeComparator());
        writeSortedAuthorsCsv(list, "testdata/sorted_2_lastname_bookcount.csv");

        // 3. Anonyme Klasse (sortiere nach birthDate)
        list = new ArrayList<>(originalAuthors);
        Collections.sort(list, new Comparator<Author>() {
            @Override
            public int compare(Author a1, Author a2) {
                return a1.getBirthDate().compareTo(a2.getBirthDate());
            }
        });
        writeSortedAuthorsCsv(list, "testdata/sorted_3_birthdate.csv");

        // 4. Lambda Expression (sortiere nach Anzahl Bücher)
        list = new ArrayList<>(originalAuthors);
        Collections.sort(list, (a1, a2) -> Integer.compare(a1.getBooks().size(), a2.getBooks().size()));
        writeSortedAuthorsCsv(list, "testdata/sorted_4_bookcount.csv");

        // 5. Comparator-Attribut IN Author (BY_NUM_BOOKS)
        list = new ArrayList<>(originalAuthors);
        Collections.sort(list, Author.BY_NUM_BOOKS);
        writeSortedAuthorsCsv(list, "testdata/sorted_5_comparator_in_author.csv");

        // 6. Comparator-Chain (bookCount asc lastName asc)
        list = new ArrayList<>(originalAuthors);
        Collections.sort(list, Author.BY_NUM_BOOKS_THEN_LASTNAME);
        writeSortedAuthorsCsv(list, "testdata/sorted_6_bookcount_lastname.csv");

        // 7. Reverse Order (Natural Order reverse nach lastName)
        list = new ArrayList<>(originalAuthors);
        Collections.sort(list, Comparator.reverseOrder());
        writeSortedAuthorsCsv(list, "testdata/sorted_7_lastname_desc.csv");

        // 8. Mehrstufige Sortierung (lastName birthDate bookCount)
        list = new ArrayList<>(originalAuthors);
        Comparator<Author> complexComparator = Comparator
                .comparing(Author::getLastName)
                .thenComparing(Author::getBirthDate)
                .thenComparing(a -> a.getBooks().size());
        Collections.sort(list, complexComparator);
        writeSortedAuthorsCsv(list, "testdata/sorted_8_lastname_birthdate_bookcount.csv");

        System.out.println(">> Alle Sortierungen wurden erfolgreich als CSV gespeichert in /testdata/");
    }

   
    // Schreibt die sortierte Liste der Autoren in eine CSV-Datei.
   
    private static void writeSortedAuthorsCsv(List<Author> authors, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("id,firstName,lastName,birthDate,bookCount\n");
            for (Author a : authors) {
                writer.append(a.getId() + ",")
                      .append("\"" + a.getFirstName() + "\",")
                      .append("\"" + a.getLastName() + "\",")
                      .append(a.getBirthDate().toString() + ",")
                      .append(String.valueOf(a.getBooks().size()))
                      .append("\n");
            }
        } catch (IOException e) {
            System.err.println("Fehler beim Schreiben der CSV: " + e.getMessage());
        }
    }
}
