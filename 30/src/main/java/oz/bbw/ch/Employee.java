package oz.bbw.ch;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class Employee implements Comparable<Employee> {
  private String name;
  private LocalDate birthDate;
  private BigDecimal salary;
  private Department department;
  private List<String> projects;
  private LocalDate entryDate;

  // Comparator-Attribute in der Datenklasse
  public static final Comparator<Employee> BY_SALARY = Comparator.comparing(Employee::getSalary);
  public static final Comparator<Employee> BY_BIRTH_DATE = Comparator.comparing(Employee::getBirthDate);
  public static final Comparator<Employee> BY_ENTRY_DATE = Comparator.comparing(Employee::getEntryDate);
  public static final Comparator<Employee> BY_DEPARTMENT = Comparator.comparing(e -> e.getDepartment().name());
  public static final Comparator<Employee> BY_PROJECTS_COUNT = Comparator.comparing(e -> e.getProjects().size());

  // Mehrstufiger Comparator als Attribut
  public static final Comparator<Employee> BY_DEPARTMENT_AND_SALARY = Comparator.comparing(Employee::getDepartment)
      .thenComparing(Employee::getSalary);

  public Employee(String name, LocalDate birthDate, BigDecimal salary, Department department, List<String> projects,
      LocalDate entryDate) {
    this.name = name;
    this.birthDate = birthDate;
    this.salary = salary;
    this.department = department;
    this.projects = projects;
    this.entryDate = entryDate;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public BigDecimal getSalary() {
    return salary;
  }

  public void setSalary(BigDecimal salary) {
    this.salary = salary;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public List<String> getProjects() {
    return projects;
  }

  public void setProjects(List<String> projects) {
    this.projects = projects;
  }

  public LocalDate getEntryDate() {
    return entryDate;
  }

  public void setEntryDate(LocalDate entryDate) {
    this.entryDate = entryDate;
  }

  @Override
  public int compareTo(Employee other) {
    return this.name.compareTo(other.name);
  }

  @Override
  public String toString() {
    return "Employee{" +
        "name='" + name + '\'' +
        ", birthDate=" + birthDate +
        ", salary=" + salary +
        ", department=" + department +
        ", projects=" + projects +
        ", entryDate=" + entryDate +
        '}';
  }
}