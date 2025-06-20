package ch.bbw.BenBrc;

import ch.bbw.BenBrc.models.*;
import ch.bbw.BenBrc.services.ConsolePrinter;
import ch.bbw.BenBrc.services.DataGenerator;
import ch.bbw.BenBrc.utils.compound.*;
import ch.bbw.BenBrc.utils.mix.*;
import ch.bbw.BenBrc.utils.solution.*;
import ch.bbw.BenBrc.factories.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Die Klasse CoSoMi ist der Einstiegspunkt für die Anwendung.
 * Sie generiert Daten für Verbindungen, Lösungen und Mischungen
 * und sortiert bzw. filtert diese Daten anschließend.
 * Autor: Benedict Brück
 * Version: 1.0
 * Datum: 19.06.2025
 */
public class CoSoMi {
    public static void main(String[] args) {
        // Daten generieren
        List<Compounds> compounds = DataGenerator.generateCompounds();
        List<Solutions> solutions = DataGenerator.generateSolutions(compounds);
        List<Mixes> mixes = DataGenerator.generateMixes(solutions);

        // Konsolenausgabe für Compounds (Verbindungen)
        ConsolePrinter.prettyPrintCompounds(
                CompoundSorter.byNameThenCASThenDate(compounds, true),
                "COMPOUND SORTIERUNG (asc) Name → CAS → CreatedAt"
        );

        // Sortierung von Compounds
        ConsolePrinter.prettyPrintCompounds(
                CompoundSorter.byMolecularMassThenIUPAC(compounds, false),
                "COMPOUND SORTIERUNG (desc) Mol. Mass → IUPAC"
        );

        // Filterung von Compounds
        ConsolePrinter.prettyPrintCompounds(
                CompoundFilter.byNameContains(compounds, "5")
                        .stream()
                        .limit(5)
                        .collect(Collectors.toList()),
                "COMPOUND FILTER: Name enthält '5'"
        );

        // Konsolenausgabe für Solutions (Lösungen)
        ConsolePrinter.prettyPrintSolutions(
                SolutionSorter.byNameThenMass(solutions, true),
                "SOLUTION SORTIERUNG (asc) Name → Mass"
        );

        // Sortierung und Filterung von Solutions
        ConsolePrinter.prettyPrintSolutions(
                SolutionFilter.byClassificationContains(solutions, "organic")
                        .stream()
                        .limit(5)
                        .collect(Collectors.toList()),
                "SOLUTION FILTER: Classification enthält 'organic'"
        );

        // Konsolenausgabe für Mixes (Mischungen)
        ConsolePrinter.prettyPrintMixes(
                MixSorter.byTypeThenVolume(mixes, false),
                "MIX SORTIERUNG (desc) Type → Volume"
        );

        // Sortierung von Mixes
        ConsolePrinter.prettyPrintMixes(
                MixFilter.byTypeEquals(mixes, "buffer")
                        .stream()
                        .limit(5)
                        .collect(Collectors.toList()),
                "MIX FILTER: Type = 'buffer'"
        );
    }
}
