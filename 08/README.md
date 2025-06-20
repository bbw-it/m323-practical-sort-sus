# Functional Sorting Project

This project demonstrates various aspects of functional sorting in Java, focusing on sorting structured data using different techniques.

## Project Structure

```
src/
├── main/java/com/sorting/
│   ├── model/
│   │   ├── Student.java
│   │   ├── Address.java
│   │   └── Course.java
│   └── util/
│       └── StudentSorter.java
└── test/java/com/sorting/
    ├── StudentDataGenerator.java
    └── StudentSortingTest.java
```

## Features

1. **Data Model**
   - `Student` class with 7 attributes (5 different types)
   - Non-primitive attributes: `Address` and `Course[]`
   - Implements `Comparable` for natural ordering

2. **Sorting Techniques Demonstrated**
   - Comparator as a separate class
   - Anonymous class comparator
   - Lambda expression comparator
   - Comparator chain
   - Natural ordering (Comparable)
   - Reverse ordering
   - Multi-level sorting
   - Complex sorting criteria

3. **Test Data Generation**
   - Generates 100 random student records
   - Random data for all attributes
   - Reproducible test data

## How to Run

1. Build the project:
   ```bash
   mvn clean install
   ```

2. Run the tests:
   ```bash
   mvn test
   ```

## Implementation Details

### Student Class
- Implements `Comparable<Student>` for natural ordering by student ID
- Contains various attributes of different types
- Includes non-primitive attributes (Address and Course[])

### Sorting Techniques
1. **Separate Class Comparator**
   - `LastNameComparator` implements `Comparator<Student>`

2. **Anonymous Class**
   - `getFirstNameComparator()` returns an anonymous comparator

3. **Lambda Expression**
   - `getBirthDateComparator()` uses lambda syntax

4. **Comparator Chain**
   - `getMultiFieldComparator()` demonstrates chaining

5. **Natural Order**
   - Uses `Student`'s `compareTo` method

6. **Reverse Order**
   - Uses `Comparator.reverseOrder()`

7. **Complex Sorting**
   - Multi-level sorting with multiple criteria
   - Sorting by associated objects (Address)
   - Sorting by array length (Courses)

## Requirements Met

- [x] Data class with 5+ attributes
- [x] 4+ different data types
- [x] 2+ non-primitive/non-wrapper types
- [x] 100+ random data records
- [x] Various sorting aspects implemented
- [x] All attributes used in sorting
- [x] Multi-level sorting
- [x] Natural and reverse ordering 

## Deep Dive into Java Sorting (Extended Requirement)

This section explains the underlying mechanisms of sorting in Java, addressing the "Tiefes Eintauchen" requirement of the assignment.

### Natural Order (`Comparable`)

Java's natural ordering is defined by the `java.lang.Comparable` interface. A class implementing this interface must provide a `compareTo(T other)` method. This method compares the current object with the specified object (`other`) and returns:

- A negative integer if the current object is less than the other object.
- Zero if the current object is equal to the other object.
- A positive integer if the current object is greater than the other object.

Classes whose objects have a natural ordering (like `String`, `Integer`, `LocalDate`, etc.) implement `Comparable`. When you sort a `List` of objects that implement `Comparable` without providing an explicit `Comparator` (e.g., using `Collections.sort(list)` or `list.sort(null)`), Java uses the natural ordering defined by the `compareTo` method.

In this project, the `Student` class implements `Comparable<Student>` and defines its natural order based on the `studentId` attribute, as shown in `src/main/java/com/sorting/model/Student.java`:

```71:74:src/main/java/com/sorting/model/Student.java
    @Override
    public int compareTo(Student other) {
        // Natural ordering by student ID
        return Integer.compare(this.studentId, other.studentId);
    }
```

### Reverse Order (`Comparator.reverseOrder()`)

Reverse order is essentially the opposite of the natural order or a specified comparator's order. In Java, you can easily obtain a reverse comparator. If the elements have a natural order, `Comparator.reverseOrder()` provides a comparator that imposes the reverse of that natural ordering. If you want the reverse of a specific `Comparator`, you can use the `.reversed()` method on that comparator.

This project demonstrates reverse order by sorting the list using `Comparator.reverseOrder()` on the elements' natural order (student ID), as shown in `src/main/java/com/sorting/util/StudentSorter.java`:

```53:55:src/main/java/com/sorting/util/StudentSorter.java
    // 6. Reverse order
    public static void sortByReverseOrder(List<Student> students) {
        students.sort(Comparator.reverseOrder());
    }
```

It also shows reversing a complex multi-level comparator in `sortByComplexCriteria`:

```68:74:src/main/java/com/sorting/util/StudentSorter.java
    // 10. Complex multi-level sort
    public static void sortByComplexCriteria(List<Student> students) {
        students.sort(Comparator
            .comparing(Student::getAddress, Comparator.comparing(addr -> addr.getCountry()))
            .thenComparing(s -> s.getAddress().getCity())
            .thenComparing(Student::getLastName)
            .thenComparing(Student::getFirstName)
            .thenComparingDouble(Student::getGradePointAverage)
            .reversed());
    }
```

### How Java Sorts

Java's standard library sorting methods for `List`s (`Collections.sort` and `List.sort`) use a highly optimized algorithm called **Timsort**. Timsort is a hybrid stable sorting algorithm, derived from merge sort and insertion sort. It's designed to perform well on many kinds of real-world data, which often contains runs of already sorted elements.

When you call `sort` with no `Comparator` (or `null`), Timsort uses the `compareTo` method of the elements (if they implement `Comparable`) to determine their relative order. When you provide a `Comparator`, Timsort uses the `compare` method of that `Comparator` instead.

Timsort divides the list into smaller sublists, sorts them using insertion sort, and then merges the sorted sublists using a merge sort-like process. Its performance is efficient, with an average and worst-case time complexity of O(n log n), but it can achieve O(n) on already sorted or nearly sorted data.

The sorting process in Java is performed in-place for arrays (using `Arrays.sort`) but may require additional space for lists depending on the implementation and the specific sort operation, although Timsort is designed to minimize this.

By using `Collections.sort` or `List.sort`, you leverage this efficient built-in implementation without needing to implement the sorting algorithm yourself, focusing instead on defining the comparison logic via `Comparable` or `Comparator`. 