package Try1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Kunde {
    String name;
    List<Produkt> bestellungen;

    public Kunde(String name, List<Produkt> bestellungen) {
        this.name = name;
        this.bestellungen = bestellungen;
    }

    public List<Produkt> getBestellungen() {
        return bestellungen;
    }

    public static void sortiereNachTeuerstemProdukt(List<Kunde> kunden) {
        List<Kunde> kopie = new ArrayList<>(kunden);
        kopie.sort(Comparator.comparing(
            k -> k.getBestellungen().stream().mapToDouble(Produkt::getPreis).max().orElse(0.0)
        ));
    }
}
