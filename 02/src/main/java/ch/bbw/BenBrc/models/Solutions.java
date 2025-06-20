package ch.bbw.BenBrc.models;

import java.util.*;
/**
 * Datenklasse für chemische Lösungen (Solutions)
 * Die Klasse `Solutions` ist eine Erweiterung der `Compounds`-Daten.
 * Sie stellt eine chemische Verbindung mit einem Zusatz dar, wie z.B. Deuterium oder ein Lösungsmittel.
 * Neben den geerbten Attributen besitzt die Klasse zusätzliche Felder wie `classification` und `molecularMass`.
 * Diese Klasse wird für erweiterte Analysen und Ausgaben verwendet, insbesondere beim Drucken oder Sortieren.
 * Die `Solutions`-Objekte verknüpfen also Basisdaten einer Verbindung mit spezifischen Eigenschaften als Lösung.
 * author: Benedict Brück
 * version: 1.0
 * date: 13.06.25
 */
public class Solutions {
    private int cid;
    private String name;
    private String molecularFormula;
    private double molecularWeight;
    private double molecularMass; // NEW
    private String smiles;
    private String iupacName;
    private List<String> synonyms;
    private String inchiKey;
    private String classification;
    private Date createdAt;

    /**
     * Konstruktor für die vollständige Initialisierung eines Solutions-Objekts
     * @param cid               Die eindeutige numerische Kennung (Compound ID) der Lösung. Dient zur internen Identifikation.
     * @param name              Der Name der Lösung, oft basierend auf dem Ursprungs-Compound.
     * @param molecularFormula  Die chemische Formel (z.B. C6H12O6) der Lösung. Kann durch Zusätze von der Basisformel abweichen.
     * @param molecularWeight   Das Molekulargewicht der Lösung (g/mol). Nützlich für quantitative Berechnungen.
     * @param molecularMass     Die präzise Molekülmasse basierend auf Isotopenverteilung. Wird oft für Massenspektrometrie verwendet.
     * @param smiles            Die SMILES-Notation (strukturierter Text) der Verbindung. Wichtig für cheminformatische Anwendungen.
     * @param iupacName         Der standardisierte IUPAC-Name, der die chemische Struktur formal beschreibt.
     * @param synonyms          Eine Liste bekannter Synonyme oder Handelsnamen der Lösung. Nützlich für Suchen oder Datenabgleich.
     * @param inchiKey          Der InChIKey als kompakter, eindeutiger Bezeichner für die chemische Struktur.
     * @param classification    Eine inhaltliche Zuordnung (z.B. "organic", "buffer", "acid"). Dient der Gruppierung.
     * @param createdAt         Das Erstellungsdatum der Lösung in der Datenbank oder Sammlung. Relevanz z.B. für Haltbarkeit oder Labordokumentation.
     */
    public Solutions(int cid,
                     String name,
                     String molecularFormula,
                     double molecularWeight,
                     double molecularMass,
                     String smiles,
                     String iupacName,
                     List<String> synonyms,
                     String inchiKey,
                     String classification,
                     Date createdAt) {
        this.cid = cid;
        this.name = name;
        this.molecularFormula = molecularFormula;
        this.molecularWeight = molecularWeight;
        this.molecularMass = molecularMass;
        this.smiles = smiles;
        this.iupacName = iupacName;
        this.synonyms = synonyms;
        this.inchiKey = inchiKey;
        this.classification = classification;
        this.createdAt = createdAt != null ? createdAt : new Date();
    }

    // Getters (Zugriffsmethoden) für alle Attribute
    public int getCid() { return cid; }
    public String getName() { return name; }
    public String getMolecularFormula() { return molecularFormula; }
    public double getMolecularWeight() { return molecularWeight; }
    public double getMolecularMass() { return molecularMass; } // NEW
    public String getSmiles() { return smiles; }
    public String getIupacName() { return iupacName; }
    public List<String> getSynonyms() { return synonyms; }
    public String getInchiKey() { return inchiKey; }
    public String getClassification() { return classification; }
    public Date getCreatedAt() { return createdAt; }

    // Setters (Änderungsmethoden) für alle Attribute
    public void setCid(int cid) { this.cid = cid; }
    public void setName(String name) { this.name = name; }
    public void setMolecularFormula(String molecularFormula) { this.molecularFormula = molecularFormula; }
    public void setMolecularWeight(double molecularWeight) { this.molecularWeight = molecularWeight; }
    public void setMolecularMass(double molecularMass) { this.molecularMass = molecularMass; } // NEW
    public void setSmiles(String smiles) { this.smiles = smiles; }
    public void setIupacName(String iupacName) { this.iupacName = iupacName; }
    public void setSynonyms(List<String> synonyms) { this.synonyms = synonyms; }
    public void setInchiKey(String inchiKey) { this.inchiKey = inchiKey; }
    public void setClassification(String classification) { this.classification = classification; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt != null ? createdAt : new Date();}

    /**
     * Überschreibt die toString-Methode, um eine lesbare Darstellung des Objekts zu erhalten.
     * @return String-Repräsentation des Solutions-Objekts
     */
    @Override
    public String toString() {
        return "Solutions{" +
                "cid=" + cid +
                ", name='" + name + '\'' +
                ", molecularFormula='" + molecularFormula + '\'' +
                ", molecularWeight=" + molecularWeight +
                ", molecularMass=" + molecularMass + // NEW
                ", smiles='" + smiles + '\'' +
                ", iupacName='" + iupacName + '\'' +
                ", synonyms=" + synonyms +
                ", inchiKey='" + inchiKey + '\'' +
                ", classification='" + classification + '\'' +
                ", createdAt=" + createdAt + '\'' +
                '}';
    }
}
