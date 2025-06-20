package org.example;

import org.example.model.Country;
import org.example.service.CountryService;
import org.example.sort.SortByPopulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        naturalOrder();
        reverseOrder();
        abgeleiteteKlassePopulation();
        anonymeKlasse();
        lambdaExpressionRegion();
        comparatorChain();
        comparatorAttributInDerDatenklasse();
        sortiereNachAttributInDerAssoziation();
    }

    private static ArrayList<Country> getDataOutOfJSON() {
        CountryService countryService = new CountryService();
        return countryService.loadCountries("countries.json");
    }

    // Natural Order (Comparable): nach Name
    private static void naturalOrder() {
        ArrayList<Country> countries = getDataOutOfJSON();
        Collections.sort(countries);
        System.out.println(countries);
    }

    // Reverse Order (Comparable): nach Name
    private static void reverseOrder() {
        ArrayList<Country> countries = getDataOutOfJSON();
        countries.sort(Collections.reverseOrder());
        System.out.println(countries);
    }

    // Comparator-Klasse: Sortierung nach Bevölkerung
    private static void abgeleiteteKlassePopulation() {
        ArrayList<Country> countries = getDataOutOfJSON();
        countries.sort(new SortByPopulation());
        System.out.println(countries);
    }

    // Anonyme Klasse: Sortierung nach Fläche
    private static void anonymeKlasse() {
        ArrayList<Country> countries = getDataOutOfJSON();
        countries.sort(new Comparator<Country>() {
            @Override
            public int compare(Country c1, Country c2) {
                return Double.compare(c1.getArea(), c2.getArea());
            }
        });
        System.out.println(countries);
    }

    // Lambda Expression: Sortierung nach Region
    private static void lambdaExpressionRegion() {
        ArrayList<Country> countries = getDataOutOfJSON();
        countries.sort((c1, c2) -> (c1.getRegion()).compareTo(c2.getRegion()));
        System.out.println(countries);
    }

    // Comparator Chain: Region → Bevölkerung → Name
    private static void comparatorChain() {
        ArrayList<Country> countries = getDataOutOfJSON();
        countries.sort(Comparator.comparing(Country::getRegion).thenComparing(Country::getPopulation).thenComparing(Country::getName));
        System.out.println(countries);
    }

    // Comparator-Attribut: Sortierung nach Fläche
    private static void comparatorAttributInDerDatenklasse() {
        ArrayList<Country> countries = getDataOutOfJSON();
        countries.sort(Country.BY_AREA);
        System.out.println(countries);
    }
    // Sortierung nach Währungsname (Assoziation zu Currency)
    private static void sortiereNachAttributInDerAssoziation() {
        ArrayList<Country> countries = getDataOutOfJSON();
        countries.sort(Comparator.comparing(c-> c.getCurrency() != null ? c.getCurrency().getSymbol() : " "));
        System.out.println(countries);
    }
}
