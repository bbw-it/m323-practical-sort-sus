package com.example.sortieren.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Datenklasse mit 5 Attributen, 4 versch. Datentypen, 2 kein primitives/Wrapperclass.
 * Erfüllt auch die erweiterte Anforderung einer Assoziation (zu Author).
 * <p>
 * NOTE FOR GRADE 6: Association changed to a more complex 1-to-N relationship (a book can have multiple authors).
 */
public class Book implements Comparable<Book> {

    private String title;           // Attribut 1, Datentyp 1 (String)
    private List<Author> authors;   // Attribut 2, Datentyp 2 (Custom: List<Author>) -> 1:N Association
    private int publicationYear;    // Attribut 3, Datentyp 3 (primitiv: int)
    private Genre genre;            // Attribut 4, Datentyp 4 (Custom: Genre) -> Kein Primitiv
    private double rating;          // Attribut 5, Datentyp 5 (primitiv: double)
    private String isbn;            // Bonus Attribut 6

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * // Natural Order
     * Implementierung von Comparable fuer die natuerliche Sortierreihenfolge.
     * Die natuerliche Ordnung fuer Buecher ist alphabetisch nach dem Titel.
     */
    @Override
    public int compareTo(Book other) {
        return this.title.compareTo(other.title);
    }

    @Override
    public String toString() {
        String authorStr = authors == null ? "N/A" : authors.stream().map(Author::getName).collect(Collectors.joining(", "));
        return String.format("Book{title='%s', authors=[%s], year=%d, genre=%s, rating=%.1f, isbn='%s'}",
                title, authorStr, publicationYear, genre, rating, isbn);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
} 