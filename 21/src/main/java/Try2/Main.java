package Try2;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Produkt> produkte = DatenGenerator.generiereProdukte(100);

        // Natural Order
        Collections.sort(produkte);
        System.out.println("Produkte nach natürlicher Reihenfolge (Preis aufsteigend):");
        produkte.subList(0, 5).forEach(System.out::println);
        System.out.println();

        // Reverse Order
        Collections.sort(produkte, Comparator.reverseOrder());
        System.out.println("Produkte nach umgekehrter Reihenfolge (Preis absteigend):");
        produkte.subList(0, 5).forEach(System.out::println);
        System.out.println();

        List<Kunde> kunden = new ArrayList<>(List.of(
                new Kunde("Alice", produkte.subList(0, 5)),
                new Kunde("Bob", produkte.subList(5, 10)),
                new Kunde("Charlie", produkte.subList(10, 15))
        ));

        Kunde.sortiereNachTeuerstemProdukt(kunden);
        System.out.println("Kunden sortiert nach teuerstem Produkt:");
        kunden.forEach(k -> System.out.println(k.name + " - Teuerstes Produkt: " + k.getTeuerstesProdukt().preis));
        System.out.println();

        kunden.sort(Comparator.comparingDouble(Kunde::getGesamtpreisProdukte).reversed());
        System.out.println("Kunden sortiert nach Gesamtpreis aller Produkte (absteigend):");
        kunden.forEach(k -> System.out.println(k.name + " - Gesamtpreis: " + k.getGesamtpreisProdukte()));
        System.out.println();

        kunden.sort(Comparator.comparingInt((Kunde k) -> k.produkte.size())
                .thenComparing(k -> k.name));
        System.out.println("Kunden sortiert nach Anzahl Produkte, dann Name:");
        kunden.forEach(k -> System.out.println(k.name + " - Anzahl Produkte: " + k.produkte.size()));
        System.out.println();

        System.out.println("Erklärung:");
        System.out.println("- Comparable Produkt: sortiert nach Preis aufsteigend.");
        System.out.println("- Reverse Order: kehrt sortierte Reihenfolge um.");
        System.out.println("- Comparator: Kunden werden nach Kriterien sortiert.");
        System.out.println("- Java verwendet intern TimSort für sort.");
    }
}
