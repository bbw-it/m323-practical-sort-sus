package ch.bbw.sortieren;

import java.util.List;

public class School {
    private String name;
    private List<Student> students;

    public School(String name, List<Student> students) {
        this.name = name;
        this.students = students;
    }

    public String getName() { return name; }
    public List<Student> getStudents() { return students; }

    @Override
    public String toString() {
        return name + " (" + students.size() + " Students)";
    }
}