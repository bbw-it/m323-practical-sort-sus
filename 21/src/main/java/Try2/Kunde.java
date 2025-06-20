package Try2;

import java.util.Comparator;
import java.util.List;

public class Kunde {
    String name;
    List<Produkt> produkte;

    public Kunde(String name, List<Produkt> produkte) {
        this.name = name;
        this.produkte = produkte;
    }

    public Produkt getTeuerstesProdukt() {
        return produkte.stream().max(Comparator.naturalOrder()).orElse(null);
    }

    public double getGesamtpreisProdukte() {
        return produkte.stream().mapToDouble(p -> p.preis).sum();
    }

    public static void sortiereNachTeuerstemProdukt(List<Kunde> kunden) {
        kunden.sort((k1, k2) -> {
            Produkt p1 = k1.getTeuerstesProdukt();
            Produkt p2 = k2.getTeuerstesProdukt();
            if (p1 == null && p2 == null) return 0;
            if (p1 == null) return 1;
            if (p2 == null) return -1;
            return Double.compare(p2.preis, p1.preis);
        });
    }
}
