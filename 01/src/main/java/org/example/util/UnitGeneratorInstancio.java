package org.example.util;

import org.example.model.Faction;
import org.example.model.Unit;
import org.example.model.UnitType;
import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.Select;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class UnitGeneratorInstancio {

    private static final List<String> FACTION_NAMES = List.of("Ultramarines", "Blood Angels", "Dark Eldar", "Necrons", "Orks", "Tau");
    private static final List<String> HOMEWORLDS = List.of("Macragge", "Baal", "Commorragh", "Mundus", "Armageddon", "T'au Prime");

    public static List<Unit> generateUnits(int count) {
        List<Faction> factions = FACTION_NAMES.stream()
                .map(name -> new Faction(name,
                        HOMEWORLDS.get(ThreadLocalRandom.current().nextInt(HOMEWORLDS.size()))))
                .collect(Collectors.toList());

        Model<Unit> model = Instancio.of(Unit.class)
                .supply(Select.field("name"), gen -> gen.oneOf("Tactical Marine", "Devastator", "Assault Marine", "Dreadnought", "Land Raider", "Stormraven"))
                .supply(Select.field("firepower"), gen -> gen.intRange(10, 100))
                .supply(Select.field("armorRating"), gen -> gen.doubleRange(1.0, 10.0))
                .supply(Select.field("deploymentDate"), gen -> {
                    long daysAgo = ThreadLocalRandom.current().nextLong(0, 2000);
                    return LocalDate.now().minusDays(daysAgo);
                })
                .supply(Select.field("unitType"), gen -> gen.oneOf(UnitType.values()))
                .supply(Select.field("faction"), gen -> gen.oneOf(factions))
                .toModel();

        return Instancio.of(model).withSeed(4242L).stream().limit(count).collect(Collectors.toList());
    }
}