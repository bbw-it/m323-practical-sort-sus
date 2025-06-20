# Wie Java sortiert

## Natural Order

Wenn man in deiner Klasse (z. B. Book) sagt, wie sie sich selbst mit anderen vergleichen soll, ist das natürliche Ordnung.

Beispiel von oben:
```java
public class Book implements Comparable<Book> {
    @Override
    public int compareTo(Book other) {
        return this.title.compareTo(other.title); // Sortiert nach Titel
    }
}
```

Das ist die standardmässige Sortierung. Das folgende würde also die Bücher nach ihrem Titel sortieren:
```java
Collections.sort(books);
```

## Comparator-Chain

Hier werden mehrere Sortierkriterien nacheinander angewandt für den folgenden Anwendungsfalls:
- Man will z.B. Bücher zuerst nach dem Autor sortieren (alphabetisch)
- Dann, wenn ein Autor mehrere Bücher hat, dann sortiere diese noch einmal (alphabetisch)

```java
books.sort(
    Comparator.comparing((Book b) -> b.getAuthor().getName()) // Zuerst Autor
              .thenComparing(Book::getTitle)                  // Dann Titel
);
```

## Wie funktioniert Sortieren "under the hood"

1. Java ruft die `compareTo()` method auf und vergleicht die Elemente miteinander
2. Java verwendet `TimSort` (Mischung aus MergeSort und InsertionSort)

TimSort funktioniert so:
```
Liste: [3, 4, 5, 1, 2, 8, 7, 6]
```
- Zuerst sucht Java nach bereits sortieren Abschnitten (sogenannte **"Runs"**)
```
Runs gefunden: [3,4,5], [1,2], [8], [7,6]
```
- Diese werden dann mit `InsertionSort` sortiert (wenn nötig)
```
Sortieren: [3,4,5], [1,2], [8], [6,7]
```
- Diese Runs werden dann mit `MergeSort` zusammengefügt
```
Mergen: [1,2,3,4,5,6,7,8]
```

> Java verwendet `TimSort` **nicht** bei primitiven Datentypen (das oben war nur zur Veranschaulichung)