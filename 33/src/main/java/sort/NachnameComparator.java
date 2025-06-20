package sort;

import model.Person;

import java.util.Comparator;

public class NachnameComparator implements Comparator<Person> {
  @Override
  public int compare(Person p1, Person p2) {
    return p1.getNachname().compareTo(p2.getNachname()); // Comparator-Klasse
  }
}
