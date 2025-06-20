package ch.bbw.model;

import java.util.Objects;

public class Address {
    private String street;
    private int houseNumber;
    private String city;
    private String country;

    public Address(String street, int houseNumber, String city, String country) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.country = country;
    }

    // Getter, Setter, toString, equals, hashCode
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    public int getHouseNumber() { return houseNumber; }
    public void setHouseNumber(int houseNumber) { this.houseNumber = houseNumber; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    @Override
    public String toString() {
        return street + " " + houseNumber + ", " + city + ", " + country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return houseNumber == address.houseNumber &&
                Objects.equals(street, address.street) &&
                Objects.equals(city, address.city) &&
                Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, houseNumber, city, country);
    }
}
