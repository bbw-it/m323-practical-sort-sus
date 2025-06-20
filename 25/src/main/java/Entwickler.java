// Klasse Entwickler (nicht-primitiver Datentyp)
class Entwickler {
    String name;
    String land;

    public Entwickler(String name, String land) {
        this.name = name;
        this.land = land;
    }

    @Override
    public String toString() {
        return name + " aus " + land;
    }
}