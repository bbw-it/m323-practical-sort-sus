package ch.bbw.model;

import java.time.LocalDate;
import java.util.Objects;

public class Dog {
    private String name;
    private String breed;
    private LocalDate birthDate;
    private double weight;
    private Person owner;

    public Dog(String name, String breed, LocalDate birthDate, double weight, Person owner) {
        this.name = name;
        this.breed = breed;
        this.birthDate = birthDate;
        this.weight = weight;
        this.owner = owner;
    }

    // Getter, Setter, toString, equals, hashCode
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
    public Person getOwner() { return owner; }
    public void setOwner(Person owner) { this.owner = owner; }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", birthDate=" + birthDate +
                ", weight=" + weight +
                ", owner=" + (owner != null ? owner.getName() : null) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return Double.compare(dog.weight, weight) == 0 &&
                Objects.equals(name, dog.name) &&
                Objects.equals(breed, dog.breed) &&
                Objects.equals(birthDate, dog.birthDate) &&
                Objects.equals(owner, dog.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, breed, birthDate, weight, owner);
    }
}
