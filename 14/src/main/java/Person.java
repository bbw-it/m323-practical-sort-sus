import java.time.LocalDate;

public class Person implements Comparable<Person> {
    private String name;
    private int age;
    private double salary;
    private LocalDate birthDate;
    private Address address;

    public Person(String name, int age, double salary, LocalDate birthDate, Address address) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.birthDate = birthDate;
        this.address = address;
    }

    // Getter
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getSalary() { return salary; }
    public LocalDate getBirthDate() { return birthDate; }
    public Address getAddress() { return address; }
    public String getCity() { return address.getCity(); }

    @Override
    public String toString() {
        return name + " | " + age + " | " + salary + " | " + birthDate + " | " + address;
    }

    // Natural Order by Name
    @Override
    public int compareTo(Person other) {
        return this.name.compareTo(other.name);
    }
}
