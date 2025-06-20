package ch.bbw.util;

import ch.bbw.model.Address;
import ch.bbw.model.Dog;
import ch.bbw.model.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {
    private static final String[] NAMES = {"Anna", "Ben", "Clara", "David", "Eva", "Felix", "Greta", "Hans", "Ida", "Jonas"};
    private static final String[] CITIES = {"Zürich", "Bern", "Basel", "Luzern", "St. Gallen"};
    private static final String[] COUNTRIES = {"Schweiz", "Deutschland", "Österreich"};
    private static final String[] STREETS = {"Hauptstrasse", "Bahnhofstrasse", "Gartenweg", "Schulweg", "Bergstrasse"};
    private static final String[] DOG_NAMES = {"Bello", "Luna", "Rocky", "Milo", "Emma", "Sam"};
    private static final String[] BREEDS = {"Labrador", "Pudel", "Dackel", "Schäferhund", "Mischling"};
    private static final Random random = new Random(42); // fixed seed for reproducibility

    public static List<Person> generatePersons(int count) {
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String name = NAMES[random.nextInt(NAMES.length)] + i;
            int age = 18 + random.nextInt(60);
            double height = 150 + random.nextDouble() * 50;
            LocalDate birthday = LocalDate.now().minusYears(age).minusDays(random.nextInt(365));
            Address address = new Address(
                    STREETS[random.nextInt(STREETS.length)],
                    1 + random.nextInt(100),
                    CITIES[random.nextInt(CITIES.length)],
                    COUNTRIES[random.nextInt(COUNTRIES.length)]
            );
            persons.add(new Person(name, age, height, birthday, address));
        }
        return persons;
    }

    public static List<Dog> generateDogs(int count, List<Person> owners) {
        List<Dog> dogs = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String name = DOG_NAMES[random.nextInt(DOG_NAMES.length)] + i;
            String breed = BREEDS[random.nextInt(BREEDS.length)];
            LocalDate birthDate = LocalDate.now().minusYears(1 + random.nextInt(15)).minusDays(random.nextInt(365));
            double weight = 5 + random.nextDouble() * 35;
            Person owner = owners.get(random.nextInt(owners.size()));
            dogs.add(new Dog(name, breed, birthDate, weight, owner));
        }
        return dogs;
    }
}
