package ch.bbw.pc;

import org.instancio.Instancio;
import org.instancio.Select;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DatenGenerator {
    public static List<Person> generierePersonen(int anzahl) {
        return IntStream.range(0, anzahl)
                .mapToObj(i -> Instancio.of(Person.class)
                        .set(Select.field("geburtstag"), LocalDate.now().minusYears((long) (Math.random() * 80)))
                        .create())
                .collect(Collectors.toList());
    }
}
