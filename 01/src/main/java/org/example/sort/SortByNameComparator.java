package org.example.sort;

import org.example.model.Unit;
import java.util.Comparator;

public class SortByNameComparator implements Comparator<Unit> {
    @Override
    public int compare(Unit u1, Unit u2) {
        return u1.getName().compareTo(u2.getName());
    }
}