## Datengenerierung

Die Testdaten wurden nicht über eine API, sondern manuell in der Datei `movies.json` erstellt.  
Diese Datei liegt im Projekt unter `src/main/resources/movies.json`.  
Sie enthält 100 realistische Filme mit Titel, Jahr, Bewertung, Dauer, Genre und Regisseur.  
Damit ist die Aufgabe zur Datengenerierung und Reproduzierbarkeit erfüllt.

---

## Vergleich: Sortiermethoden in Java

### `Collections.sort()`

Die Methode `Collections.sort(list, comparator)` sortiert eine Liste mit einem übergebenen Comparator.  
Dabei wird die Original-Liste direkt verändert. Diese Methode stammt aus der Klasse `java.util.Collections`.  
Sie ist nützlich, wenn man eine **anonyme Klasse verwendet** oder älteren Java-Code pflegt.

---

### `List.sort()`

`List.sort(comparator)` ist eine modernere Variante von `Collections.sort()`.  
Sie funktioniert direkt auf der Liste selbst und benötigt keinen statischen Methodenaufruf.  
Der Code ist **kürzer und lesbarer** und wird daher bevorzugt verwendet.

---

### `stream().sorted()`

Mit `stream().sorted()` wird ein **Stream von Objekten sortiert**, ohne die ursprüngliche Liste zu verändern.  
Man kann das Ergebnis z. B. mit `limit(5).forEach(...)` weiterverarbeiten.  
Ideal für **Filter, Top-N-Ergebnisse** und methodenverketteten Code.

---

## Reflexion

Das Projekt war hilfreich, um den Unterschied zwischen `Comparable` und `Comparator` besser zu verstehen.  
Besonders die Kombination verschiedener Sortieransätze (anonyme Klasse, Lambda, Comparator-Chain) war lehrreich.  
Durch das Sortieren nach verschachtelten Objekten wie `director.name` konnte ich außerdem mit komplexeren Objektstrukturen arbeiten.  
Ich habe gelernt, wie man realistische Daten mit JSON importiert und gezielt mit Java-Streams verarbeitet.  
Am Anfang war es schwierig, aber durch Testen und schrittweises Vorgehen wurde alles verständlicher.
