# Galactic Zoo Sorter: Was ist das? (Einfache Erklärung)

## 1. Worum geht's hier?

Stell dir vor, du hast einen riesigen Zoo im Weltall mit vielen komischen Tieren von anderen Planeten. Das "Galactic Zoo Sorter"-Projekt ist ein Java-Programm, das dir zeigt, wie du diese Tiere nach verschiedenen Eigenschaften sortieren kannst – zum Beispiel nach Name, Gewicht oder Gefahr. Wir schauen uns an, wie das in Java mit Dingen wie `Comparable` und `Comparator` funktioniert.

## 2. Die Bausteine: Unsere Zoo-Daten

Um Tiere sortieren zu können, brauchen wir erstmal Daten. Diese sind in verschiedenen Java-Klassen (wie Bauplänen) festgelegt:

### `AlienAnimal` (Das Alien-Tier)
Das ist die Hauptsache – unser Alien-Tier.
* **Eigenschaften:**
    * `speciesName`: Wie heißt die Tierart? (Text)
    * `originPlanet`: Von welchem Planet kommt es? (Ein `Planet`-Objekt)
    * `weightInTons`: Wie schwer ist es in Tonnen? (Zahl)
    * `dateDiscovered`: Wann wurde es entdeckt? (Datum)
    * `dangerLevel`: Wie gefährlich ist es? (Ein `DangerLevel`-Wert)
    * `brainProfile`: Wie schlau ist es? (Ein `IntelligenceProfile`-Objekt)
* **Standard-Sortierung (`Comparable`):**
    * `AlienAnimal` kann sich selbst mit anderen Tieren vergleichen. Standardmäßig sortiert es sich nach dem `speciesName` (alphabetisch A-Z).

### `Planet` (Der Planet)
Beschreibt einen Planeten.
* **Eigenschaften:**
    * `name`: Name des Planeten (Text).
    * `distanceFromEarth`: Wie weit weg von der Erde (in Lichtjahren, eine Zahl).
    * `atmosphereType`: Was für Luft gibt es dort? (Ein `AtmosphereType`-Wert, z.B. GIFTIG, ATEMBAR).

### `IntelligenceProfile` (Das Gehirn-Profil)
Beschreibt, wie clever ein Tier ist.
* **Eigenschaften:**
    * `iqLevel`: Der IQ-Wert (Zahl zwischen 0-300).
    * `telepathic`: Kann es Gedanken lesen? (Ja/Nein).
    * `knownLanguages`: Welche Sprachen kennt es? (Eine Liste von Texten).

### `DangerLevel` (Gefahrenstufe - eine Aufzählung)
Legt fest, wie gefährlich ein Tier ist: `HARMLESS` (harmlos), `CAUTION` (Vorsicht), `DANGEROUS` (gefährlich), `EXTREME_DANGER` (extrem gefährlich).

### `AtmosphereType` (Atmosphären-Typ - eine Aufzählung)
Legt fest, welche Art von Atmosphäre ein Planet hat: `TOXIC` (giftig), `BREATHABLE` (atembar), `UNKNOWN` (unbekannt), `NONE` (keine).

### `Zookeeper` (Der Zoowärter - für Fortgeschrittene)
Ein Zoowärter, der sich um Tiere kümmert.
* **Eigenschaften:**
    * `name`: Name des Wärters (Text).
    * `experienceYears`: Wie viele Jahre Erfahrung hat er? (Zahl).
    * `assignedCreatures`: Für welche Tiere ist er zuständig? (Eine Liste von `AlienAnimal`-Objekten).

## 3. Test-Tiere erschaffen (`DataGenerator`)

Die Klasse `DataGenerator` ist ein Helferlein. Sie denkt sich ganz viele zufällige Alien-Tiere und Zoowärter aus, damit wir etwas zum Sortieren haben.

## 4. Wie sortiert man in Java? Die Werkzeuge

Java gibt uns zwei Hauptwerkzeuge, um Dinge zu sortieren: `Comparable` und `Comparator`.

### 4.1. `Comparable`: Die natürliche Ordnung

* **Was ist das?** `Comparable` ist wie eine eingebaute Regel in einer Klasse (z.B. `AlienAnimal`), die sagt: "So sortiere ich mich standardmäßig."
* **Wie geht das?** Man schreibt eine Methode namens `compareTo`. Diese vergleicht das aktuelle Objekt (`this`) mit einem anderen.
    * Ergebnis negativ: `this` kommt *vor* dem anderen.
    * Ergebnis null: `this` ist *gleich* dem anderen.
    * Ergebnis positiv: `this` kommt *nach* dem anderen.
* **Beispiel `AlienAnimal`:**
    ```java
    // AlienAnimal.java
    public class AlienAnimal implements Comparable<AlienAnimal> {
        // ...
        @Override
        public int compareTo(AlienAnimal other) {
            // Vergleiche die Namen der Tierarten
            return this.speciesName.compareTo(other.speciesName);
        }
    }
    ```
* **Benutzung:** Wenn man `Collections.sort(listeTiere)` aufruft, nutzt Java automatisch diese `compareTo`-Methode.

### 4.2. `Comparator`: Sortieren, wie du es willst

* **Was ist das?** Ein `Comparator` ist ein externer Sortier-Experte. Du benutzt ihn, wenn:
    * Die Klasse keine eigene `Comparable`-Regel hat.
    * Du nach verschiedenen Regeln sortieren willst (mal nach Gewicht, mal nach IQ).
    * Du die Standard-Sortierung nicht ändern willst.
* **Wie geht das?** Man schreibt eine Methode namens `compare`, die zwei Objekte (z.B. Tier1, Tier2) nimmt und sagt, welches zuerst kommt.
* **Verschiedene Arten, einen `Comparator` zu schreiben:**

    * **Eigene Klasse dafür:**
        ```java
        // Eigene Klasse, die Tiere nach Gewicht sortiert
        class TierGewichtVergleicher implements Comparator<AlienAnimal> {
            @Override
            public int compare(AlienAnimal tier1, AlienAnimal tier2) {
                return Double.compare(tier1.getWeightInTons(), tier2.getWeightInTons());
            }
        }
        // Benutzen: Collections.sort(tiere, new TierGewichtVergleicher());
        ```

    * **Anonyme Klasse (kurz & bündig für einmalige Nutzung):**
        ```java
        Collections.sort(tiere, new Comparator<AlienAnimal>() {
            public int compare(AlienAnimal tier1, AlienAnimal tier2) {
                // Sortiere nach IQ
                return Integer.compare(tier1.getBrainProfile().getIqLevel(), tier2.getBrainProfile().getIqLevel());
            }
        });
        ```

    * **Lambda-Ausdruck (noch kürzer, ab Java 8):**
        ```java
        // Sortiere nach Gewicht
        tiere.sort((tier1, tier2) -> Double.compare(tier1.getWeightInTons(), tier2.getWeightInTons()));
        ```

    * **`Comparator`-Hilfsmethoden (super praktisch, ab Java 8):**
        * `Comparator.comparing(tier -> tier.getEigenschaft())`: Sortiert nach einer Eigenschaft.
            ```java
            // Sortiere nach Gewicht
            Comparator<AlienAnimal> nachGewicht = Comparator.comparing(tier -> tier.getWeightInTons());
            ```
        * **Mehrere Regeln kombinieren (`thenComparing`):** Erst nach Regel A, dann (wenn A gleich ist) nach Regel B.
            ```java
            // Erst nach Planetenname, dann nach Gefährlichkeit (die gefährlichsten zuerst)
            Comparator<AlienAnimal> nachPlanetDannGefahr = Comparator
                .comparing(tier -> tier.getOriginPlanet().getName()) // Regel 1: Planetenname
                .thenComparing(tier -> tier.getDangerLevel(), Comparator.reverseOrder()); // Regel 2: Gefahr (umgekehrt)
            ```
    * **`Comparator` direkt in der Klasse speichern:** Man kann oft genutzte `Comparator`en direkt in der `AlienAnimal`-Klasse als Helfer bereitstellen.

### 4.3. "Natural Order": Die Standard-Sortierung nutzen

* Wenn Java eine Liste sortieren soll (`Collections.sort(list)`) und die Dinge in der Liste `Comparable` sind (also eine `compareTo`-Methode haben), dann benutzt Java diese Methode ganz von alleine. Java fragt quasi jedes Ding: "Wie vergleichst du dich mit dem anderen Ding?"

### 4.4. "Reverse Order": Andersherum sortieren

Willst du eine Liste umgekehrt sortieren (z.B. Z-A statt A-Z)? Java hilft dir:

1.  **`Collections.reverseOrder()`:**
    * Gibt dir einen speziellen `Comparator`, der die Standard-Sortierung (`Comparable`) einfach umdreht.
    * Beispiel: `tiere.sort(Collections.reverseOrder());` (sortiert Tiere nach Name von Z bis A).

2.  **`meinComparator.reversed()`:**
    * Hast du schon einen `Comparator` (z.B. einen, der nach Gewicht sortiert)? Mit `.reversed()` kannst du ihn umdrehen.
    * Beispiel: `nachGewicht.reversed()` (sortiert Tiere vom schwersten zum leichtesten).

## 5. Wie macht Java das eigentlich? Ein Blick hinter die Kulissen

Wenn du Java sagst "Sortier das!", benutzt es meistens einen schlauen Algorithmus namens **Timsort**.
* **Was ist Timsort?** Eine Mischung aus zwei anderen Sortierideen (Merge Sort und Insertion Sort).
* **Was kann Timsort gut?**
    * **Schnell:** Meistens O(n log n) – das ist ein guter Wert für Sortieralgorithmen.
    * **Stabil:** Wenn zwei Tiere nach einer Regel gleich sind (z.B. gleiches Gewicht), behalten sie ihre ursprüngliche Reihenfolge zueinander.
    * **Clever:** Wenn Teile der Liste schon sortiert sind, nutzt Timsort das aus und ist noch schneller.
* **Kurz gesagt:** Timsort findet schon sortierte Abschnitte ("Runs"), macht kurze Abschnitte mit einer einfachen Methode länger und fügt dann die sortierten Abschnitte clever zusammen, bis alles sortiert ist. Du musst dich darum aber nicht kümmern, Java macht das für dich!

## 6. Zusammenfassung: Sortieren in Java – gar nicht so schwer!

Java macht das Sortieren einfach, weil es:
1.  **`Comparable`** hat: Damit Dinge wissen, wie sie sich standardmäßig sortieren.
2.  **`Comparator`** hat: Damit du eigene Sortierregeln festlegen kannst – ganz flexibel mit Klassen, Lambda-Ausdrücken oder den praktischen Helfermethoden.
3.  **Timsort** benutzt: Einen schnellen und cleveren Sortieralgorithmus im Hintergrund.

So kannst du deine Alien-Tiere (oder was auch immer) nach allen möglichen Regeln ordnen!
<br>  
<br>  


> Die Dokumentation, der Datengenerator und die main-Methode der Hauptdatei wurden mit Hilfe von Gemini 2.5 Pro erstellt und anschließend von mir überarbeitet.

— Hinweis zur Entwicklung