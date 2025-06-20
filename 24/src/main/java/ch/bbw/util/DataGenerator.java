package ch.bbw.util;

import ch.bbw.model.Author;
import ch.bbw.model.Book;
import org.instancio.Instancio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.instancio.Select.field;

/*
    DataGenerator erzeugt 100 Autoren mit jeweils 0–5 Büchern.
    Schreibt zwei CSVs: testdata/authors.csv und testdata/books.csv
 */
public class DataGenerator {

    private static final int AUTHOR_COUNT = 100;

    /**
        Erzeugt einen zufälligen String aus exakt "length" Großbuchstaben (A–Z).
    */
    private static String randomLetters(int length) {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char c = (char) rnd.nextInt('A', 'Z' + 1);
            sb.append(c);
        }
        return sb.toString();
    }

    // Erzeugt 100 Author-Objekte (jeweils mit 0..5 Book-Instanzen) per Instancio.

    public static List<Author> generateAuthorsWithBooks() {
        List<Author> authors = Instancio.ofList(Author.class)
                .size(AUTHOR_COUNT)

                // a) Author.id: Zufalls-Int zwischen 1 und 1000
                .generate(field(Author::getId),
                          gen -> gen.ints().range(1, 1001))

                // b) birthDate: zufälliges LocalDate zw. 1950-01-01 und 1995-12-31
                .generate(field(Author::getBirthDate),
                          gen -> gen.temporal().localDate().past()
                                    .range(LocalDate.of(1950, 1, 1),
                                           LocalDate.of(1995, 12, 31)))

                // c) Bücherliste: 0 bis 5 Book-Instanzen
                .generate(field(Author::getBooks),
                          gen -> gen.collection().minSize(0).maxSize(5))

                // d) Book.id: Zufalls-Int zwischen 1 und 10000
                .generate(field(Book::getId),
                          gen -> gen.ints().range(1, 10001))

                // e) publishDate: zufälliges LocalDate zwischen 2000-01-01 und 2022-12-31
                .generate(field(Book::getPublishDate),
                          gen -> gen.temporal().localDate().past()
                                    .range(LocalDate.of(2000, 1, 1),
                                           LocalDate.of(2022, 12, 31)))

                // f) price: BigDecimal zwischen 5.00 und 100.00 (2 Dezimalstellen)
                .supply(field(Book::getPrice),
                        () -> {
                            double price = ThreadLocalRandom.current().nextDouble(5.00, 100.01);
                            double rounded = Math.round(price * 100) / 100.0;
                            return BigDecimal.valueOf(rounded);
                        })

                .create();

        // 2)   Eigene Logik, um Vorname, Nachname und Buchtitel mit
        //      jeweils zufälligen Buchstaben zu füllen:
        for (Author author : authors) {
            author.setFirstName(randomLetters(4));
            author.setLastName(randomLetters(4));
            for (Book book : author.getBooks()) {
                book.setTitle(randomLetters(5));
            }
        }

        return authors;
    }

    public static void main(String[] args) {
        // 1) Generiere 100 Autoren mit Büchern
        List<Author> authors = generateAuthorsWithBooks();

        // 2) Optional: Ausgabe in der Konsole
        System.out.println(">> Generierte 100 Autoren mit Instancio + eigenem Text-Random:");
        authors.forEach(System.out::println);

        // 3) Erstelle Ordner testdata/, falls noch nicht vorhanden
        File dataDir = new File("testdata");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        // 4) Schreibe CSV-Dateien
        writeAuthorsCsv(authors, "testdata/authors.csv");
        writeBooksCsv(authors, "testdata/books.csv");
        System.out.println(">> CSV-Dateien erstellt: testdata/authors.csv & testdata/books.csv");
    }

    /*
        Schreibt authors.csv mit den Spalten:
        id,firstName,lastName,birthDate,bookCount
    */
    private static void writeAuthorsCsv(List<Author> authors, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("id,firstName,lastName,birthDate,bookCount\n");
            for (Author a : authors) {
                writer.append(String.valueOf(a.getId())).append(",");
                writer.append("\"").append(a.getFirstName()).append("\",");
                writer.append("\"").append(a.getLastName()).append("\",");
                writer.append(a.getBirthDate().toString()).append(",");
                writer.append(String.valueOf(a.getBooks().size())).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Fehler beim Schreiben von authors.csv: " + e.getMessage());
        }
    }

    /*
        Schreibt books.csv mit den Spalten:
        id,authorId,title,publishDate,price
    */
    private static void writeBooksCsv(List<Author> authors, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("id,authorId,title,publishDate,price\n");
            for (Author a : authors) {
                for (Book b : a.getBooks()) {
                    writer.append(String.valueOf(b.getId())).append(",");
                    writer.append(String.valueOf(a.getId())).append(",");
                    writer.append("\"").append(b.getTitle()).append("\",");
                    writer.append(b.getPublishDate().toString()).append(",");
                    writer.append(b.getPrice().toString()).append("\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Fehler beim Schreiben von books.csv: " + e.getMessage());
        }
    }
}
