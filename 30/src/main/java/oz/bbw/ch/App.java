package oz.bbw.ch;

import java.util.List;

public class App {
    public static void main(String[] args) {
        // Generate test data
        List<Employee> employees = EmployeeDataGenerator.generateEmployees(100);

        System.out.println("Original list (first 5 employees):");
        employees.stream().limit(5).forEach(System.out::println);

        // Demonstrate different sorting methods
        System.out.println("\n1. Natural Order (by name):");
        employees.stream()
                .sorted(EmployeeComparators.NATURAL_ORDER)
                .limit(5)
                .forEach(System.out::println);

        System.out.println("\n2. Reverse Order:");
        employees.stream()
                .sorted(EmployeeComparators.REVERSE_ORDER)
                .limit(5)
                .forEach(System.out::println);

        System.out.println("\n3. Sort by Salary (using class attribute):");
        employees.stream()
                .sorted(Employee.BY_SALARY)
                .limit(5)
                .forEach(System.out::println);

        System.out.println("\n4. Sort by Birth Date (using class attribute):");
        employees.stream()
                .sorted(Employee.BY_BIRTH_DATE)
                .limit(5)
                .forEach(System.out::println);

        System.out.println("\n5. Sort by Entry Date (using class attribute):");
        employees.stream()
                .sorted(Employee.BY_ENTRY_DATE)
                .limit(5)
                .forEach(System.out::println);

        System.out.println("\n6. Sort by Department (using class attribute):");
        employees.stream()
                .sorted(Employee.BY_DEPARTMENT)
                .limit(5)
                .forEach(System.out::println);

        System.out.println("\n7. Sort by Projects Count (using class attribute):");
        employees.stream()
                .sorted(Employee.BY_PROJECTS_COUNT)
                .limit(5)
                .forEach(System.out::println);

        System.out.println("\n8. Multi-level Sort (Department -> Salary using class attribute):");
        employees.stream()
                .sorted(Employee.BY_DEPARTMENT_AND_SALARY)
                .limit(5)
                .forEach(System.out::println);
    }
}