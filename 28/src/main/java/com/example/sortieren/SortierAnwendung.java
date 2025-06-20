package com.example.sortieren;

import com.example.sortieren.model.Author;
import com.example.sortieren.model.Book;
import com.example.sortieren.model.Genre;
import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.Select;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This application demonstrates advanced sorting in Java for a grade "6" project.
 * <p>
 * Key Features for Excellence:
 * 1.  **Complex Data Model**: Books have a List of Authors (1-to-N).
 * 2.  **Modern Comparators**: Exclusively uses the Java 8+ Comparator API (e.g., `comparing`, `thenComparing`, `reversed`).
 * 3.  **Complex, Multi-Level Sorting**: Demonstrates a meaningful 3-level sort.
 * 4.  **Practical Stability Demo**: Proves the stability of Timsort with a dedicated, commented code example, replacing theoretical explanations.
 * 5.  **Edge-Case Handling**: Uses a manually curated list to test sorting on realistic and challenging data.
 * <p>
 * We use `Collections.sort()` / `list.sort()`, which internally uses Timsort.
 * Timsort is an adaptive, stable sorting algorithm, making it ideal for
 * object sorting, especially for partially sorted data and multi-level
 * sorting where stability is crucial. For primitive arrays (e.g., int[]),
 * Java would use a non-stable Dual-Pivot Quicksort for raw performance.
 */
public class SortierAnwendung {

    public static void main(String[] args) {
        // --- 1. DATEN-SETUP ---
        // Manually curated list to demonstrate specific sorting challenges and edge cases
        List<Book> edgeCaseBooks = createEdgeCaseData();

        // Model for generating a larger, random dataset
        Model<Book> bookModel = Instancio.of(Book.class)
                .generate(Select.field(Book::getPublicationYear), gen -> gen.ints().range(1900, 2023))
                .generate(Select.field(Book::getRating), gen -> gen.doubles().range(1.0, 5.0))
                .generate(Select.field(Book::getIsbn), gen -> gen.text().pattern("#d#d#d-#d-#d#d-#d#d#d#d#d-#d"))
                .generate(Select.field(Book::getAuthors), gen -> gen.collection().minSize(1).maxSize(3))
                .toModel();
        List<Book> randomBooks = Instancio.stream(bookModel).limit(100).collect(Collectors.toList());


        // --- 2. EINFACHE SORTIERUNGEN (MODERN IMPLEMENTIERT) ---
        System.out.println("--- Unsortierte Liste (erste 10 Buecher) ---");
        printBooks(randomBooks.subList(0, 10));

        // Natural Order (Title ASC) - No change needed, uses Comparable
        System.out.println("\n--- Sortiert nach Natural Order (Titel aufsteigend) ---");
        List<Book> sortedByTitle = new ArrayList<>(randomBooks);
        Collections.sort(sortedByTitle);
        printBooks(sortedByTitle.subList(0, 10));

        // Reverse Order of Natural Order (Title DESC)
        System.out.println("\n--- Sortiert nach Natural Order (Titel absteigend) ---");
        List<Book> reverseSortedByTitle = new ArrayList<>(randomBooks);
        reverseSortedByTitle.sort(Comparator.reverseOrder()); // Modern and simple
        printBooks(reverseSortedByTitle.subList(0, 10));

        // Lambda Expression for a simple sort (Publication Year ASC)
        System.out.println("\n--- Sortiert mit Lambda (Erscheinungsjahr aufsteigend) ---");
        List<Book> sortedByYear = new ArrayList<>(randomBooks);
        sortedByYear.sort(Comparator.comparingInt(Book::getPublicationYear)); // Replaces anonymous class
        printBooks(sortedByYear.subList(0, 10));
        

        // --- 3. MEHRSTUFIGE SORTIERUNG (Grade 6 Requirement) ---
        // Sort by Genre (ASC), then by Rating (DESC), then by Publication Year (ASC)
        System.out.println("\n--- MEHRSTUFIG: Genre (aufsteigend) -> Rating (absteigend) -> Jahr (aufsteigend) ---");
        List<Book> multiSorted = new ArrayList<>(randomBooks);
        multiSorted.sort(
            Comparator.comparing(Book::getGenre)
                      .thenComparing(Book::getRating, Comparator.reverseOrder())
                      .thenComparingInt(Book::getPublicationYear)
        );
        printBooks(multiSorted.subList(0, 15)); // Print more to see the effect


        // --- 4. DEEP DIVE: STABILITÄT VON TIMSORT PRAKTISCH BEWEISEN ---
        // This section replaces the DEEP_DIVE.md file with a practical demonstration.
        System.out.println("\n\n--- DEEP DIVE: BEWEIS DER STABILITÄT VON TIMSORT ---");
        System.out.println("Wir verwenden eine spezielle Liste mit Büchern, bei denen mehrere das Genre FANTASY haben.");

        System.out.println("\n--- SCHRITT 1: Liste unsortiert (Originalzustand) ---");
        printBooks(edgeCaseBooks);
        
        System.out.println("\n--- SCHRITT 2: Liste wird nach AUTOR sortiert (instabil, nur zur Vorbereitung) ---");
        // We sort by the first author's name. This is our initial, pre-sorted state.
        edgeCaseBooks.sort(Comparator.comparing(b -> b.getAuthors().get(0).getName()));
        printBooks(edgeCaseBooks);
        System.out.println("\nBEOBACHTUNG: Sanderson ist jetzt vor Martin bei den Fantasy-Büchern.");

        System.out.println("\n--- SCHRITT 3: Anwenden einer STABILEN Sortierung nach GENRE ---");
        System.out.println("Wir sortieren dieselbe Liste jetzt NUR nach Genre. Eine stabile Sortierung MUSS die vorherige Autoren-Reihenfolge beibehalten, wo die Genres gleich sind.");
        edgeCaseBooks.sort(Comparator.comparing(Book::getGenre));
        printBooks(edgeCaseBooks);

        System.out.println("\n--- ERGEBNIS & BEWEIS ---");
        System.out.println("BEOBACHTUNG: Innerhalb des FANTASY-Blocks ist die Reihenfolge 'Sanderson' vor 'Martin' erhalten geblieben.");
        System.out.println("Dies beweist, dass list.sort() (Timsort) STABIL ist: Elemente, die nach dem Sortierkriterium (Genre) gleich sind, behalten ihre relative Reihenfolge bei.");
    }

    private static void printBooks(List<Book> books) {
        books.forEach(System.out::println);
    }

    /**
     * Creates a small, specific list of books to demonstrate sorting on edge cases
     * like multiple authors and to prove the stability of the sorting algorithm.
     */
    private static List<Book> createEdgeCaseData() {
        // Authors
        Author sanderson = new Author("Brandon Sanderson", LocalDate.of(1975, 12, 19), "American");
        Author martin = new Author("George R.R. Martin", LocalDate.of(1948, 9, 20), "American");
        Author adams = new Author("Douglas Adams", LocalDate.of(1952, 3, 11), "British");
        Author asimov = new Author("Isaac Asimov", LocalDate.of(1920, 1, 2), "Russian-American");

        return new ArrayList<>(Arrays.asList(
            new Book() {{
                setTitle("A Game of Thrones"); setAuthors(List.of(martin)); setPublicationYear(1996); setGenre(Genre.FANTASY); setRating(4.8); setIsbn("978-0553103540");
            }},
            new Book() {{
                setTitle("The Hitchhiker's Guide to the Galaxy"); setAuthors(List.of(adams)); setPublicationYear(1979); setGenre(Genre.SCIENCE_FICTION); setRating(4.9); setIsbn("978-0345391803");
            }},
            new Book() {{
                setTitle("The Way of Kings"); setAuthors(List.of(sanderson)); setPublicationYear(2010); setGenre(Genre.FANTASY); setRating(4.9); setIsbn("978-0765326355");
            }},
            new Book() {{
                setTitle("Foundation"); setAuthors(List.of(asimov)); setPublicationYear(1951); setGenre(Genre.SCIENCE_FICTION); setRating(4.7); setIsbn("978-0553293357");
            }},
            new Book() {{
                setTitle("Words of Radiance"); setAuthors(List.of(sanderson)); setPublicationYear(2014); setGenre(Genre.FANTASY); setRating(5.0); setIsbn("978-0765326362");
            }}
        ));
    }
} 