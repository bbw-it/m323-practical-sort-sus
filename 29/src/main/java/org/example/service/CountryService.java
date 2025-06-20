package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Country;
import org.example.model.Currency;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CountryService {

    /**
     * Liest Länder aus einer JSON-Datei und wandelt sie in Country-Objekte um.
     *
     * @param filePath Pfad zur Datei, z. B. "countries.json"
     * @return Liste von Country-Objekten
     */
    public ArrayList<Country> loadCountries(String filePath) {
        ArrayList<Country> countries = new ArrayList<Country>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            List<JsonNode> rawList = mapper.readValue(
                    new File(filePath),
                    new TypeReference<List<JsonNode>>() {}
            );

            for (JsonNode node : rawList) {
                String name = node.path("name").path("common").asText(null);
                long population = node.path("population").asLong(0);
                double area = node.path("area").asDouble(0.0);
                String region = node.path("region").asText(null);
                boolean independent = node.path("independent").asBoolean(false);

                ArrayList<String> timezones = new ArrayList<String>();
                for (JsonNode tzNode : node.path("timezones")) {
                    timezones.add(tzNode.asText());
                }

                Currency currency = Country.extractCurrency(node);
                Country country = new Country(name, population, area, region, independent, timezones, currency);
                countries.add(country);
            }

        } catch (Exception e) {
            System.err.println("Fehler beim Einlesen der Länder: " + e.getMessage());
            e.printStackTrace();
        }

        return countries;
    }
}
