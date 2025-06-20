// src/main/java/BookSortDemo.java
import java.util.*;

public class BookSortDemo {
    public static void main(String[] args) {
        List<Book> books = BookDataGenerator.generateBooks(100);

        // Natural Order (by title)
        Collections.sort(books);
        System.out.println("Sorted by title (natural order):");
        books.stream().limit(3).forEach(System.out::println);

        // Reverse Order
        books.sort(Collections.reverseOrder());
        System.out.println("\nReverse order by title:");
        books.stream().limit(3).forEach(System.out::println);

        // Comparator class (by price)
        books.sort(new PriceComparator());
        System.out.println("\nSorted by price:");
        books.stream().limit(3).forEach(System.out::println);

        // Anonymous class (by pages)
        books.sort(new Comparator<Book>() {
            public int compare(Book b1, Book b2) {
                return Integer.compare(b1.getPages(), b2.getPages());
            }
        });
        System.out.println("\nSorted by pages (anonymous class):");
        books.stream().limit(3).forEach(System.out::println);

        // Lambda expression (by published date)
        books.sort((b1, b2) -> b1.getPublishedDate().compareTo(b2.getPublishedDate()));
        System.out.println("\nSorted by published date (lambda):");
        books.stream().limit(3).forEach(System.out::println);

        // Comparator chain (by genre, then price)
        books.sort(Comparator.comparing(Book::getGenre).thenComparing(Book::getPrice));
        System.out.println("\nSorted by genre, then price (comparator chain):");
        books.stream().limit(3).forEach(System.out::println);
    }
}