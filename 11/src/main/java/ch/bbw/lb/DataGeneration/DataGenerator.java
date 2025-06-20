package ch.bbw.lb.DataGeneration;

import ch.bbw.lb.DataClasses.DinnerTime;
import ch.bbw.lb.DataClasses.OneTimeEvent;
import ch.bbw.lb.DataClasses.PresenceType;
import ch.bbw.lb.DataClasses.RepeatedEvent;
import ch.bbw.lb.DataClasses.User;
import ch.bbw.lb.DataGeneration.adapter.GsonAdapter;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DataGenerator {
    public static final String FINAL_FILE = "data.json";

    private static final Random RANDOM = new Random(42);
    private static final String[] POSSIBLE_ROLES = { "ADMIN", "USER", "MANAGER", "GUEST" };

    public static void main(String[] args) throws IOException {
        // 1) Users erzeugen
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            String userName = "user" + i;
            String email    = userName + "@example.com";
            String password = "pass" + i;
            LocalDateTime regAt = LocalDateTime.of(2020, 1, 1, 0, 0)
                    .plusDays(RANDOM.nextInt(365))
                    .plusHours(RANDOM.nextInt(24))
                    .plusMinutes(RANDOM.nextInt(60));

            // 1–3 zufällige, eindeutige Rollen pro User
            List<String> roles = RANDOM.ints(0, POSSIBLE_ROLES.length)
                    .distinct()
                    .limit(1 + RANDOM.nextInt(3))
                    .mapToObj(idx -> POSSIBLE_ROLES[idx])
                    .collect(Collectors.toList());

            users.add(new User(i, userName, email, password, regAt, roles));
        }

        // 2) OneTimeEvents erzeugen
        List<OneTimeEvent> oneTimeEvents = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            LocalDate date = LocalDate.of(2021, 1, 1)
                    .plusDays(RANDOM.nextInt(365));
            LocalTime start = LocalTime.of(RANDOM.nextInt(24), RANDOM.nextInt(60));
            LocalTime end   = start.plusHours(1).plusMinutes(RANDOM.nextInt(60));
            PresenceType presence = PresenceType.values()[RANDOM.nextInt(PresenceType.values().length)];
            // Zeit nur für DEFAULT oder LATE
            LocalTime dinnerTime = (presence == PresenceType.DEFAULT || presence == PresenceType.LATE)
                    ? LocalTime.of(RANDOM.nextInt(24), RANDOM.nextInt(60))
                    : null;
            int userId = 1 + RANDOM.nextInt(10);

            oneTimeEvents.add(
                    new OneTimeEvent(
                            i,
                            "OneTimeEvent" + i,
                            date,
                            start,
                            end,
                            new DinnerTime(presence, dinnerTime),
                            userId
                    )
            );
        }

        // 3) RepeatedEvents erzeugen
        List<RepeatedEvent> repeatedEvents = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            LocalDate first = LocalDate.of(2021, 1, 1)
                    .plusDays(RANDOM.nextInt(365));
            LocalDate last  = first.plusDays(RANDOM.nextInt(31));
            LocalTime start = LocalTime.of(RANDOM.nextInt(24), RANDOM.nextInt(60));
            LocalTime end   = start.plusHours(1).plusMinutes(RANDOM.nextInt(60));
            PresenceType presence = PresenceType.values()[RANDOM.nextInt(PresenceType.values().length)];
            LocalTime dinnerTime = (presence == PresenceType.DEFAULT || presence == PresenceType.LATE)
                    ? LocalTime.of(RANDOM.nextInt(24), RANDOM.nextInt(60))
                    : null;
            int userId = 1 + RANDOM.nextInt(10);

            repeatedEvents.add(
                    new RepeatedEvent(
                            i,
                            "RepeatedEvent" + i,
                            first,
                            last,
                            start,
                            end,
                            new DinnerTime(presence, dinnerTime),
                            userId
                    )
            );
        }

        // 4) Alles in einem Wrapper sammeln
        DataDump dump = new DataDump(users, oneTimeEvents, repeatedEvents);

        // 5) JSON-Output
        var fileWriter = new FileWriter(FINAL_FILE);
        fileWriter.write(GsonAdapter.createGson().toJson(dump));
        fileWriter.close();

        System.out.println("✔️  " + FINAL_FILE + " created with 10 Users, 100 OneTimeEvents & 100 RepeatedEvents.");
    }
}
