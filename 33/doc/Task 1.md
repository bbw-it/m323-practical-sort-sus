# Projekt: Funktionales Sortieren in Java

## Übersicht
Dieses Projekt demonstriert die Anwendung funktionaler Programmierung in Java am Beispiel von Vergleichen und Sortieren mit `Comparator` und `Comparable`. Es erfüllt alle Anforderungen des Bewertungsrasters für das Modul vom Herr Farni.

---

## Bewertungsraster & Umsetzung

### 1. Projekt und Daten (6 Punkte)
- **Datenklasse mit 5 Attributen, 4 versch. Datentypen, 2 kein primitives/Wrapperclass, 100 Datensätze**
  - `Person` hat 5 Attribute: `vorname` (String), `nachname` (String), `alter` (int), `geburtsdatum` (LocalDate), `haustiere` (List<Haustier>)
  - 4 verschiedene Datentypen: String, int, LocalDate, List<Haustier>
  - Mindestens 2 Attribute sind keine primitiven Typen (LocalDate, List<Haustier>)
  - Testdatengenerator erzeugt 100 Datensätze
  - **→ Erfüllt**

### 2. Anwendung Sortieren Teil 1 (8 Punkte)
- **Comparator abgeleitete Klasse, Anonyme Klasse, Lambda Expression, Comparator Chain**
  - `NachnameComparator` (abgeleitete Klasse)
  - Anonyme Klasse für Alter
  - Lambda für Geburtsdatum
  - Comparator Chain für Nachname, Vorname, Alter
  - **→ Erfüllt**

### 3. Anwendung Sortieren Teil 2 (8 Punkte)
- **Verschiedene Sortierungen, alle Attribute 1x verwendet, mehrstufige Sortierung, Natural Order, Reverse Order**
  - Natural Order (`Comparable` in `Person`)
  - Reverse Order (`Comparator.reverseOrder()`)
  - Alle Attribute werden mindestens einmal sortiert (Nachname, Vorname, Alter, Geburtsdatum, Haustiername)
  - Mehrstufige Sortierung (Comparator Chain)
  - **→ Erfüllt**

### 4. Erweiterte Anforderungen (je 4 Punkte)
- **Datenstruktur von zwei Datenklassen, Sortieren mit Bezug auf Assoziation**
  - `Person` und `Haustier` als zwei Datenklassen, Assoziation über `List<Haustier>`
  - Sortierung nach Haustiername (wenn vorhanden)
  - **→ Erfüllt**

- **Tiefes Eintauchen, Natural-Order, Reverse-Order, Sortierung**
  - Natural-Order und Reverse-Order werden gezeigt
  - Verschiedene Sortierarten und -techniken werden demonstriert
  - **→ Erfüllt**

---

## Hinweise zur Abgabe
- Im Code sind an den wichtigsten Stellen kurze Kommentare, die auf die jeweilige Bewertungsanforderung hinweisen (z.B. `// Natural Order`, `// Lambda`, `// Assoziation`, `//Comparator-Chain`).
- Die Testdaten werden mit der Klasse `TestdatenGenerator` generiert. Diese erzeugt 100 `Person`-Objekte mit unterschiedlichen Attributen und ggf. Haustieren.
- Die wichtigsten Sortierarten werden in `mainNew.java` demonstriert.

---

## Testdatengenerierung
Die Testdaten werden automatisch erzeugt:
```java
List<Person> personen = TestdatenGenerator.generiereTestdaten();
```
- Es werden 100 Personen mit unterschiedlichen Namen, Altern, Geburtsdaten und ggf. Haustieren generiert.
- Die Generierung ist im Code dokumentiert und kann beliebig oft wiederholt werden.

---

## Optimierung der Datenklassen (Optional)
- Um Boilerplate-Code (Getter, Setter, Konstruktor, toString, etc.) zu vermeiden, könnten die Datenklassen als `record` (ab Java 16) oder mit Lombok (`@Data`, `@Getter`, `@Setter`) implementiert werden.
- Beispiel für ein Java-Record:
```java
public record Person(String vorname, String nachname, int alter, LocalDate geburtsdatum, List<Haustier> haustiere) {}
```
- Dies ist optional, da die Anforderungen bereits erfüllt sind.

---

## Kontakt
Fragen? Einfach melden bei Yanis Meichtry.
