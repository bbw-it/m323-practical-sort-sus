package oz.bbw.ch;

import java.util.Comparator;

public class EmployeeComparators {

  // Comparator-Klasse
  public static class SalaryComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) {
      return e1.getSalary().compareTo(e2.getSalary());
    }
  }

  // Geburtsdatum-Vergleich
  public static final Comparator<Employee> BIRTH_DATE_COMPARATOR = new Comparator<Employee>() {
    @Override
    public int compare(Employee e1, Employee e2) {
      return e1.getBirthDate().compareTo(e2.getBirthDate());
    }
  };

  // Lambda Expression für Eintrittsdatumn Vergleich
  public static final Comparator<Employee> ENTRY_DATE_COMPARATOR = (e1, e2) -> e1.getEntryDate()
      .compareTo(e2.getEntryDate());

  // Lambda Expression für Abteilungs Vergleich
  public static final Comparator<Employee> DEPARTMENT_COMPARATOR = (e1, e2) -> e1.getDepartment().name()
      .compareTo(e2.getDepartment().name());

  // Comparator Chain für mehrstufige Sortierung
  public static final Comparator<Employee> MULTI_LEVEL_COMPARATOR = Comparator.comparing(Employee::getDepartment)
      .thenComparing(Employee::getSalary)
      .thenComparing(Employee::getName);

  // Natural Order Comparator
  public static final Comparator<Employee> NATURAL_ORDER = Comparator.naturalOrder();

  // Reverse Order Comparator
  public static final Comparator<Employee> REVERSE_ORDER = Comparator.reverseOrder();
}