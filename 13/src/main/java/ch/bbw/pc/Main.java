package ch.bbw.pc;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> personen = DummyDaten.generierePersonen(4);
        // List<Person> personen = DatenGenerator.generierePersonen(4);
        System.out.println("Vor dem Sortieren:");
        personen.stream().limit(5).forEach(System.out::println);

        Sortierer.sortiere(personen);

        System.out.println("\nNach dem Sortieren:");
        personen.stream().limit(5).forEach(System.out::println);
    }
}

// curl command: curl -X GET http://localhost:8080/api/personen -o personen.json