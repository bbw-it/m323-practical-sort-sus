package ch.bbw.BenBrc.models;

import java.util.*;
/**
 * Datenklasse für chemische Verbindungen (Compounds)
 * Diese Klasse modelliert eine chemische Verbindung mit typischen Eigenschaften wie Name, Summenformel,
 * CAS-Nummer, Beschreibung, Strukturinformationen (SMILES, InChI), sowie Molekulargewicht und Masse.
 * Enthält:
 * - 13 Attribute mit gemischten Datentypen (String, Integer, Double, Date)
 * - Konstruktor zur Initialisierung
 * - Getter- und Setter-Methoden für jedes Feld
 * - Überschreibung der toString()-Methode für lesbare Ausgabe
 * author: Benedict Brück
 * version: 1.0
 * date: 13.06.25
 */
public class Compounds {
    private Integer compound_CID;
    private String name;
    private String formula;
    private String casNumber;
    private String description;
    private String pubChemId;
    private Double MolecularMass;
    private Double MolecularWeight;
    private String InChIKey;
    private String InChI;
    private String SMILES;
    private Date createdAt;
    private String IUPACName;

    /**
     * Konstruktor für die vollständige Initialisierung eines Compounds
     * @param compound_CID   Eindeutige ID der Verbindung
     * @param name           Name der Verbindung
     * @param formula        Summenformel
     * @param casNumber      CAS-Nummer
     * @param description    Beschreibung
     * @param pubChemId      PubChem-Datenbank-ID
     * @param molecularMass  Molekularmasse
     * @param molecularWeight Molekulargewicht
     * @param inChIKey       InChIKey (Struktur-ID)
     * @param inChI          InChI (strukturierte chemische Beschreibung)
     * @param SMILES         Strukturbeschreibung in SMILES-Notation
     * @param createdAt      Erstellungsdatum
     * @param IUPACName      IUPAC-konformer Name
     */
    public Compounds(int compound_CID,
                     String name,
                     String formula,
                     String casNumber,
                     String description,
                     String pubChemId,
                     Double molecularMass,
                     Double molecularWeight,
                     String inChIKey,
                     String inChI,
                     String SMILES,
                     Date createdAt,
                     String IUPACName) {
        this.compound_CID = compound_CID;
        this.name = name;
        this.formula = formula;
        this.casNumber = casNumber;
        this.description = description;
        this.pubChemId = pubChemId;
        this.MolecularMass = molecularMass;
        this.MolecularWeight = molecularWeight;
        this.InChIKey = inChIKey;
        this.InChI = inChI;
        this.SMILES = SMILES;
        this.createdAt = createdAt;
        this.IUPACName = IUPACName;
    }

    // Getters (Zugriffsmethoden) für alle Attribute
    public String getCasNumber() {return casNumber;}
    public Date getCreatedAt() {return createdAt;}
    public Integer getCompound_CID() {return compound_CID;}
    public String getDescription() {return description;}
    public String getFormula() {return formula;}
    public String getInChI() {return InChI;}
    public String getInChIKey() {return InChIKey;}
    public String getIUPACName() {return IUPACName;}
    public Double getMolecularMass() { return MolecularMass;}
    public Double getMolecularWeight() {return MolecularWeight;}
    public String getName() {return name;}
    public String getPubChemId() {return pubChemId;}
    public String getSMILES() {return SMILES;}

    // Setters (Änderungsmethoden) für alle Attribute
    public void setCasNumber(String casNumber) {this.casNumber = casNumber;}
    public void setCreatedAt(Date createdAt) {this.createdAt = createdAt;}
    public void setCompound_CID(Integer compound_CID) {this.compound_CID = compound_CID;}
    public void setDescription(String description) {this.description = description;}
    public void setFormula(String formula) {this.formula = formula;}
    public void setInChI(String inChI) {this.InChI = inChI;}
    public void setInChIKey(String inChIKey) {InChIKey = inChIKey;}
    public void setIUPACName(String IUPACName) {this.IUPACName = IUPACName;}
    public void setMolecularMass(Double molecularMass) {MolecularMass = molecularMass;}
    public void setMolecularWeight(Double molecularWeight) {MolecularWeight = molecularWeight;}
    public void setName(String name) {this.name = name;}
    public void setPubChemId(String pubChemId) {this.pubChemId = pubChemId;}
    public void setSMILES(String SMILES) {this.SMILES = SMILES;}

    /**
     * Überschriebene toString()-Methode zur Textausgabe der Compound-Daten
     * Gibt alle Werte des Objekts in einem lesbaren Format aus (z.B. für Debugging oder Konsolenausgabe).
     */
    @Override
    public String toString() {
        return "Compounds{" +
                "compound_CID=" + compound_CID +
                ", name='" + name + '\'' +
                ", formula='" + formula + '\'' +
                ", casNumber='" + casNumber + '\'' +
                ", description='" + description + '\'' +
                ", pubChemId='" + pubChemId + '\'' +
                ", MolecularWeight='" + MolecularWeight + '\'' +
                ", InChIKey='" + InChIKey + '\'' +
                ", InChI='" + InChI + '\'' +
                ", SMILES='" + SMILES + '\'' +
                ", createdAt=" + createdAt +
                ", IUPACName='" + IUPACName + '\'' +
                '}';
    }
}
