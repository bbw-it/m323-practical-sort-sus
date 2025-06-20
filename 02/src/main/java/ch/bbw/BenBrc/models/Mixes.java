package ch.bbw.BenBrc.models;

import java.util.Date;
import java.util.List;

/**
 * Datenklasse für chemische Mischungen (Mixes)
 * Die Klasse `Mixes` stellt eine Mischung dar, die aus mehreren `Solutions` besteht.
 * Sie enthält Metadaten wie den Typ (z.B. Buffer, Suspension), das Volumen, Vorbereitungs- und Verfallsdatum sowie eine Beschreibung.
 * Das zentrale Attribut ist die Liste von Komponenten (`List<Solution>`), was eine Assoziation zu einer anderen Datenklasse darstellt.
 * Die Klasse wird verwendet, um komplexe Gemische zu verwalten und ihre Zusammensetzung darzustellen.
 * Diese Klasse erfüllt damit die Anforderung "Datenstruktur mit Bezug auf Assoziation".
 * author: Benedict Brück
 * version: 1.0
 * date: 16.06.25
 */
public class Mixes {
    private int mixId;
    private String name;
    private String description;
    private String type;
    private double volume;
    private List<Solutions> components;
    private Date preparationDate;
    private Date expirationDate;

    /**
     * Konstruktor zur vollständigen Initialisierung eines Mixes-Objekts.
     * Ein Mix stellt eine Kombination aus mehreren Solutions dar, typischerweise in einer bestimmten Konzentration oder für einen speziellen Zweck
     * (z.B. Pufferlösung, Suspension, Reagenzgemisch). Diese Klasse verknüpft Lösungen zu komplexeren, zusammengesetzten Präparaten.
     * @param name              Der Name des Mixes, meist sprechend benannt nach Typ oder Inhalt (z. B. "PBS Buffer", "NaCl + Glucose Mix").
     * @param type              Die Art des Mixes (z. B. "buffer", "suspension", "reagent"). Dient der funktionellen Einordnung.
     * @param volume            Das Volumen des hergestellten Mixes (z. B. in Milliliter). Wichtig für Lagerung, Dosierung und Verbrauch.
     * @param components        Eine Liste von Solution-Objekten, die diesen Mix bilden. Enthält die eigentliche chemische Zusammensetzung.
     * @param preparationDate   Das Datum, an dem der Mix hergestellt wurde. Relevant z. B. für Frische und Qualitätssicherung.
     * @param expirationDate    Das Verfallsdatum oder maximale Nutzungsdatum. Erforderlich für Laborsicherheit und Dokumentation.
     * @param description       Eine optionale Freitextbeschreibung, z. B. zu Konzentrationen, Anwendungshinweisen oder Besonderheiten.
     */
    public Mixes(int mixId,
                 String name,
                 String description,
                 String type,
                 double volume,
                 List<Solutions> components,
                 Date preparationDate,
                 Date expirationDate) {
        this.mixId = mixId;
        this.name = name;
        this.description = description;
        this.type = type;
        this.volume = volume;
        this.components = components;
        this.preparationDate = preparationDate;
        this.expirationDate = expirationDate;
    }


    // Getters (Zugriffsmethoden) für alle Attribute
    public int getMixId() { return mixId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getType() { return type; }
    public double getVolume() { return volume; }
    public List<Solutions> getComponents() { return components; }
    public Date getPreparationDate() { return preparationDate; }
    public Date getExpirationDate() { return expirationDate; }

    // Setters (Änderungsmethoden) für alle Attribute
    public void setMixId(int mixId) { this.mixId = mixId; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setType(String type) { this.type = type; }
    public void setVolume(double volume) { this.volume = volume; }
    public void setComponents(List<Solutions> components) { this.components = components; }
    public void setPreparationDate(Date preparationDate) { this.preparationDate = preparationDate; }
    public void setExpirationDate(Date expirationDate) { this.expirationDate = expirationDate; }

    /**
     * Überschreibt die toString()-Methode, um eine lesbare Darstellung des Mixes zu liefern.
     * @return String-Repräsentation des Mixes
     */
    @Override
    public String toString() {
        return "Mixes{" +
                "mixId=" + mixId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", volume=" + volume +
                ", components=" + components + '\'' +
                ", preparationDate='" + preparationDate + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                '}';
    }
}
