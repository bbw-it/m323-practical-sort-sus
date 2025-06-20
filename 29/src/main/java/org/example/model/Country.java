package org.example.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country implements Comparable<Country> {
    private String name;
    private long population;
    private double area;
    private String region;
    private boolean independent;
    private List<String> timezone;
    private Currency currency;

    // Natural Order (Comparable): nach Name
    @Override
    public int compareTo(Country other) {
        return this.name.compareTo(other.name);
    }

    public static Currency extractCurrency(JsonNode root) {
        JsonNode currenciesNode = root.path("currencies");
        if (!currenciesNode.isObject() || currenciesNode.size() == 0) {
            return null;
        }
        // Nimm den ersten Key (z.B. "EUR"), extrahiere name & symbol
        String code = currenciesNode.fieldNames().next();
        JsonNode data = currenciesNode.get(code);
        String name = data.path("name").asText(null);
        String symbol = data.path("symbol").asText(null);
        return new Currency(code, name, symbol);
    }

    public static final Comparator<Country> BY_AREA= Comparator.comparingDouble(Country::getArea);
}
