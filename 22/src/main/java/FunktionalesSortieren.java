import Model.*;
import Comparator.*;
import Generator.DataGenerator;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.math.BigDecimal;

/**
 * Modul 323 - Funktionales Sortieren von strukturierten Daten
 *
 * Diese Klasse demonstriert alle Aspekte des funktionalen Sortierens:
 * - Verschiedene Comparator-Implementierungen
 * - Natural Order und Reverse Order
 * - Mehrstufige Sortierungen
 * - Sortierung mit Assoziationen
 * - Tiefes Eintauchen in Java's Sortierimplementierung
 */
public class FunktionalesSortieren {

    public static void main(String[] args) {
        System.out.println("=== MODUL 323: FUNKTIONALES SORTIEREN ===\n");

        // Datengenerierung mit reproduzierbarem Seed
        // Anforderung: 100 zufällig generierte Datensätze
        DataGenerator generator = new DataGenerator(12345L);
        List<Person> personen = generator.generatePersonen(100);

        System.out.println("Generierte " + personen.size() + " Personen mit Fahrzeugen\n");

        // Alle Sortierungsaspekte demonstrieren
        demonstriereComparatorVarianten(personen);
        demonstriereAttributSortierungen(personen);
        demonstriereMehrstufigeSortierung(personen);
        demonstriereNaturalUndReverseOrder(personen);
        demonstriereAssozationsSortierung(personen);

        // Erweiterte Anforderung: Tiefes Eintauchen
        demonstriereJavaSortierungsInternals();
    }

    /**
     * Demonstriert verschiedene Comparator-Implementierungen
     * Erfüllt Anforderungen:
     * - Comparator abgeleitete Klasse
     * - Anonyme Klasse
     * - Lambda Expression
     * - Comparator Chain
     */
    private static void demonstriereComparatorVarianten(List<Person> personen) {
        System.out.println("=== 1. COMPARATOR-VARIANTEN ===");

        // 1.1 Comparator abgeleitete Klasse
        List<Person> sortedByAge = new ArrayList<>(personen);
        sortedByAge.sort(new AlterComparator()); // Comparator abgeleitete Klasse
        System.out.println("Sortiert nach Alter (abgeleitete Klasse): " +
                sortedByAge.subList(0, 3).stream()
                        .map(p -> p.getName() + "(" + p.getAlter() + ")")
                        .collect(Collectors.joining(", ")));

        // 1.2 Anonyme Klasse
        List<Person> sortedByEinkommen = new ArrayList<>(personen);
        sortedByEinkommen.sort(new Comparator<Person>() { // Anonyme Klasse
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getMonatsEinkommen().compareTo(p2.getMonatsEinkommen());
            }
        });
        System.out.println("Sortiert nach Einkommen (anonyme Klasse): " +
                sortedByEinkommen.subList(0, 3).stream()
                        .map(p -> p.getName() + "(" + p.getMonatsEinkommen() + ")")
                        .collect(Collectors.joining(", ")));

        // 1.3 Lambda Expression
        List<Person> sortedByName = new ArrayList<>(personen);
        sortedByName.sort((p1, p2) -> p1.getName().compareTo(p2.getName())); // Lambda Expression
        System.out.println("Sortiert nach Name (lambda): " +
                sortedByName.subList(0, 3).stream()
                        .map(Person::getName)
                        .collect(Collectors.joining(", ")));

        // 1.4 Comparator Chain
        List<Person> sortedChain = new ArrayList<>(personen);
        sortedChain.sort(Comparator // Comparator Chain
                .comparing(Person::getBerufsStatus)
                .thenComparing(Person::getAlter)
                .thenComparing(Person::getName));
        System.out.println("Sortiert nach Berufsstatus->Alter->Name (chain): " +
                sortedChain.subList(0, 3).stream()
                        .map(p -> p.getBerufsStatus() + "-" + p.getName() + "(" + p.getAlter() + ")")
                        .collect(Collectors.joining(", ")));

        System.out.println();
    }

    /**
     * Demonstriert Sortierung nach allen Attributen mindestens einmal
     * Erfüllt Anforderung: Verschiedene Sortierungen, so dass alle Attribute 1x verwendet wurden
     */
    private static void demonstriereAttributSortierungen(List<Person> personen) {
        System.out.println("=== 2. SORTIERUNG NACH ALLEN ATTRIBUTEN ===");

        // Attribut 1: Name (String)
        List<Person> byName = personen.stream()
                .sorted(Comparator.comparing(Person::getName))
                .limit(3).collect(Collectors.toList());
        System.out.println("Nach Name: " + byName.stream().map(Person::getName).collect(Collectors.joining(", ")));

        // Attribut 2: Geburtsdatum (LocalDate)
        List<Person> byBirthdate = personen.stream()
                .sorted(Comparator.comparing(Person::getGeburtsdatum))
                .limit(3).collect(Collectors.toList());
        System.out.println("Nach Geburtsdatum: " + byBirthdate.stream()
                .map(p -> p.getName() + "(" + p.getGeburtsdatum() + ")")
                .collect(Collectors.joining(", ")));

        // Attribut 3: MonatsEinkommen (BigDecimal)
        List<Person> byIncome = personen.stream()
                .sorted(Comparator.comparing(Person::getMonatsEinkommen))
                .limit(3).collect(Collectors.toList());
        System.out.println("Nach Einkommen: " + byIncome.stream()
                .map(p -> p.getName() + "(" + p.getMonatsEinkommen() + ")")
                .collect(Collectors.joining(", ")));

        // Attribut 4: BerufsStatus (Enum)
        List<Person> byStatus = personen.stream()
                .sorted(Comparator.comparing(Person::getBerufsStatus))
                .limit(3).collect(Collectors.toList());
        System.out.println("Nach Berufsstatus: " + byStatus.stream()
                .map(p -> p.getName() + "(" + p.getBerufsStatus() + ")")
                .collect(Collectors.joining(", ")));

        // Attribut 5: Fahrzeuge (List<Fahrzeug>)
        List<Person> byVehicleCount = personen.stream()
                .sorted(Comparator.comparing(p -> p.getFahrzeuge().size()))
                .limit(3).collect(Collectors.toList());
        System.out.println("Nach Anzahl Fahrzeuge: " + byVehicleCount.stream()
                .map(p -> p.getName() + "(" + p.getFahrzeuge().size() + " Fahrzeuge)")
                .collect(Collectors.joining(", ")));

        System.out.println();
    }

    /**
     * Demonstriert mehrstufige Sortierung
     * Erfüllt Anforderung: Mehrstufige Sortierungen
     */
    private static void demonstriereMehrstufigeSortierung(List<Person> personen) {
        System.out.println("=== 3. MEHRSTUFIGE SORTIERUNG ===");

        // Methode 1: Mit eigener Comparator-Klasse
        List<Person> mehrstufig1 = new ArrayList<>(personen);
        mehrstufig1.sort(new MehrstufenComparator());
        System.out.println("Mehrstufige Sortierung mit Comparator-Klasse (Status->Einkommen desc->Name):");
        mehrstufig1.stream().limit(5).forEach(p -> System.out.println("  " + p.getBerufsStatus() +
                " | " + p.getMonatsEinkommen() + " | " + p.getName()));

        // Methode 2: Mit Comparator Chain API
        List<Person> mehrstufig2 = personen.stream()
                .sorted(Comparator
                        .comparing(Person::getBerufsStatus)
                        .thenComparing(Person::getMonatsEinkommen, Comparator.reverseOrder())
                        .thenComparing(Person::getName))
                .limit(5)
                .collect(Collectors.toList());

        System.out.println("\nMehrstufige Sortierung mit Comparator Chain (Status->Einkommen desc->Name):");
        mehrstufig2.forEach(p -> System.out.println("  " + p.getBerufsStatus() +
                " | " + p.getMonatsEinkommen() + " | " + p.getName()));

        System.out.println();
    }

    /**
     * Demonstriert Natural Order und Reverse Order
     * Erfüllt Anforderungen: 
     * - Natural Order 
     * - Reverse Order
     */
    private static void demonstriereNaturalUndReverseOrder(List<Person> personen) {
        System.out.println("=== 4. NATURAL ORDER UND REVERSE ORDER ===");

        // Natural Order (Person implementiert Comparable)
        List<Person> naturalOrder = new ArrayList<>(personen);
        Collections.sort(naturalOrder); // Natural Order
        System.out.println("Natural Order (nach Name): " +
                naturalOrder.subList(0, 5).stream()
                        .map(Person::getName)
                        .collect(Collectors.joining(", ")));

        // Reverse Order mit Collections
        List<Person> reverseOrder1 = new ArrayList<>(personen);
        reverseOrder1.sort(Comparator.reverseOrder()); // Reverse Order
        System.out.println("Reverse Order (Name rückwärts): " +
                reverseOrder1.subList(0, 5).stream()
                        .map(Person::getName)
                        .collect(Collectors.joining(", ")));

        // Reverse Order mit Stream
        List<Person> reverseOrder2 = personen.stream()
                .sorted(Collections.reverseOrder())
                .limit(5)
                .collect(Collectors.toList());
        System.out.println("Reverse Order mit Stream: " +
                reverseOrder2.stream()
                        .map(Person::getName)
                        .collect(Collectors.joining(", ")));

        System.out.println();
    }

    /**
     * Demonstriert Sortierung mit Assoziationen
     * Erfüllt erweiterte Anforderung: Sortieren mit Bezug auf Assoziation
     */
    private static void demonstriereAssozationsSortierung(List<Person> personen) {
        System.out.println("=== 5. SORTIERUNG MIT ASSOZIATIONEN ===");

        // Nach teuerstem Fahrzeug sortieren (mit speziellem Assoziations-Comparator)
        List<Person> byExpensiveCar = personen.stream()
                .filter(p -> !p.getFahrzeuge().isEmpty())
                .sorted(FahrzeugAssoziationsComparator.byTeuerstenFahrzeug().reversed())
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("Nach teuerstem Fahrzeug (mit Assoziations-Comparator):");
        byExpensiveCar.forEach(p -> {
            BigDecimal maxPrice = p.getFahrzeuge().stream()
                    .map(Fahrzeug::getPreis)
                    .max(BigDecimal::compareTo)
                    .orElse(BigDecimal.ZERO);
            System.out.println("  " + p.getName() + " - teuerstes Fahrzeug: " + maxPrice);
        });

        // Nach durchschnittlichem Fahrzeugalter sortieren (mit Assoziations-Comparator)
        List<Person> byAvgCarAge = personen.stream()
                .filter(p -> !p.getFahrzeuge().isEmpty())
                .sorted(FahrzeugAssoziationsComparator.byDurchschnittlichesFahrzeugalter())
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("\nNach durchschnittlichem Fahrzeugalter (mit Assoziations-Comparator):");
        byAvgCarAge.forEach(p -> {
            double avgAge = p.getFahrzeuge().stream()
                    .mapToInt(f -> LocalDate.now().getYear() - f.getBaujahr())
                    .average()
                    .orElse(0.0);
            System.out.println("  " + p.getName() + " - Ø Fahrzeugalter: " + String.format("%.1f", avgAge) + " Jahre");
        });

        // Alternative: Direkter Inline-Vergleich für Assoziation
        List<Person> byNewestCar = personen.stream()
                .filter(p -> !p.getFahrzeuge().isEmpty())
                .sorted(Comparator.comparing(p -> p.getFahrzeuge().stream()
                        .mapToInt(Fahrzeug::getBaujahr)
                        .max()
                        .orElse(0), Comparator.reverseOrder()))
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("\nNach neuestem Fahrzeug (Inline-Comparator):");
        byNewestCar.forEach(p -> {
            int newestYear = p.getFahrzeuge().stream()
                    .mapToInt(Fahrzeug::getBaujahr)
                    .max()
                    .orElse(0);
            System.out.println("  " + p.getName() + " - neuestes Fahrzeug von: " + newestYear);
        });

        System.out.println();
    }

    /**
     * Erweiterte Anforderung: Tiefes Eintauchen in Java's Sortierung
     * Erfüllt erweiterte Anforderung II: Tiefes Eintauchen
     */
    private static void demonstriereJavaSortierungsInternals() {
        System.out.println("=== 6. TIEFES EINTAUCHEN: JAVA SORTIERUNGS-INTERNALS ===");

        System.out.println("A) NATURAL ORDER IMPLEMENTIERUNG:");
        System.out.println("   - Person implementiert Comparable<Person> Interface");
        System.out.println("   - compareTo() definiert die natürliche Ordnung");
        System.out.println("   - Die Implementierung in Person erfolgt über Namensvergleich");
        System.out.println("   - Collections.sort() und Arrays.sort() verwenden diese Implementierung");
        System.out.println("   - Natural Order ist die Standardreihenfolge einer Klasse");
        System.out.println("   - Intern ruft Java die compareTo-Methode auf den Objekten auf");

        System.out.println("\nB) REVERSE ORDER IMPLEMENTIERUNG:");
        System.out.println("   - Comparator.reverseOrder() erstellt eine Instanz von ReverseComparator");
        System.out.println("   - Der ReverseComparator ist intern als private Klasse in Collections implementiert");
        System.out.println("   - Er kehrt das Ergebnis des Vergleichs um: return -compare(a,b)");
        System.out.println("   - Dies ist ein Wrapper um bestehenden Comparator");
        System.out.println("   - Implementierung in Java: return -(((Comparable<T>)c1).compareTo(c2))");

        System.out.println("\nC) JAVA'S SORTIER-ALGORITHMUS (Timsort):");
        System.out.println("   - Java verwendet Timsort seit Java 7 für Collections.sort() und Arrays.sort()");
        System.out.println("   - Timsort ist ein hybrider Sortieralgorithmus aus Merge Sort und Insertion Sort");
        System.out.println("   - Optimiert für teilweise sortierte Daten (reale Szenarien)");
        System.out.println("   - Der Algorithmus identifiziert natürliche aufsteigende oder absteigende Runs");
        System.out.println("   - Diese Runs werden mit Merge Sort zusammengeführt");
        System.out.println("   - Sehr kleinen Teilbereiche werden mit Insertion Sort optimiert");
        System.out.println("   - Stabil: Erhält Reihenfolge gleicher Elemente");
        System.out.println("   - Zeitkomplexität: Worst-case: O(n log n), Best-case: O(n)");
        System.out.println("   - Speicherkomplexität: O(n)");

        // Praktische Demonstration der Performance
        demonstrierePerformanceUnterschiede();

        System.out.println("\nD) COMPARATOR-CHAIN IMPLEMENTATION:");
        System.out.println("   - thenComparing() erstellt verketteten Comparator");
        System.out.println("   - Der erste Comparator in der Kette wird immer zuerst ausgeführt");
        System.out.println("   - Nur wenn der Vergleich Gleichheit (0) ergibt, wird der nächste verwendet");
        System.out.println("   - Intern wird ein neuer Comparator zurückgegeben, der beide kombiniert");
        System.out.println("   - Die verketteten Comparators werden sequentiell ausgeführt");
        System.out.println("   - Implementierung ähnlich zu: if (result != 0) return result; else return nextCompare()");

        System.out.println();
    }

    /**
     * Demonstriert Performance-Unterschiede verschiedener Sortieransätze
     * Teil der erweiterten Anforderung: Tiefes Eintauchen
     */
    private static void demonstrierePerformanceUnterschiede() {
        System.out.println("\nE) PERFORMANCE-ANALYSE:");

        DataGenerator generator = new DataGenerator(54321L);
        List<Person> testData = generator.generatePersonen(10000);

        // Natural Order
        long start = System.nanoTime();
        List<Person> naturalSorted = new ArrayList<>(testData);
        Collections.sort(naturalSorted);
        long naturalTime = System.nanoTime() - start;

        // Lambda Comparator
        start = System.nanoTime();
        List<Person> lambdaSorted = new ArrayList<>(testData);
        lambdaSorted.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
        long lambdaTime = System.nanoTime() - start;

        // Method Reference
        start = System.nanoTime();
        List<Person> methodRefSorted = new ArrayList<>(testData);
        methodRefSorted.sort(Comparator.comparing(Person::getName));
        long methodRefTime = System.nanoTime() - start;

        System.out.println("   Performance (10.000 Elemente):");
        System.out.println("   - Natural Order:    " + String.format("%,d", naturalTime) + " ns");
        System.out.println("   - Lambda:           " + String.format("%,d", lambdaTime) + " ns");
        System.out.println("   - Method Reference: " + String.format("%,d", methodRefTime) + " ns");
        System.out.println("   → Natural Order ist oft am schnellsten (direkte compareTo-Aufrufe)");
        System.out.println("   → Lambda und Method Reference haben geringen Overhead durch Funktionsobjekte");
    }
}