import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Book> books = generateBooks();

        // Comparator-Klasse, die nach Seitenzahlen sortiert
        class CompareByPages implements Comparator<Book> {
            public int compare(Book b1, Book b2) {
                return Integer.compare(b1.getPages(), b2.getPages());
            }
        }
        books.sort(new CompareByPages());

        books.sort(new Comparator<>() {
            public int compare(Book b1, Book b2) {
                return b1.getPublishedDate().compareTo(b2.getPublishedDate());
            }
        });

        // Lambda
        books.sort((b1, b2) -> b1.getPrice().compareTo(b2.getPrice()));

        // Comparator-Chain
        books.sort(Comparator.comparing((Book b) -> b.getAuthor().getName())
                             .thenComparing(Book::getTitle)); // Comparator-Chain

        // Natural Order
        Collections.sort(books); // sortiert nach Titel

        // Reverse Order
        books.sort(Comparator.comparing(Book::getTitle).reversed());

        books.stream().limit(10).forEach(System.out::println);
    }

    private static List<Book> generateBooks() {
        List<Book> list = new ArrayList<>();
        Random rand = new Random();
        String[] titles = {"Java", "Python", "TypeScript", "C#", "PHP"};
        String[] authorNames = {"James Gosling", "Guido van Rossum", "Anders Hejlsberg", "Anders Hejlsberg", "Rasmus Lerdorf"};

        // Random 100 Bücher generieren
        for (int i = 0; i < 100; i++) {
            String title = titles[rand.nextInt(titles.length)] + " #" + i;
            int pages = 100 + rand.nextInt(400);
            LocalDate date = LocalDate.of(1990 + rand.nextInt(30), 1 + rand.nextInt(12), 1 + rand.nextInt(28));
            Author author = new Author(authorNames[rand.nextInt(authorNames.length)], 1950 + rand.nextInt(50));
            BigDecimal price = BigDecimal.valueOf(10 + rand.nextDouble() * 90).setScale(2, BigDecimal.ROUND_HALF_UP);
            list.add(new Book(title, pages, date, author, price));
        }
        return list;
    }
}