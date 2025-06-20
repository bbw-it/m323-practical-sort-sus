package org.example;

import java.util.List;

public class Person implements Comparable<Person> {
    private String name;
    private int age;
    private boolean employed;
    private Address address;
    private List<String> hobbies;

    private Dog dog;


    public Person() {}

    public Person(String name, int age, boolean employed, Address address, List<String> hobbies) {
        this.name = name;
        this.age = age;
        this.employed = employed;
        this.address = address;
        this.hobbies = hobbies;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", employed=" + employed +
                ", address=" + address +
                ", hobbies=" + hobbies +
                ", dog=" + dog +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(String name, int age, boolean employed, Address address, List<String> hobbies, Dog dog) {
        this.name = name;
        this.age = age;
        this.employed = employed;
        this.address = address;
        this.hobbies = hobbies;
        this.dog = dog;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isEmployed() {
        return employed;
    }

    public void setEmployed(boolean employed) {
        this.employed = employed;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public int compareTo(Person other) {
        return this.name.compareTo(other.name); // Natural Order = Name
    }
}

