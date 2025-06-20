package ch.bbw.pc;

public class Adresse {
    private String stadt;
    private String strasse;

    public Adresse(String stadt, String strasse) {
        this.stadt = stadt;
        this.strasse = strasse;
    }

    public String getStadt() { return stadt; }
    public String getStrasse() { return strasse; }

    @Override
    public String toString() {
        return strasse + ", " + stadt;
    }
}
