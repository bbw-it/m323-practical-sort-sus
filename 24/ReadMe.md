FunktionalesSortieren
=====================

Dieses Projekt demonstriert die Generierung von Testdaten (Autoren und Bücher) mit **Instancio 5.4.1** und verschiedene Sortieransätze in Java. Es erzeugt 100 Author-Objekte (jeweils mit 0–5 Book-Instanzen), schreibt sie in CSV-Dateien und zeigt acht unterschiedliche Sortierstrategien.

Inhaltsverzeichnis
------------------

1.  Projektstruktur
    
2.  Voraussetzungen
    
3.  Installation & Ausführung
    
4.  Datengenerierung mit Instancio
    
5.  Sorting Deep Dive
    
6.  Weiterführende Quellen
    
7.  Formatierung
    

Projektstruktur
---------------

*   pom.xml: Maven-Konfiguration mit Java 17 und Instancio 5.4.1.
    
*   App.java: Führt verschiedene Sortiermethoden auf einer Liste von Author-Objekten aus und gibt die Ergebnisse aus.
    
*   Author.java: Datenklasse für Autoren.
    
*   Book.java: Datenklasse für Bücher.
    
*   DataGenerator.java: Erzeugt 100 Autoren mit 0–5 Büchern und schreibt CSV-Dateien.
    

Voraussetzungen
---------------

*   Java 17 (JDK 17)
    
*   Maven 3.6 oder höher
    
*   Internetzugang für Abhängigkeiten (nur beim ersten Build)
    

Installation & Ausführung
-------------------------

1.  Projekt öffnen oder klonen.
2. Projekt kompilieren

```bash
mvn clean compile
```

3. CSV-Dateien generieren (Authors und Books)
```bash
mvn exec:java -Dexec.mainClass="ch.bbw.util.DataGenerator"
```

4. Sortierungen in der Konsole ausgeben
```bash
mvn exec:java -Dexec.mainClass="ch.bbw.app.App"
```
    
5.  CSV-Dateien anschauen:
    
    *   testdata/authors.csv
        
    *   testdata/books.csv
        

Datengenerierung mit Instancio
------------------------------

Im Generator DataGenerator.java wird Instancio genutzt, um Felder wie id, birthDate, publishDate und price zu generieren.

Die Felder firstName, lastName und title werden **nicht** von Instancio, sondern von einer eigenen Methode randomLetters(int length) erzeugt. So wird garantiert, dass keine ?????-Platzhalter in der CSV erscheinen.
Da ich Probleme mit INstancio hatte. 

Beispiel für die Textgenerierung:
```java
    private static String randomLetters(int length) {        
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char c = (char) rnd.nextInt('A', 'Z' + 1);
            sb.append(c);
        }
        return sb.toString();
    }
```


Vorgehen:

*   IDs (1–1000 für Autoren, 1–10000 für Bücher)
    
*   Zufällige Geburtstage (zwischen 1950–1995)
    
*   Zufällige Erscheinungsdaten der Bücher (zwischen 2000–2022)
    
*   Preise (5.00–100.00 Euro, 2 Dezimalstellen)
    
*   Vornamen, Nachnamen und Buchtitel werden per eigener Logik generiert.
    

Sorting Deep Dive
-----------------

In der App.java werden acht verschiedene Sortiermethoden gezeigt:

1.  **Natural Order** (Comparable nach lastName)
    
2.  **Externer Comparator** (erst lastName, dann bookCount)
    
3.  **Anonyme Klasse** (nach birthDate)
    
4.  **Lambda** (nach Anzahl Bücher)
    
5.  **Comparator-Attribut in Author** (statisch definiert)
    
6.  **Comparator-Chain** (bookCount aufsteigend, dann lastName)
    
7.  **Reverse Natural Order** (lastName absteigend)
    
8.  **Mehrstufige Sortierung** (lastName, birthDate, bookCount)
    

### Details

*   **Comparable vs Comparator** 
Comparable wird direkt in der Klasse implementiert und erlaubt eine natürliche Ordnung. Comparator ist flexibler und kann verschiedene Sortierstrategien ermöglichen.
    
*   **Anonyme Klassen vs Lambdas** 
Lambdas bieten kompaktere Syntax, anonyme Klassen sind ausführlicher und traditionell.
    
*   **Comparator-Chaining** 
Mehrere Felder können hintereinander sortiert werden, z.B. zuerst nach bookCount, dann nach lastName.
    
*   **Reverse Order**
Eine einfache Umkehr der natürlichen Ordnung mit Collections.reverseOrder().
    

Diese Vielfalt zeigt die Stärke und Flexibilität von Java-Sortierungen.

Weiterführende Quellen
----------------------

*   Instancio Dokumentation: [https://www.instancio.org/user-guide/](https://www.instancio.org/user-guide/)
    
*   Erklärung zu Comparable und Comparator: https://www.geeksforgeeks.org/comparable-vs-comparator-in-java/
    
*   Generierung zufälliger Buchstaben in Java: https://stackoverflow.com/questions/20536566/random-string-generator-java
        

Formatierung
------------

*   Verwendet wurde Markdown-Syntax für klare Gliederung (Überschriften, Listen, Codeblöcke).
    
*   Die Quellenangaben sind transparent angegeben.
    
*   Das Readme wurde mit Hilfe von **ChatGPT** sturkturiert.