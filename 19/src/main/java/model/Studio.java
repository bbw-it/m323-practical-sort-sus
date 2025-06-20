package model;

import java.util.List;

public class Studio {
    private String name;
    private List<Film> filme;

    public Studio(String name, List<Film> filme) {
        this.name = name;
        this.filme = filme;
    }

    public String getName() {
        return name;
    }

    public List<Film> getFilme() {
        return filme;
    }

    @Override
    public String toString() {
        return "Studio{name='" + name + "', filme=" + filme.size() + " Filme}";
    }
}