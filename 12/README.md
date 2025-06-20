
### Was macht Java beim Sortieren?

Java sortiert Objekte in Listen mit hilfe von Vergleichslogik.
Diese wird über die Interfaces `Comparable` für Natural Order oder `Comparator` für eigene Sortierreihenfolgen gesteuert.


### Natural Order – wie Java standardmässig sortiert

Java nutzt die natürliche Ordnung einer Klasse, wenn sie das Interface `Comparable<T>` implementiert.

Beispiel:

```java
class Person implements Comparable<Person> {
    String name;

    public int compareTo(Person p) {
        return this.name.compareTo(p.name);
    }
}
```

Dann kann man einfach so sortieren:

```java
Collections.sort(personList);
```

`compareTo()` bestimmt die natürliche Reihenfolge.


### Reverse Order

Statt aufsteigend (A–Z), kann Java auch absteigend (Z–A) sortieren, mit einem Comparator.


```java
Collections.sort(personList, Collections.reverseOrder());
```

Wenn die Klasse `Comparable` ist, kehrt `reverseOrder()` die Natural Order einfach um.


### Wie sortiert Java technisch?

Intern nutzt Java meist eine Variante des TimSort-Algorithmus:

* Mischung aus MergeSort und InsertionSort
* Stabil gleiche Elemente bleiben in Reihenfolge
* Effizient bei echten Daten optimiert für bereits teilweise sortierte Liste

