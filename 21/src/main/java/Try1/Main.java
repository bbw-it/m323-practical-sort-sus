package Try1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Produkt> produkte = DatenGenerator.generiereProdukte(10);

        System.out.println("Original:");
        produkte.forEach(System.out::println);

        // 1. Natural Order (Comparable)
        Collections.sort(produkte);
        System.out.println("\nSortiert nach Natural Order (Name):");
        produkte.forEach(System.out::println);

        // 2. Comparator - Preis aufsteigend (Lambda)
        produkte.sort(Comparator.comparing(Produkt::getPreis));
        System.out.println("\nSortiert nach Preis aufsteigend:");
        produkte.forEach(System.out::println);

        // 3. Comparator Chain - Kategorie, dann Preis
        produkte.sort(Comparator.comparing(Produkt::getKategorie)
                               .thenComparing(Produkt::getPreis));
        System.out.println("\nSortiert nach Kategorie, dann Preis:");
        produkte.forEach(System.out::println);

        // 4. Reverse Order 
        produkte.sort(Collections.reverseOrder());
        System.out.println("\nReverse Order der natürlichen Reihenfolge:");
        produkte.forEach(System.out::println);

        // 5. Anonyme Comparator-Klasse (nach Preis absteigend)
        produkte.sort(new Comparator<Produkt>() {
            @Override
            public int compare(Produkt p1, Produkt p2) {
                return Double.compare(p2.getPreis(), p1.getPreis());
            }
        });
        System.out.println("\nSortiert nach Preis absteigend (anonyme Klasse):");
        produkte.forEach(System.out::println);
    }
}
