package org.example;

import com.github.javafaker.Faker;
import org.instancio.Instancio;
import org.instancio.Model;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.instancio.Select.field;

public class DataGenerator {

    private static final long SEED = 12345L;
    private static final Faker faker = new Faker(new Random(SEED));

    private static final Model<Person> personModel = Instancio.of(Person.class)
            .withSeed(SEED)
            .supply(field(Person.class, "name"), () -> faker.name().firstName())
            .supply(field(Person.class, "age"), () -> faker.number().numberBetween(18, 70))
            .supply(field(Person.class, "employed"), () -> faker.bool().bool())

            .supply(field(Address.class, "street"), () -> faker.address().streetAddress())
            .supply(field(Address.class, "city"), () -> faker.address().city())
            .supply(field(Address.class, "postalCode"), () -> faker.address().zipCode())

            .supply(field(Person.class, "hobbies"), () -> List.of(
                    faker.options().option("Fußball", "Lesen", "Schwimmen", "Programmieren", "Kochen"),
                    faker.options().option("Reisen", "Musik", "Joggen", "Fotografie", "Gaming")
            ))

            .supply(field(Dog.class, "name"), () -> faker.dog().name())
            .supply(field(Dog.class, "breed"), () -> faker.dog().breed())
            .supply(field(Dog.class, "age"), () -> faker.number().numberBetween(1, 15))

            .toModel();

    public static Person generatePerson() {
        return Instancio.of(personModel).create();
    }

    public static List<Person> generatePeople(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> generatePerson())
                .collect(Collectors.toList());
    }
}
