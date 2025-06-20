package ch.berke_poslu.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course implements Comparable<Course> {
    private String courseId;
    private String name;
    private String instructor;
    private LocalDateTime startTime;
    private Integer maxStudents;
    private List<Student> enrolledStudents;
    private Boolean isActive;

    @Override
    public int compareTo(Course other) {
        // natürliche sortierung nach kurs-id
        return this.courseId.compareTo(other.courseId);
    }
} 
