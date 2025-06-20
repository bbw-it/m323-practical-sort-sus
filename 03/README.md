# funktionales sortieren - datengenerierung

## testdaten-generierung

die testdaten werden direkt im code generiert. dafür gibt es zwei hauptmethoden:

### 1. kurs-generierung

```java
generateCourses(int count)
```

- generiert die angegebene anzahl an kurse (standard: 10)
- jeder kurs bekommt:
  - eine eindeutige kurs-id (K1, K2, ...)
  - einen namen aus der liste: Mathematik, Physik, Chemie, Biologie, Informatik, Geschichte, Literatur, Kunst, Musik, Sport
  - einen dozenten (Prof. + kursname)
  - eine startzeit (heute + tage)
  - maximale studentenzahl (30)
  - eine leere studentenliste
  - aktiv-status (true)

### 2. studenten-generierung

```java
generateStudents(int count, List<Course> courses)
```

- generiert die angegebene anzahl an studenten (standard: 100)
- jeder student bekommt:
  - einen vornamen aus der liste: Max, Anna, Lukas, Sophie, David, Emma, Tim, Laura, Felix, Sarah
  - einen nachnamen aus der liste: Mueller, Schmidt, Schneider, Fischer, Weber, Meyer, Wagner, Becker, Schulz, Hoffmann
  - ein geburtsdatum (zwischen 1990 und 2005)
  - eine leere kursliste
  - einen notendurchschnitt (zwischen 1.0 und 6.0)
  - ein semester (zwischen 1 und 8)
  - einen aktiv-status (zufällig true/false)
  - 1-5 zufällige kurseinschreibungen

### verknüpfungen

- die verknüpfungen zwischen studenten und kursen werden bidirektional erstellt
- wenn ein student in einen kurs eingeschrieben wird:
  - wird der kurs zur studentenliste des studenten hinzugefügt
  - wird der student zur studentenliste des kurses hinzugefügt

## wie man die daten generiert

1. das projekt klonen
2. maven installieren
3. projekt bauen:
   ```bash
   mvn clean install
   ```
4. programm ausführen:
   ```bash
   mvn exec:java -Dexec.mainClass="ch.berke_poslu.Main"
   ```

## anpassung der datengenerierung

um die datengenerierung anzupassen, können folgende parameter geändert werden:

1. anzahl der kurse:

   ```java
   List<Course> courses = generateCourses(10); // zahl anpassen
   ```

2. anzahl der studenten:

   ```java
   List<Student> students = generateStudents(100, courses); // zahl anpassen
   ```

3. namenslisten in den methoden `getRandomName()` und `getRandomLastName()`

4. kursnamen in der methode `generateCourses()`

5. bereiche für:
   - geburtsdatum
   - notendurchschnitt
   - semester
   - anzahl der kurseinschreibungen

## reproduzierbarkeit

die generierten daten sind bei jedem programmstart neu. für reproduzierbare daten müsste ein fester seed für den zufallsgenerator gesetzt werden.

## java sortier-mechanismen im detail

### 1. natural ordering in java

java verwendet für die natürliche sortierung das `Comparable` interface. wenn eine klasse `Comparable` implementiert, definiert sie ihre natürliche sortierreihenfolge:

```java
public class Student implements Comparable<Student> {
    @Override
    public int compareTo(Student other) {
        // natürliche sortierung: nach nachname, dann vorname
        int lastNameCompare = this.lastName.compareTo(other.lastName);
        if (lastNameCompare != 0) {
            return lastNameCompare;
        }
        return this.firstName.compareTo(other.firstName);
    }
}
```

wichtige punkte:

- `compareTo()` muss konsistent mit `equals()` sein
- die methode gibt zurück:
  - < 0: wenn this < other
  - 0: wenn this == other
  - > 0: wenn this > other
- java verwendet diese methode für `Collections.sort()` und `Arrays.sort()`

### 2. reverse ordering in java

java bietet mehrere wege für die umgekehrte sortierung:

1. über `Collections.reverseOrder()`:

```java
Collections.sort(students, Collections.reverseOrder());
```

2. über `Comparator.reversed()`:

```java
Comparator<Student> reverseComparator =
    Comparator.comparing(Student::getLastName).reversed();
```

intern:

- `reverseOrder()` erstellt einen wrapper-comparator
- dieser kehrt das ergebnis des originalen comparators um
- die ursprüngliche sortierlogik bleibt erhalten, wird nur umgedreht

### 3. java's sortier-algorithmus

java verwendet für das sortieren den timsort algorithmus:

1. **timsort eigenschaften**:

   - hybrid aus merge sort und insertion sort
   - stabil (behält relative reihenfolge gleicher elemente)
   - adaptiv (passt sich an bereits sortierte teile an)
   - o(n log n) im worst case
   - o(n) im best case (wenn daten fast sortiert sind)

2. **ablauf**:
   - teilt die daten in "runs" (sortierte teilstücke)
   - verwendet insertion sort für kleine runs
   - merge sort für das zusammenführen der runs
   - optimiert für real-world daten

### 4. comparator-kette in java

java's comparator interface ermöglicht mehrstufige sortierungen:

```java
Comparator<Student> multiLevelComparator = Comparator
    .<Student, Integer>comparing(student -> student.getEnrolledCourses().size())
    .thenComparing(Student::getGradePointAverage)
    .thenComparing(Student::getLastName)
    .thenComparing(Student::getFirstName);
```

intern:

1. erster comparator wird ausgewertet
2. bei gleichheit wird der nächste comparator geprüft
3. dies wird rekursiv fortgesetzt
4. java optimiert die ausführung durch lazy evaluation

### 5. praktische anwendung

beispiel für die kombination verschiedener sortier-mechanismen:

```java
// natürliche sortierung
Collections.sort(students);

// umgekehrte natürliche sortierung
Collections.sort(students, Collections.reverseOrder());

// mehrstufige sortierung mit comparator
students.sort(Comparator
    .comparing(Student::getLastName)
    .thenComparing(Student::getFirstName)
    .thenComparing(Student::getGradePointAverage));

// umgekehrte mehrstufige sortierung
students.sort(Comparator
    .comparing(Student::getLastName)
    .thenComparing(Student::getFirstName)
    .thenComparing(Student::getGradePointAverage)
    .reversed());
```

### 6. performance-aspekte

1. **speichernutzung**:

   - timsort benötigt zusätzlichen speicherplatz
   - für primitive typen wird ein optimierter quicksort verwendet

2. **stabilität**:

   - timsort ist stabil
   - relative reihenfolge gleicher elemente bleibt erhalten
   - wichtig für mehrstufige sortierungen

3. **parallelisierung**:
   - java 8+ bietet parallel sorting
   - `Arrays.parallelSort()` für große datenmengen
   - nutzt fork/join framework

### 7. best practices

1. **comparable vs comparator**:

   - comparable: natürliche sortierreihenfolge
   - comparator: alternative sortierreihenfolgen
   - beide können kombiniert werden

2. **null-handling**:

   - null-werte sollten explizit behandelt werden
   - `Comparator.nullsFirst()` oder `Comparator.nullsLast()`
   - konsistente null-policy wichtig

3. **performance-optimierung**:
   - für kleine listen: insertion sort
   - für große listen: timsort
   - für primitive typen: quicksort
   - für parallelisierung: parallelsort

## quellen

1. oracle java documentation:

   - [comparable interface](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Comparable.html)
   - [comparator interface](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Comparator.html)
   - [collections.sort()](<https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Collections.html#sort(java.util.List)>)

2. timsort algorithmus:

   - [timsort paper](https://svn.python.org/projects/python/trunk/Objects/listsort.txt)
   - [timsort in java](https://bugs.openjdk.org/browse/JDK-6804124)

3. java sorting best practices:

   - [java sorting tutorial](https://www.baeldung.com/java-sorting)
   - [java 8 comparator](https://www.baeldung.com/java-8-comparator-comparing)

4. performance und parallelisierung:
   - [java parallel sorting](<https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Arrays.html#parallelSort(T[])>)
   - [fork/join framework](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/ForkJoinPool.html)
