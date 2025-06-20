package ch.bbw.BenBrc;

import ch.bbw.BenBrc.models.Compounds;
import ch.bbw.BenBrc.models.Mixes;
import ch.bbw.BenBrc.models.Solutions;
import ch.bbw.BenBrc.utils.compound.CompoundSorter;
import ch.bbw.BenBrc.utils.mix.MixSorter;
import ch.bbw.BenBrc.utils.solution.SolutionSorter;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    /**
     * Testet die Sortierung von Compounds nach Name, CAS und Datum aufsteigend.
     * Erwartetes Ergebnis: Compounds werden zuerst nach Name, dann nach CAS-Nummer und schließlich nach Erstellungsdatum sortiert.
     */
    public void testCompoundSorterByNameThenCASThenDateAscending() {
        Date now = new Date();
        Compounds a = new Compounds(
                1,
                "Alpha",
                "C7H14O2",
                "123-45-6",
                "Beschreibung A",
                "CID001",
                150.0,
                140.0,
                "InChIKey001",
                "InChI=1S/C001Generated",
                "C1=CC=CC=1", now,
                "IUPAC_A");
        Compounds b = new Compounds(
                2,
                "Alpha",
                "C7H14O2",
                "122-45-6",
                "Beschreibung B",
                "CID002",
                150.0,
                140.0,
                "InChIKey002",
                "InChI=1S/C002Generated",
                "C1=CC=CC=2", now,
                "IUPAC_B");
        Compounds c = new Compounds(
                3,
                "Beta",
                "C5H12",
                "124-45-6",
                "Beschreibung C",
                "CID003",
                150.0,
                140.0,
                "InChIKey003",
                "InChI=1S/C003Generated",
                "C1=CC=CC=3", now,
                "IUPAC_C");

        List<Compounds> input = List.of(a, b, c);
        List<Compounds> result = CompoundSorter.byNameThenCASThenDate(input, true);

        assertEquals("Alpha", result.get(0).getName());
        assertEquals("122-45-6", result.get(0).getCasNumber());
        assertEquals("Alpha", result.get(1).getName());
        assertEquals("123-45-6", result.get(1).getCasNumber());
        assertEquals("Beta", result.get(2).getName());
    }

    /**
     * SolutionSorter testet die Sortierung von Lösungen nach Name und Molekularmasse aufsteigend.
     * * Erwartetes Ergebnis: Lösungen werden zuerst nach Name und dann nach Molekularmasse sortiert.
     */
    public void testSolutionSorterByNameThenMassAscending() {
        Date createdAt1 = new Date();
        Date createdAt2 = new Date(createdAt1.getTime() + 86400000L);

        Solutions s1 = new Solutions(
                1,
                "SolutionA",
                "C6H12O6",
                180.16,
                180.16,
                "CC(O)C(CO)O",
                "Glucose",
                List.of("Traubenzucker"),
                "G123KEY",
                "organic",
                createdAt1
        );

        Solutions s2 = new Solutions(
                2,
                "SolutionA",
                "C6H10O5",
                162.14,
                162.14,
                "CC(O)C(CO)O",
                "Stärke",
                List.of("Polysaccharid"),
                "S123KEY",
                "organic",
                createdAt2
        );

        Solutions s3 = new Solutions(
                3,
                "SolutionB",
                "NaCl",
                58.44,
                58.44,
                "[Na+].[Cl-]",
                "Natriumchlorid",
                List.of("Kochsalz"),
                "NACLKEY",
                "inorganic",
                createdAt1
        );

        List<Solutions> input = List.of(s1, s2, s3);
        List<Solutions> result = SolutionSorter.byNameThenMass(input, true);

        assertEquals(3, result.size());
        assertEquals("SolutionA", result.get(0).getName());
        assertEquals(162.14, result.get(0).getMolecularMass());
        assertEquals("SolutionA", result.get(1).getName());
        assertEquals(180.16, result.get(1).getMolecularMass());
        assertEquals("SolutionB", result.get(2).getName());
        assertEquals(58.44, result.get(2).getMolecularMass());
    }

    /**
     * Testet die Sortierung von Mixes nach Typ und Volumen aufsteigend.
     * Erwartetes Ergebnis: Mixes werden nach Typ (Pufferlösung, Suspension) und Volumen sortiert.
     */
    public void testMixSorterByTypeThenVolumeAscendingWithAllFields() {
        Date now = new Date();
        Date later = new Date(now.getTime() + 10000000L);

        Mixes mA = new Mixes(
                1,
                "bufferA",
                "Beschreibung A",
                "Pufferlösung A",
                50.0,
                new ArrayList<>(),
                now,
                later

        );

        Mixes mB = new Mixes(
                2,
                "suspensionB",
                "Beschreibung B",
                "Suspension A",
                30.0,
                new ArrayList<>(),
                now,
                later
        );

        Mixes mC = new Mixes(
                3,
                "bufferC",
                "Beschreibung C",
                "Pufferlösung C",
                20.0,
                new ArrayList<>(),
                now,
                later
        );

        List<Mixes> input = List.of(mA, mB, mC);
        List<Mixes> result = MixSorter.byTypeThenVolume(input, true);

        assertEquals(3, result.size());
        assertEquals("bufferA", result.get(0).getName());
        assertEquals("bufferC", result.get(1).getName());
        assertEquals("suspensionB", result.get(2).getName());
    }

    public void testMixSorterByTypeThenVolumeDescendingWithAllFields() {
        Date now = new Date();
        Date later = new Date(now.getTime() + 10000000L);

        Mixes mA = new Mixes(
                1,
                "bufferA",
                "Beschreibung A",
                "Pufferlösung A",
                50.0,
                new ArrayList<>(),
                now,
                later
        );

        Mixes mB = new Mixes(
                2,
                "suspensionB",
                "Beschreibung B",
                "Suspension A",
                30.0,
                new ArrayList<>(),
                now,
                later
        );

        Mixes mC = new Mixes(
                3,
                "bufferC",
                "Beschreibung C",
                "Pufferlösung C",
                20.0,
                new ArrayList<>(),
                now,
                later
        );

        List<Mixes> input = List.of(mA, mB, mC);
        List<Mixes> result = MixSorter.byTypeThenVolume(input, false);

        assertEquals("suspensionB", result.get(0).getName());
        assertEquals("bufferC", result.get(1).getName());
        assertEquals("bufferA", result.get(2).getName());
    }


}
