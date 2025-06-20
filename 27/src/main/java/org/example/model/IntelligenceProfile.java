package org.example.model;

import java.util.ArrayList;
import java.util.List;

// Klasse für das Intelligenzprofil
public class IntelligenceProfile {
    private int iqLevel; // 0 - 300
    private boolean telepathic;
    private List<String> knownLanguages;

    public IntelligenceProfile(int iqLevel, boolean telepathic, List<String> knownLanguages) {
        this.iqLevel = iqLevel;
        this.telepathic = telepathic;
        this.knownLanguages = new ArrayList<>(knownLanguages); // Defensive copy
    }

    // Getter
    public int getIqLevel() { return iqLevel; }
    public boolean isTelepathic() { return telepathic; }
    public List<String> getKnownLanguages() { return new ArrayList<>(knownLanguages); } // Defensive copy

    @Override
    public String toString() {
        return "IQ: " + iqLevel + (telepathic ? ", Telepathic" : "") + ", Languages: " + knownLanguages.size();
    }
}