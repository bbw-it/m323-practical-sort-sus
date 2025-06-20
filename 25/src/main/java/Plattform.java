// Klasse Plattform (assoziiert mit Videospiel)
class Plattform {
    String bezeichnung;
    int erscheinungsjahr;

    public Plattform(String bezeichnung, int erscheinungsjahr) {
        this.bezeichnung = bezeichnung;
        this.erscheinungsjahr = erscheinungsjahr;
    }

    @Override
    public String toString() {
        return bezeichnung + " (" + erscheinungsjahr + ")";
    }
}
