// src/main/java/BookDataGenerator.java
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BookDataGenerator {
    private static final String[] TITLES = {"Java Basics", "Advanced Java", "Spring Boot", "Clean Code", "Algorithms"};
    private static final String[] AUTHORS = {"Alice", "Bob", "Charlie", "Diana", "Eve"};
    private static final Genre[] GENRES = Genre.values();

    public static List<Book> generateBooks(int count) {
        List<Book> books = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            String title = TITLES[rand.nextInt(TITLES.length)] + " " + (i + 1);
            String author = AUTHORS[rand.nextInt(AUTHORS.length)];
            int pages = 100 + rand.nextInt(400);
            int price = 10 + rand.nextInt(90);
            LocalDate publishedDate = LocalDate.of(2000 + rand.nextInt(24), 1 + rand.nextInt(12), 1 + rand.nextInt(28));
            Genre genre = GENRES[rand.nextInt(GENRES.length)];
            books.add(new Book(title, author, pages, price, publishedDate, genre));
        }
        return books;
    }
}