import java.math.BigDecimal;
import java.time.LocalDate;

public class Book implements Comparable<Book> {
    private String title;
    private int pages;
    private LocalDate publishedDate; // nicht primitiver Typ
    private Author author; // Assoziation mit anderer Klasse
    private BigDecimal price; // nicht primitiver Typ

    public Book(String title, int pages, LocalDate publishedDate, Author author, BigDecimal price) {
        this.title = title;
        this.pages = pages;
        this.publishedDate = publishedDate;
        this.author = author;
        this.price = price;
    }

    public String getTitle() { return title; }
    public int getPages() { return pages; }
    public LocalDate getPublishedDate() { return publishedDate; }
    public Author getAuthor() { return author; }
    public BigDecimal getPrice() { return price; }

    // Natural Order: nach Titel
    @Override
    public int compareTo(Book other) {
        return this.title.compareTo(other.title);
    }

    @Override
    public String toString() {
        return "%s (%s) – %s Seiten, %s, %s CHF".formatted(
            title, author.getName(), pages, publishedDate, price);
    }
}