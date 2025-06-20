package ch.berke_poslu.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Comparable<Student> {
    // grundlegende eigenschaften
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private List<String> courses;
    private Double gradePointAverage;
    private Integer semester;
    private Boolean isActive;

    // verknüpfung zu kursen
    private List<Course> enrolledCourses;

    @Override
    public int compareTo(Student other) {
        // natürliche sortierung: nach nachname, dann vorname
        int lastNameCompare = this.lastName.compareTo(other.lastName);
        if (lastNameCompare != 0) {
            return lastNameCompare;
        }
        return this.firstName.compareTo(other.firstName);
    }
} 
