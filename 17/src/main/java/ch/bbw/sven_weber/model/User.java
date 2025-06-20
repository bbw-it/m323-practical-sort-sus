package ch.bbw.sven_weber.model;

import java.time.LocalDate;
import java.util.Comparator;

/**
 * @author Sven Weebr
 */
public class User implements Comparable<User>{
    public static final Comparator<User> COMPARATOR_GLOBAL_RANK = Comparator.comparing(User::getGlobalRank);
    public static final Comparator<User> COMPARATOR_COUNTRY = Comparator.comparing((user) -> user.country.getName());
    public static final Comparator<User> COMPARATOR_SCORE = Comparator.comparing(User::getScore);

    private final String name;
    private final String email;
    private final boolean hasWon;
    private final LocalDate date;
    private final byte score;
    private final Country country;
    /**
     * 0 = Amateur
     * 1 = Semi-Pro
     * 2 = Pro
     * 3 = Elite (World-wide)
     */
    private final int globalRank;


    public User(String name, String email, boolean hasWon, LocalDate date, byte score, Country country, int globalRank) {
        this.name = name;
        this.email = email;
        this.hasWon = hasWon;
        this.date = date;
        this.score = score;
        this.country = country;
        this.globalRank = globalRank;
    }

    /**
     * from Comparable interface!
     */
    @Override
    public int compareTo(User other) {
        // Natural order by score
        int value = Byte.compare(this.score, other.score);

        // Check if comparison is NOT equal, then return
        if(value != 0) return value;

        // If score is equal then we sort by name
        return this.name.compareTo(other.name);
    }

    public String getEmailDomain(){
        String[] part = email.split("@");
        return part[1].substring(0, part[1].indexOf("."));
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean hasWon() {
        return hasWon;
    }

    public LocalDate getDate() {
        return date;
    }

    public byte getScore() {
        return score;
    }

    public Country getCountry() {
        return country;
    }

    public int getGlobalRank(){
        return globalRank;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", hasWon=" + hasWon +
                ", date=" + date +
                ", score=" + score +
                ", country_id=" + country.getName() +
                ", globalRank=" + globalRank +
                '}';
    }
}
