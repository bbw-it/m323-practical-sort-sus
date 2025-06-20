package Try1;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DatenGenerator {
    public static List<Produkt> generiereProdukte(int anzahl) {
        return IntStream.range(0, anzahl)
                .mapToObj(i -> new Produkt(
                        "Produkt" + i,
                        ThreadLocalRandom.current().nextDouble(10.0, 500.0),
                        LocalDate.now().minusDays(ThreadLocalRandom.current().nextInt(0, 1000)),
                        Kategorie.values()[ThreadLocalRandom.current().nextInt(Kategorie.values().length)],
                        List.of("tag1", "tag2", "tag" + i)
                ))
                .collect(Collectors.toList());
    }
}
