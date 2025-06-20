import java.util.Comparator;

class BewertungComparator implements Comparator<Videospiel> {
    public int compare(Videospiel v1, Videospiel v2) {
        return Double.compare(v1.bewertung, v2.bewertung);
    }
}