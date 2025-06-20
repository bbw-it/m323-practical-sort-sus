# 🧪 M323_sort_struct_data

## 📌 Projektbeschreibung

Dieses Java-Projekt dient der Generierung, Verwaltung und Sortierung strukturierter Testdaten im chemischen Kontext – ideal für Ausbildungs- oder Testzwecke. Es modelliert chemische **Verbindungen (Compounds)**, deren **Lösungen (Solutions)** sowie daraus entstehende **Mischungen (Mixes)**. Die Sortierung erfolgt über spezielle Services mit `Comparator`-Ketten und `Java Streams`.

---

## 🏗️ Datenstruktur

### 🔬 `Compounds`
Repräsentiert eine chemische Grundverbindung.  
Attribute:
- `Integer compound_CID` – Interne ID
- `String name` – Verbindungsname
- `String formula` – Molekülformel (z.B. C6H12O6)
- `String casNumber` – CAS-Nummer im Format 123-45-6
- `String description` – Beschreibung des Stoffes
- `String pubChemId` – Externe CID von PubChem
- `double molecularMass` – Molekulare Masse (g/mol)
- `double molecularWeight` – Molekulares Gewicht (g/mol)
- `String inchiKey` – InChIKey-Identifier
- `String inchi` – InChI-String
- `String smiles` – SMILES-Notation
- `Date createdAt` – Erstellungsdatum
- `String iupacName` – IUPAC-konformer Name

### 💧 `Solutions`
Repräsentiert eine Lösung, welche auf einem Compound basiert (z.B. mit Isotopenzusätzen).  
Attribute:
- `int cid` – Compound-ID, auf dem die Lösung basiert
- `String name` – Name der Lösung
- `String molecularFormula` – Formel der Lösung
- `double molecularWeight` – Gewicht der Lösung
- `double molecularMass` – Masse der Lösung
- `String smiles` – SMILES-Formel
- `String iupacName` – IUPAC-Name
- `List<String> synonyms` – Synonyme Bezeichnungen
- `String inchiKey` – InChIKey
- `String classification` – organisch / anorganisch
- `Date createdAt` – Erstellungszeitpunkt

### 🧪 `Mixes`
Kombination mehrerer Solutions zu einer komplexen Mischung.  
Attribute:
- `String type` – Typ der Mischung (z.B. "suspension", "buffer", ...)
- `double volume` – Gesamtvolumen in ml
- `List<Solutions> components` – Liste enthaltener Lösungen
- `Date preparedDate` – Zubereitungsdatum
- `Date expirationDate` – Haltbarkeit
- `String description` – Beschreibung

---

## ⚙️ Services

### `CompoundSorter`
- Sortiert Compounds nach Name → CAS-Nummer → Erstellungsdatum (`Comparator`-Kette)
- Sortiert alternativ nach Molekularmasse → IUPAC-Name

### `SolutionSorter`
- Sortiert nach Name → Masse

### `MixSorter`
- Sortiert nach Typ → Volumen

### `CompoundFilter` (Bonus)
- Filtert Compounds, z.B. Name enthält "5", oder Masse liegt in einem Bereich

---

## ▶️ Ausführung

### ✅ Voraussetzungen
- Java 17+
- IDE wie **PyCharm (mit Java-Plugin)**
- Keine externen Abhängigkeiten notwendig

### 🏁 Starten des Programms

1. IDE PyCharm
   - Öffne das Projekt in PyCharm
   - Klicke auf den grünen Pfeil neben der `main`-Methode CoSoMi um oberen Rand des Bildschirms


---

## 🧪 Testen

erste Unit-Tests befinden sich in der Datei:  
`src/test/java/AppTest.java`

Sie prüfen u.a.:
- Sortierfunktionen mit realen Konstruktoren

---

## 📚 Inspiration

Die chemischen Bezeichner orientieren sich an realen Daten von:  
🔗 https://pubchem.ncbi.nlm.nih.gov
(wurde aber teils stark vereinfacht)
---

## 👨‍🔬 Autor

**Benedict Brück**  
_Praktikant im Bereich Softwareentwicklung an er EAWAG_
_Projekt im Rahmen des Moduls M323 – Funktionales Programmieren / Datenverarbeitung_

