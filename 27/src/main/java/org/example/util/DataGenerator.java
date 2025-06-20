package org.example.util;

import org.example.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DataGenerator {
    private static final Random random = new Random();

    private static final String[] ALIEN_SPECIES_PREFIXES = {
            "Gleep", "Xylar", "Nebula", "Quasar", "Void", "Stardust", "Cosmic", "Photon", "Zor", "Alpha", "Krypto", "Lunar", "Solar", "Dimen", "Grav", "Singular"
    };
    private static final String[] ALIEN_SPECIES_SUFFIXES = {
            "glorp", "ion", "Nymph", "Quokka", "Viper", "Steed", "Kraken", "Pheasant", "gon", "Mite", "Jumper", "Moth", "Fox", "Dweller", "Grub", "Sloth"
    };

    private static final String[] PLANET_NAMES = {
            "Xylos", "Zephyrion", "Aqua Prime", "Terra Nova II", "Cryonia", "Infernus VI", "Aetheria",
            "Geodia", "Volcanis", "Botanica", "Mechanos", "Psychedelia", "Chronos Prime", "Nihilum", "Pandora", "Arrakis"
    };

    private static final String[] ZOOKEEPER_NAMES = {
            "Jax Xenon", "Lyra Nova", "Cmdr. Kael", "Dr. Aris Thorne", "Seraphina Luna", "Orion Pax", "Zara Quinton"
    };

    private static final String[][] KNOWN_LANGUAGES_OPTIONS = {
            {"Galactic Basic"}, {"Huttish", "Binary"}, {"Shyriiwook"}, {"Klingon", "Standard"},
            {"Telepathic Echoes"}, {"Silent Understanding"}, {"Quantum Lingo"}, {"Flow-Speak"}
    };

    public static Planet generatePlanet() {
        String name = PLANET_NAMES[random.nextInt(PLANET_NAMES.length)];
        int distance = random.nextInt(10000) + 100;
        AtmosphereType atmType = AtmosphereType.values()[random.nextInt(AtmosphereType.values().length)];
        return new Planet(name, distance, atmType);
    }

    public static IntelligenceProfile generateIntelligenceProfile() {
        int iq = random.nextInt(281) + 20;
        boolean telepathic = random.nextBoolean();
        List<String> languages = new ArrayList<>(Arrays.asList(KNOWN_LANGUAGES_OPTIONS[random.nextInt(KNOWN_LANGUAGES_OPTIONS.length)]));
        if (random.nextDouble() < 0.2) {
            languages.add("Ancient " + (char)('A' + random.nextInt(26)));
        }
        return new IntelligenceProfile(iq, telepathic, languages);
    }

    public static LocalDate generateDateDiscovered() {
        long minDay = LocalDate.of(2200, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2300, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    public static AlienAnimal generateAlienAnimal() {
        String species = ALIEN_SPECIES_PREFIXES[random.nextInt(ALIEN_SPECIES_PREFIXES.length)] +
                ALIEN_SPECIES_SUFFIXES[random.nextInt(ALIEN_SPECIES_SUFFIXES.length)] + " " +
                (char)('A' + random.nextInt(26)) + (random.nextInt(90) + 10);
        Planet planet = generatePlanet();
        double weight = Math.round((random.nextDouble() * 499.9 + 0.1) * 100.0) / 100.0; // 0.1 to 500.0 tons
        LocalDate discovered = generateDateDiscovered();
        DangerLevel danger = DangerLevel.values()[random.nextInt(DangerLevel.values().length)];
        IntelligenceProfile intel = generateIntelligenceProfile();
        return new AlienAnimal(species, planet, weight, discovered, danger, intel);
    }

    public static List<AlienAnimal> generateAlienAnimalList(int count) {
        List<AlienAnimal> animals = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            animals.add(generateAlienAnimal());
        }
        return animals;
    }

    public static List<Zookeeper> generateZookeepers(int count, List<AlienAnimal> allAnimals) {
        List<Zookeeper> zookeepers = new ArrayList<>();
        List<AlienAnimal> animalsToAssign = new ArrayList<>(allAnimals); // Create a mutable copy
        Collections.shuffle(animalsToAssign); // Shuffle animals to distribute them more randomly

        int totalAnimals = animalsToAssign.size();
        int numKeepers = Math.max(1, count);

        for (int i = 0; i < numKeepers; i++) {
            String name;
            if (i < ZOOKEEPER_NAMES.length) {
                name = ZOOKEEPER_NAMES[i];
            } else {
                name = "Keeper Candidate " + (i + 1 - ZOOKEEPER_NAMES.length);
            }
            int experience = random.nextInt(30) + 1;
            Zookeeper keeper = new Zookeeper(name, experience);

            if (totalAnimals > 0) {
                // Distribute animals among keepers
                // Start index for this keeper's animals in the shuffled list
                int startIndex = i * totalAnimals / numKeepers;
                // End index for this keeper's animals
                int endIndex = (i + 1) * totalAnimals / numKeepers;

                if (startIndex < endIndex && startIndex < animalsToAssign.size()) {
                    keeper.assignCreatures(new ArrayList<>(animalsToAssign.subList(startIndex, Math.min(endIndex, animalsToAssign.size()))));
                }
            }
            zookeepers.add(keeper);
        }
        return zookeepers;
    }
}