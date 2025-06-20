// src/main/java/Book.java
import java.time.LocalDate;
import java.util.Objects;

public class Book implements Comparable<Book> {
    private String title;
    private String author;
    private int pages;
    private int price;
    private LocalDate publishedDate;
    private Genre genre;

    public Book(String title, String author, int pages, int price, LocalDate publishedDate, Genre genre) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.price = price;
        this.publishedDate = publishedDate;
        this.genre = genre;
    }

    // Getters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPages() { return pages; }
    public int getPrice() { return price; }
    public LocalDate getPublishedDate() { return publishedDate; }
    public Genre getGenre() { return genre; }

    // Natural order: by title
    @Override
    public int compareTo(Book other) {
        return this.title.compareTo(other.title);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return pages == book.pages &&
                price == book.price &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(publishedDate, book.publishedDate) &&
                genre == book.genre;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, pages, price, publishedDate, genre);
    }

    @Override
    public String toString() {
        return title + " by " + author + " (" + genre + ", " + publishedDate + ", " + pages + " pages, CHF " + price + ")";
    }
}