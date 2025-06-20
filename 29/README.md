# Funktionales Sortieren in Java - Country Example

## Überblick
Dieses Projekt demonstriert verschiedene Sortierungsansätze in Java anhand einer Country-Klasse. Es zeigt die Anwendung von Natural Order, Reverse Order und verschiedenen Comparator-Implementierungen.
Um die Testdaten zu bekommen kann man den curl befel auführen (250 Datensätze):
```bash
curl https://restcountries.com/v3.1/all -o countries.json
```
## Wie funktioniert Java's Sortierung im Detail?

### 1. Natural Order (Natürliche Ordnung) - Comparable Interface

#### Was passiert intern?
Java verwendet das `Comparable<T>` Interface, um eine natürliche Ordnung für Objekte zu definieren. Wenn eine Klasse `Comparable` implementiert, definiert sie ihre "natürliche" Sortierreihenfolge.

```java
public class Country implements Comparable<Country> {
    private String name;
    
    @Override
    public int compareTo(Country other) {
        return this.name.compareTo(other.name);
    }
}
```

#### Wie Java die Natural Order anwendet:

1. **Collections.sort() Aufruf**: 
   ```java
   Collections.sort(countries);
   ```

2. **Interne Verarbeitung**:
   - Java prüft, ob die Objekte `Comparable` implementieren
   - Falls ja, wird die `compareTo()` Methode für Vergleiche verwendet
   - Falls nein, wird eine `ClassCastException` geworfen

3. **compareTo() Rückgabewerte**:
   - **Negativ** (< 0): Das aktuelle Objekt ist "kleiner" als das verglichene
   - **Null** (= 0): Beide Objekte sind "gleich"
   - **Positiv** (> 0): Das aktuelle Objekt ist "größer" als das verglichene

4. **String.compareTo() im Detail**:
   ```java
   // Intern verwendet String.compareTo() lexikographische Ordnung
   "Apple".compareTo("Banana") // Negativ - Apple kommt vor Banana
   "Zebra".compareTo("Apple")  // Positiv - Zebra kommt nach Apple
   ```

### 2. Reverse Order (Umgekehrte Ordnung)

#### Wie Java Reverse Order implementiert:

```java
countries.sort(Collections.reverseOrder());
```

#### Interne Funktionsweise:

1. **Collections.reverseOrder()** erstellt einen speziellen Comparator:
   ```java
   // Vereinfachte interne Implementierung
   public static <T> Comparator<T> reverseOrder() {
       return new ReverseComparator<T>();
   }
   
   private static class ReverseComparator<T> implements Comparator<T> {
       public int compare(T a, T b) {
           return ((Comparable<T>)b).compareTo(a); // Umgekehrte Reihenfolge!
       }
   }
   ```

2. **Umkehrung der Logik**: 
   - Statt `a.compareTo(b)` wird `b.compareTo(a)` aufgerufen
   - Dadurch werden die Rückgabewerte umgekehrt
   - Positive Werte werden negativ und umgekehrt

### 3. Java's Sortieralgorithmus - TimSort

#### Welchen Algorithmus verwendet Java?

Java verwendet seit Version 7 den **TimSort-Algorithmus** für `Collections.sort()` und `Arrays.sort()` (für Objekte).

#### TimSort Eigenschaften:

1. **Hybrid-Algorithmus**: Kombination aus Merge Sort und Insertion Sort
2. **Stabil**: Gleiche Elemente behalten ihre relative Reihenfolge
3. **Adaptiv**: Nutzt bereits sortierte Bereiche (Runs) optimal aus
4. **Zeitkomplexität**: 
   - Best Case: O(n) - bei bereits sortierten Daten
   - Average Case: O(n log n)
   - Worst Case: O(n log n)

#### Wie TimSort funktioniert:

1. **Run-Erkennung**: Sucht nach bereits sortierten Bereichen
2. **Minimum Run Size**: Berechnet optimale Mindestlänge für Runs
3. **Insertion Sort**: Für kleine Bereiche (< 32 Elemente)
4. **Merge**: Verschmilzt Runs intelligent miteinander
5. **Galloping Mode**: Optimierung für stark ungleiche Runs

```java
// Vereinfachter TimSort-Ablauf
1. Identifiziere natürliche Runs in den Daten
2. Erweitere kurze Runs mit Insertion Sort
3. Merge Runs mit optimierter Merge-Strategie
4. Verwende Galloping für ungleiche Run-Größen
```

### 4. Comparator Interface - Flexible Sortierung

#### Custom Comparator Implementierung:

```java
public class SortByPopulation implements Comparator<Country> {
    @Override
    public int compare(Country c1, Country c2) {
        return Long.compare(c1.getPopulation(), c2.getPopulation());
    }
}
```

#### Verschiedene Comparator-Ansätze:

1. **Separate Klasse**:
   ```java
   countries.sort(new SortByPopulation());
   ```

2. **Anonyme Klasse**:
   ```java
   countries.sort(new Comparator<Country>() {
       @Override
       public int compare(Country c1, Country c2) {
           return Double.compare(c1.getArea(), c2.getArea());
       }
   });
   ```

3. **Lambda Expression**:
   ```java
   countries.sort((c1, c2) -> c1.getRegion().compareTo(c2.getRegion()));
   ```

4. **Method Reference**:
   ```java
   countries.sort(Comparator.comparing(Country::getName));
   ```

5. **Comparator Chaining**:
   ```java
   countries.sort(Comparator.comparing(Country::getRegion)
                           .thenComparing(Country::getPopulation)
                           .thenComparing(Country::getName));
   ```

### 5. Interne Optimierungen von Java

#### Wie Java Sortierung optimiert:

1. **Primitive vs. Objekte**:
   - **Primitive** (int[], double[]): Dual-Pivot Quicksort
   - **Objekte** (Object[]): TimSort

2. **Speicher-Optimierungen**:
   - In-Place Sortierung wo möglich
   - Minimaler zusätzlicher Speicherbedarf
   - Wiederverwendung von temporären Arrays

3. **JVM-Optimierungen**:
   - HotSpot JIT-Compiler optimiert häufig verwendete Sortierungen
   - Inlining von Comparator-Methoden
   - Branch Prediction für bessere Performance

#### Performance-Vergleich:

| Sortierungstyp | Algorithmus | Stabilität | Best Case | Average Case | Worst Case |
|----------------|-------------|------------|-----------|--------------|------------|
| Arrays.sort(int[]) | Dual-Pivot Quicksort | Nein | O(n log n) | O(n log n) | O(n²) |
| Collections.sort() | TimSort | Ja | O(n) | O(n log n) | O(n log n) |
| Stream.sorted() | TimSort | Ja | O(n) | O(n log n) | O(n log n) |

### 6. Praktische Beispiele aus dem Code

#### Natural Order Anwendung:
```java
// Country implementiert Comparable<Country>
private static void naturalOrder() {
    ArrayList<Country> countries = getDataOutOfJSON();
    Collections.sort(countries); // Verwendet compareTo() Methode
    System.out.println(countries);
}
```

#### Reverse Order Anwendung:
```java
private static void reverseOrder() {
    ArrayList<Country> countries = getDataOutOfJSON();
    countries.sort(Collections.reverseOrder()); // Kehrt Natural Order um
    System.out.println(countries);
}
```

#### Komplexe Sortierung mit Chaining:
```java
private static void comparatorChain() {
    ArrayList<Country> countries = getDataOutOfJSON();
    countries.sort(Comparator.comparing(Country::getRegion)      // 1. Nach Region
                            .thenComparing(Country::getPopulation) // 2. Nach Bevölkerung
                            .thenComparing(Country::getName));     // 3. Nach Name
    System.out.println(countries);
}
```

### 7. Best Practices und Empfehlungen

#### Wann welche Sortierung verwenden?

1. **Comparable (Natural Order)**:
   - Für die "offensichtliche" Sortierung einer Klasse
   - Wenn es nur eine logische Sortierreihenfolge gibt
   - Beispiel: Personen nach Namen, Zahlen nach Wert

2. **Comparator**:
   - Für alternative Sortierungen
   - Wenn mehrere Sortierkriterien möglich sind
   - Für Klassen, die Sie nicht ändern können

3. **Lambda Expressions**:
   - Für einfache, einmalige Sortierungen
   - Bessere Lesbarkeit bei simplen Vergleichen

4. **Method References**:
   - Wenn bereits passende Getter-Methoden existieren
   - Für maximale Lesbarkeit

#### Performance-Tipps:

1. **Verwenden Sie primitive Arrays** für bessere Performance bei Zahlen
2. **Nutzen Sie Comparator.comparing()** statt manueller Implementierung
3. **Vermeiden Sie Boxing/Unboxing** in Comparatoren
4. **Sortieren Sie nur einmal** und speichern Sie das Ergebnis zwischen

## Fazit

Java's Sortierungsmechanismen sind hochoptimiert und bieten verschiedene Abstraktionsebenen:

- **Comparable** für natürliche Ordnung
- **Comparator** für flexible, alternative Sortierungen  
- **TimSort** als stabiler, adaptiver Algorithmus
- **Lambda Expressions** für moderne, lesbare Syntax
- **Method References** für maximale Eleganz

Die Kombination aus leistungsstarken Algorithmen und flexiblen APIs macht Java's Sortierung sowohl performant als auch entwicklerfreundlich.


> ⚠️ AI verzeichnis
> - ChatGpt als step by step guid bei der Implementierung genutzt. 
> - Dokumentation Generiert mit Cursor AI (Cloud-4-Sonnet) und überarbeited von Nino Siegrist