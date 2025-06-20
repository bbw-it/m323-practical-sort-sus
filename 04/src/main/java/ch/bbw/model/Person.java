package ch.bbw.model;

import java.time.LocalDate;
import java.util.Objects;

public class Person implements Comparable<Person> {
    private String name;
    private int age;
    private double height;
    private LocalDate birthday;
    private Address address;

    public Person(String name, int age, double height, LocalDate birthday, Address address) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.birthday = birthday;
        this.address = address;
    }

    // Getter, Setter, toString, equals, hashCode
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }
    public LocalDate getBirthday() { return birthday; }
    public void setBirthday(LocalDate birthday) { this.birthday = birthday; }
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", birthday=" + birthday +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Double.compare(person.height, height) == 0 &&
                Objects.equals(name, person.name) &&
                Objects.equals(birthday, person.birthday) &&
                Objects.equals(address, person.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, height, birthday, address);
    }

    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.name);
    }
}
