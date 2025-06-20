package ch.bbw.lb.Aufgabe;

import ch.bbw.lb.DataClasses.DinnerTime;
import ch.bbw.lb.DataClasses.OneTimeEvent;
import ch.bbw.lb.DataClasses.RepeatedEvent;
import ch.bbw.lb.DataClasses.User;
import ch.bbw.lb.DataGeneration.DataDump;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static ch.bbw.lb.Utils.isSorted;

public class ComparatorAufgabe {

    public static void run(DataDump data) {

        // Comparator Klassen
        System.out.println("--- 1. Benutzer nach userName sortieren (Comparator-Klasse) ---");
        sortWithComparator(data);

        // Anonyme Klassen
        System.out.println("--- 2. Anonyme Klasse: Nutzer nach id absteigend ---");
        sortWithAnonymousClass(data);

        // Lambda-Expression
        System.out.println("--- 3. Lambda Expression: Nutzer nach Registrierungsdatum ---");
        sortWithLambda(data);

        // Comparator-Chains
        System.out.println("--- 4. Comparator Chain: Nutzer nach Rollenanzahl, dann userName ---");
        sortWithComparatorChain(data);

        // Statische Comparator-Attribute
        System.out.println("--- 5. Statische Comparator-Attribute in OneTimeEvent ---");
        sortWithComparatorAttribute(data);

        // Comparator-Interface-Implementierung
        System.out.println("--- 6. Comparator-Interface-Implementierung in OneTimeEvent ---");
        sortWithComparatorInterface(data);

        // Natürliche Reihenfolge und umgekehrte Reihenfolge mit Comparable
        System.out.println("--- 7. Natürliche Reihenfolge & umgekehrte Reihenfolge für RepeatedEvent ---");
        sortByNaturalOrder(data);

        // Revers-Order
        System.out.println("--- 8. Reverse Order ---");
        reverseByNaturalOrder(data);
    }

    private static void reverseByNaturalOrder(DataDump data) {
        List<OneTimeEvent> repsReverse = new ArrayList<>(data.oneTimeEvents());
        Comparator<OneTimeEvent> cmp = Comparator.comparing((OneTimeEvent r) -> r.id).reversed();
        repsReverse.sort(cmp);
        isSorted(repsReverse, cmp);

        // Mit naruralOrder() und reversed()
        var users = new ArrayList<>(data.users());
        Comparator<User> cmpUser = Comparator.<User>naturalOrder().reversed();
        users.sort(cmpUser);
        isSorted(users, cmpUser);

        // Direkt mit reverseOrder()
        var events = new ArrayList<>(data.repeatedEvents());
        Comparator<RepeatedEvent> cmpRep = Comparator.reverseOrder();
        events.sort(cmpRep);
        isSorted(events, cmpRep);
    }

    private static void sortByNaturalOrder(DataDump data) {
        List<OneTimeEvent> events = new ArrayList<>(data.oneTimeEvents());
        Comparator<OneTimeEvent> cmp = Comparator.naturalOrder();
        events.sort(cmp);
        isSorted(events, cmp);

        var repeatedEvents = new ArrayList<>(data.repeatedEvents());
        Comparator<RepeatedEvent> cmpRep = Comparator.comparing(RepeatedEvent::getDinnerTime);
        repeatedEvents.sort(cmpRep);
        isSorted(repeatedEvents, cmpRep);
    }

    private static void sortWithComparatorInterface(DataDump data) {
        List<OneTimeEvent> eventsByInstanceComparator = new ArrayList<>(data.oneTimeEvents());
        OneTimeEvent.ComparatorInstance cmp = new OneTimeEvent.ComparatorInstance();
        eventsByInstanceComparator.sort(cmp);
        isSorted(eventsByInstanceComparator, cmp);

        List<OneTimeEvent> events = new ArrayList<>(data.oneTimeEvents());
        Comparator<OneTimeEvent> cmp2 = Comparator.comparing(OneTimeEvent::getDinnerTime, new DinnerTime.ComparatorInstance());
        events.sort(cmp2);
        isSorted(events, cmp2);
    }

    private static void sortWithComparatorAttribute(DataDump data) {
        List<OneTimeEvent> eventsByDate = new ArrayList<>(data.oneTimeEvents());
        Comparator<OneTimeEvent> cmp = OneTimeEvent.BY_DATE_then_START_REVERSED_END;
        eventsByDate.sort(cmp);
        isSorted(eventsByDate, cmp);
    }

    private static void sortWithComparatorChain(DataDump data) {
        Comparator<User> byRoleCount = Comparator.comparingInt(u -> u.roles.size());
        Comparator<User> byUserPassword = Comparator.comparing(u -> u.password);
        Comparator<User> cmp = byRoleCount.thenComparing(byUserPassword);
        List<User> usersByRolesThenName = new ArrayList<>(data.users());
        usersByRolesThenName.sort(cmp);
        isSorted(usersByRolesThenName, cmp);
    }

    private static void sortWithLambda(DataDump data) {
        List<User> usersByReg = new ArrayList<>(data.users());
        Comparator<User> cmp = Comparator.comparing(u -> u.registeredAt);
        usersByReg.sort(cmp);
        isSorted(usersByReg, cmp);
    }

    @SuppressWarnings("Convert2Lambda")
    private static void sortWithAnonymousClass(DataDump data) {
        List<User> usersByIdDesc = new ArrayList<>(data.users());
        Comparator<User> cmp = new Comparator<>() {
            @Override
            public int compare(User u1, User u2) {
                return u2.id.compareTo(u1.id);
            }
        };
        usersByIdDesc.sort(cmp);
        isSorted(usersByIdDesc, cmp);

        var events = new ArrayList<>(data.oneTimeEvents());
        Comparator<OneTimeEvent> cmp2 = new Comparator<>() {
            @Override
            public int compare(OneTimeEvent o1, OneTimeEvent o2) {
                return o1.endTime.compareTo(o2.endTime);
            }
        };
        events.sort(cmp2);
        isSorted(events, cmp2);
    }

    private static void sortWithComparator(DataDump data) {
        List<User> usersByName = new ArrayList<>(data.users());
        UserNameComparator cmp = new UserNameComparator();
        usersByName.sort(cmp);
        isSorted(usersByName, cmp);

        var events = new ArrayList<>(data.repeatedEvents());
        TimeComparator cmp2 = new TimeComparator();
        events.sort(cmp2);
        isSorted(events, cmp2);
    }

    private static class UserNameComparator implements Comparator<User> {
        @Override
        public int compare(User u1, User u2) {
            return u1.userName.compareTo(u2.userName);
        }
    }

    private static class TimeComparator implements Comparator<RepeatedEvent> {
        @Override
        public int compare(RepeatedEvent e1, RepeatedEvent e2){
            return Comparator
                    .comparing(RepeatedEvent::getStartTime)
                    .thenComparing((RepeatedEvent::getEndTime))
                    .compare(e1, e2);
        }
    }
}
