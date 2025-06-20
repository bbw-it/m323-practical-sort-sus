package org.example.sort;

import org.example.model.Country;

import java.util.Comparator;

// Comparator-Klasse: Sortierung nach Bevölkerung
public class SortByPopulation implements Comparator<Country> {

    @Override
    public int compare(Country c1, Country c2) {
        return Long.compare(c1.getPopulation(),c2.getPopulation());
    }
}
