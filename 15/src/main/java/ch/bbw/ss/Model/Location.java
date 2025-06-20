package ch.bbw.ss.Model;

public record Location (String country, String city) implements Comparable<Location> {

    @Override
    public int compareTo(Location o) {
        var countryCompared = CharSequence.compare(o.country, country);

        if(countryCompared == 0){
            return CharSequence.compare(city, city);
        }

        return countryCompared;
    }
}
