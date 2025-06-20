# Funktionales Sortieren – Java-Projekt

## Inhaltsverzeichnis
- [Aufgabenstellung (Zusammenfassung)](#aufgabenstellung-zusammenfassung)
- [Datenklassen](#datenklassen)
- [Datengenerierung](#datengenerierung)
- [Ausführen](#ausführen)
- [Was ist in diesem Projekt alles vorhanden?](#was-ist-in-diesem-projekt-alles-vorhanden)
- [Erweiterte Anforderung: Wie sortiert Java?](#erweiterte-anforderung-wie-sortiert-java)
- [Persönliche Anmerkung](#persönliche-anmerkung)

## Aufgabenstellung (Zusammenfassung)

- **Datenklasse mit mind. 5 Attributen, 4 Datentypen, davon 2 nicht-primitiv**
- **100+ zufällig generierte Datensätze** (Person, Dog)
- **Sortieren mit:**
  - Comparable (Natural Order)
  - Comparator-Klasse
  - Anonyme Klasse
  - Lambda
  - Comparator-Chain
  - Reverse Order
  - Mehrstufige Sortierung
- **Zusatz:** Assoziation zwischen zwei Klassen (Person besitzt Hund)
- **Alle Sortierarten im Code kommentiert**

---

## Datenklassen

### Person
- `String name`
- `int age`
- `double height`
- `LocalDate birthday` (kein primitiver Typ)
- `Address address` (eigene Klasse, kein primitiver Typ)

### Address
- `String street, city, country`
- `int houseNumber`

### Dog
- `String name, breed`
- `LocalDate birthDate`
- `double weight`
- `Person owner` (Assoziation)

---

## Datengenerierung
- Siehe `ch.bbw.util.DataGenerator`
- Fester Seed für Reproduzierbarkeit
- 100 Personen, 100 Hunde (jeder Hund hat einen Owner)

---

## Sortieraufgaben (siehe `Main.java`)

- **Comparable:** `Person` nach Name (`compareTo`)
- **Reverse Order:** Name absteigend
- **Comparator-Klasse:** Nach Alter (`PersonAgeComparator`)
- **Anonyme Klasse:** Nach Größe
- **Lambda:** Nach Geburtstag, Hundename
- **Comparator-Chain:**
  - Person: Stadt, dann Name
  - Person: Land, dann Alter, dann Größe
  - Dog: Owner-Name, dann Geburtsdatum
- **Reverse Order:** Hund nach Gewicht absteigend
- **Alle Sortierarten sind im Code mit Kommentaren wie `// Comparator-Chain`, `// Lambda` usw. markiert.**

---

## Ausführen

1. Projekt bauen (Maven oder IDE)
2. `Main`-Klasse ausführen
3. Die Sortierergebnisse werden in der Konsole ausgegeben (jeweils die ersten 5 Elemente pro Sortierung)

---

## WAs ist in diesem Projekt alles vorhanden?
- Alle geforderten Sortierarten und -techniken sind umgesetzt
- Jede Sortierart ist im Code klar kommentiert
- Zusatzaufgabe (Assoziation Person–Hund) ist umgesetzt und wird sortiert
- Testdaten sind reproduzierbar

---

## Erweiterte Anforderung: Wie sortiert Java?

### Wie funktioniert das Sortieren in Java?
Java bietet mit der `Collections.sort()`-Methode (für Listen) und der `Arrays.sort()`-Methode (für Arrays) eine sehr flexible Möglichkeit, Objekte zu sortieren. Dabei gibt es zwei zentrale Wege:

1. **Natural Order (natürliche Reihenfolge)**
   - Eine Klasse implementiert das Interface `Comparable<T>` und überschreibt die Methode `compareTo(T o)`.
   - Beispiel: In unserer Klasse `Person` wird nach dem Namen sortiert (`compareTo` vergleicht `name`).
   - Wird nun `Collections.sort(persons)` oder `persons.sort(null)` aufgerufen, verwendet Java automatisch diese natürliche Reihenfolge.
   - **Im Code:**
     ```java
     Collections.sort(persons); // oder persons.sort(null);
     // Nutzt Person.compareTo()
     ```
   - **Was passiert intern?**
     Java ruft für jedes zu vergleichende Paar die Methode `compareTo` auf. Das Sortierverfahren ist (seit Java 7) standardmäßig ein optimierter Mergesort (TimSort), der stabil und effizient ist.

2. **Reverse Order (umgekehrte Reihenfolge)**
   - Mit `Collections.reverseOrder()` oder `.reversed()` beim Comparator kann die natürliche Reihenfolge einfach umgedreht werden.
   - Beispiel:
     ```java
     persons.sort(Collections.reverseOrder());
     // oder
     persons.sort(Comparator.comparing(Person::getName).reversed());
     ```
   - Java ruft dann intern für jedes Paar `compareTo` auf, kehrt aber das Ergebnis um.

3. **Sortieren mit Comparator**
   - Mit einem Comparator kann jede beliebige Sortierlogik angegeben werden, z.B. nach Alter, Größe, verschachtelt usw.
   - Comparatoren können als eigene Klasse, anonyme Klasse, Lambda oder Chain geschrieben werden.
   - Beispiel:
     ```java
     persons.sort(Comparator.comparing(Person::getAge));
     // oder komplexer:
     persons.sort(Comparator.comparing(Person::getCity).thenComparing(Person::getAge));
     ```

### Wie löst Java das Sortieren technisch?
- Java verwendet intern für Listen einen stabilen Sortieralgorithmus namens **TimSort** (eine Mischung aus MergeSort und InsertionSort).
- Die zu sortierenden Objekte werden über ihre `compareTo`- oder `Comparator`-Logik verglichen.
- Das Sortieren ist **stabil**: Die Reihenfolge von Elementen mit gleichem Sortierwert bleibt erhalten.

### Zusammengefasst
- **Natural Order**: Über `Comparable` und `compareTo()` – wird automatisch genutzt.
- **Reverse Order**: Über `Collections.reverseOrder()` oder `.reversed()` – dreht die natürliche Reihenfolge um.
- **Comparator**: Beliebige Sortierlogik, auch mehrstufig, mit eigenen Regeln.
- **Technisch**: Java nutzt einen effizienten, stabilen Algorithmus (TimSort) und ruft für jedes Vergleichspaar die jeweilige Vergleichsmethode auf.

---

## Persönliche Anmerkung
Dieses Projekt habe ich im Rahmen des Unterrichts eigenständig umgesetzt. Mir war wichtig, nicht nur die Pflichtaufgaben zu erfüllen, sondern auch die erweiterten Anforderungen so verständlich wie möglich zu erklären. Die Kommentare im Code und die Beispiele im README sollen zeigen, dass ich die Sortiermechanismen in Java wirklich verstanden habe. Falls etwas unklar ist, gerne nachfragen!
