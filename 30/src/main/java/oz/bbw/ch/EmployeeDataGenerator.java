package oz.bbw.ch;

import org.instancio.Instancio;
import org.instancio.Select;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class EmployeeDataGenerator {

  public static List<Employee> generateEmployees(int count) {
    return Instancio.ofList(Employee.class)
        .size(count)
        .generate(Select.field(Employee::getSalary),
            gen -> gen.math().bigDecimal().range(new BigDecimal("3000"), new BigDecimal("15000")))
        .generate(Select.field(Employee::getBirthDate), gen -> gen.temporal().localDate().range(
            LocalDate.now().minusYears(65),
            LocalDate.now().minusYears(18)))
        .generate(Select.field(Employee::getEntryDate), gen -> gen.temporal().localDate().range(
            LocalDate.now().minusYears(20),
            LocalDate.now()))
        .generate(Select.field(Employee::getDepartment), gen -> gen.enumOf(Department.class))
        .generate(Select.field(Employee::getProjects), gen -> gen.collection().minSize(1).maxSize(5))
        .create();
  }
}