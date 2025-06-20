package ch.bbw.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Author implements Comparable<Author> {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private List<Book> books;

    public Author(int id, String firstName, String lastName, LocalDate birthDate, List<Book> books) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.books = (books != null) ? books : new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = (books != null) ? books : new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", books=" + books +
                '}';
    }

    @Override
    public int compareTo(Author other) {
        return this.lastName.compareTo(other.lastName);
    }

    // Statische Comparatoren (z.B. für Sortierbeispiele)
    public static final Comparator<Author> BY_NUM_BOOKS =
            Comparator.comparingInt(a -> a.getBooks().size());

    public static final Comparator<Author> BY_NUM_BOOKS_THEN_LASTNAME =
            Comparator.comparingInt((Author a) -> a.getBooks().size())
                      .thenComparing(Author::getLastName);
}
