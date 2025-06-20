package ch.bbw.comparator;

import ch.bbw.model.Author;

import java.util.Comparator;

/*
    Externe Comparator-Klasse für Author.
    Sortiert zuerst nach lastName (alphabetisch) und
    bei Gleichstand nach Anzahl Bücher (aufsteigend).
*/
public class AuthorAttributeComparator implements Comparator<Author> {

    @Override
    public int compare(Author a1, Author a2) {
        int lastNameCompare = a1.getLastName().compareTo(a2.getLastName());
        if (lastNameCompare != 0) {
            return lastNameCompare;
        }
        return Integer.compare(a1.getBooks().size(), a2.getBooks().size());
    }
}
