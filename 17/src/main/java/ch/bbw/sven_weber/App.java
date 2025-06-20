package ch.bbw.sven_weber;

import ch.bbw.sven_weber.model.Country;
import ch.bbw.sven_weber.model.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Sven Weebr
 */
public class App 
{
    private static final List<User> users = new ArrayList<User>();

    public static void main( String[] args )
    {
        ObjectMapper mapper = new ObjectMapper();

        System.out.println("Load data.json into users List!");

        try {
            InputStream inputStream = App.class.getClassLoader().getResourceAsStream("data.json");
            JsonNode root = mapper.readTree(inputStream);

            if (root.isArray()) {
                for (JsonNode node : root) {
                    // Get Data from JSON
                    String name = node.get("name").asText();
                    String email = node.get("email").asText();
                    boolean hasWon = node.get("hasWon").asBoolean();
                    LocalDate date = LocalDate.parse(node.get("date").asText());
                    byte score = (byte)node.get("score").asInt();
                    Country county = new Country(node.get("country").asText());
                    int globalRank = node.get("globalRank").asInt();

                    User user = new User(name, email, hasWon, date, score, county, globalRank);
                    users.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        // Successfully loaded Data, now we process it!

        System.out.println("###############################################################################");
        System.out.println("############################# Comparable Example! #############################");
        System.out.println("###############################################################################");
        System.out.println("Users Natural sort by Score and if tie than by Name:" + System.lineSeparator());

        // This uses compareTo() from the Comparable<T> interface!
        List<User> naturalSort = users.stream().sorted().limit(10).toList();

        // Print it a bit nicer.
        final byte[] currentScore = { naturalSort.isEmpty() ? 0 : naturalSort.get(0).getScore() };
        naturalSort.forEach((user -> {
            if(user.getScore() != currentScore[0]){
                currentScore[0] = user.getScore();
                System.out.print(System.lineSeparator());
            }

            System.out.print(user);
        }));

        System.out.println(System.lineSeparator() + System.lineSeparator() + System.lineSeparator());



        System.out.println("###############################################################################");
        System.out.println("############################# Comparator Example! #############################");
        System.out.println("###############################################################################");
        System.out.println("Users sorted by Score and then sorted by Date:" + System.lineSeparator());

        users.sort(Comparator.comparing(User::getScore).reversed().thenComparing(User::getDate));
        users.stream().limit(10).forEach(System.out::println);

        System.out.println(System.lineSeparator() + System.lineSeparator() + System.lineSeparator());



        System.out.println("###############################################################################");
        System.out.println("#################### Comparator as external class Example! ####################");
        System.out.println("###############################################################################");
        System.out.println("Users sorted by Email domain:");

        List<User> sortedByExternalClass = users.stream().sorted(new EmailComparator()).toList();

        // Print it a bit nicer.
        final String[] currentEmail = { sortedByExternalClass.isEmpty() ? "" : sortedByExternalClass.get(0).getEmailDomain() };
        sortedByExternalClass.forEach((user -> {
            if(!user.getEmailDomain().equals(currentEmail[0])){
                currentEmail[0] = user.getEmailDomain();
                System.out.print(System.lineSeparator());
            }

            System.out.print(user);
        }));

        System.out.println(System.lineSeparator() + System.lineSeparator() + System.lineSeparator());



        System.out.println("###############################################################################");
        System.out.println("#################### Comparator as anonymous class Example! ###################");
        System.out.println("###############################################################################");
        System.out.println("Users sorted by Country then by Score:" + System.lineSeparator());

        List<User> sortedByAnonymousClass = users.stream().sorted(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                // Compare via associated class.
                return o1.getCountry().getName().compareTo(o2.getCountry().getName());
            }
        }.thenComparing(User::getScore).reversed()).toList();

        // Print it a bit nicer.
        final String[] currentCountry = { sortedByAnonymousClass.isEmpty() ? "" : sortedByAnonymousClass.get(0).getCountry().getName() };
        sortedByAnonymousClass.forEach((user -> {
            if(!user.getCountry().getName().equals(currentCountry[0])){
                currentCountry[0] = user.getCountry().getName();
                System.out.print(System.lineSeparator());
            }

            System.out.print(user);
        }));

        System.out.println(System.lineSeparator() + System.lineSeparator() + System.lineSeparator());



        System.out.println("###############################################################################");
        System.out.println("####################### Comparator with Lambda Example! #######################");
        System.out.println("###############################################################################");
        System.out.println("Users sorted by Global Rank:" + System.lineSeparator());

        List<User> sortedByLambda = users.stream().sorted((o1, o2) -> Integer.compare(o1.getGlobalRank(), o2.getGlobalRank())).toList();

        // Print it a bit nicer.
        final int[] currentRank = { sortedByLambda.isEmpty() ? 0 : sortedByLambda.get(0).getGlobalRank() };
        sortedByLambda.forEach((user -> {
            if(user.getGlobalRank() != currentRank[0]){
                currentRank[0] = user.getGlobalRank();
                System.out.print(System.lineSeparator());
            }

            System.out.print(user);
        }));

        System.out.println(System.lineSeparator() + System.lineSeparator() + System.lineSeparator());



        System.out.println("###############################################################################");
        System.out.println("########################## Comparator Chain Example! ##########################");
        System.out.println("###############################################################################");
        System.out.println("Users Multilevel sorted by Global Rank then by Score:" + System.lineSeparator());

        List<User> sortedByComparatorChain = users.stream().sorted(Comparator.comparing(User::getGlobalRank).reversed().thenComparing(User::getScore).reversed()).toList();

        // Print it a bit nicer.
        final int[] currentRank1 = { sortedByComparatorChain.isEmpty() ? 0 : sortedByComparatorChain.get(0).getGlobalRank() };
        sortedByComparatorChain.forEach((user -> {
            if(user.getGlobalRank() != currentRank1[0]){
                currentRank1[0] = user.getGlobalRank();
                System.out.print(System.lineSeparator());
            }

            System.out.print(user);
        }));

        System.out.println(System.lineSeparator() + System.lineSeparator() + System.lineSeparator());



        System.out.println("###############################################################################");
        System.out.println("################ Comparator as static field in class Example! #################");
        System.out.println("###############################################################################");
        System.out.println("Users sorted by Country then by Global Rank then by Score:" + System.lineSeparator());

        // Compare via associated class.
        List<User> sortedByComparatorInClass = users.stream().sorted(User.COMPARATOR_COUNTRY.thenComparing(User.COMPARATOR_GLOBAL_RANK).reversed().thenComparing(User.COMPARATOR_SCORE).reversed()).toList();

        // Print it a bit nicer.
        final String[] currentCountry1 = { sortedByComparatorInClass.isEmpty() ? "" : sortedByComparatorInClass.get(0).getCountry().getName() };
        sortedByComparatorInClass.forEach((user -> {
            if(!user.getCountry().getName().equals(currentCountry1[0])){
                currentCountry1[0] = user.getCountry().getName();
                System.out.print(System.lineSeparator());
            }

            System.out.print(user);
        }));

        System.out.println(System.lineSeparator() + System.lineSeparator() + System.lineSeparator());

        // Dieser Teil ist in Deutsch da das erklären so einfacher war.

        System.out.println("###############################################################################");
        System.out.println("############################### Zusatz Aufgabe ################################");
        System.out.println("###############################################################################");

        System.out.println("Natural-Order:");
        System.out.println("Java verwendet TimSort um Daten zu sortieren. Integer werden nach grösse sortiert und Strings werden Alphabetisch sortiert." + System.lineSeparator() + "Dabei muss das Interface Comparable implementiert sein, somit kann mit compareTo(T) die klasse sortiert werden." + System.lineSeparator() + "Desswegen ist es manchmal mühsam wenn man z.B. bei Strings: [1,5,9,11,12] denn sortiert es dies so: [1,11,12,5,9]." + System.lineSeparator() + "Das ganze kann man mit Zero padding \"fixen\"." + System.lineSeparator());
        System.out.println("Reverse-Order:");
        System.out.println("Es macht genau das gleiche wie bei Natural-Order aber das ergebniss wird negiert." + System.lineSeparator());
        System.out.println("Wie sortiert Java:");
        System.out.println("Es nutzt TimSort, eine mischung von MergeSort und InsertionSort." + System.lineSeparator() + "Ich dachte immer es wäre bubbleSort aber mit O(n²) ist es viel langsamer als TimSort welches von O(n) zu O(n log n) geht." + System.lineSeparator());
        System.out.println("Was macht Java genau beim Sortieren:");
        System.out.println("Es Prüft ob die Liste <= 1 ist, wenn ja ist das Sortieren direkt fertig." + System.lineSeparator() + "Danach führt es TimSort aus. Es wird die Methode compareTo(T) oder den Comperator ausgeführt. " + System.lineSeparator() + "Die Sortierung verwendet die werte die bei compareTo oder Comparator zurück kommen, es wird nichts selbst von Java convertiert.");
    }
}
