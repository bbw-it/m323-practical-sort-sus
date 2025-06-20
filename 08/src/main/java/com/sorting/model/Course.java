package com.sorting.model;

import java.util.Objects;

public class Course {
    private String courseId;
    private String courseName;
    private int credits;
    private String department;

    public Course(String courseId, String courseName, int credits, String department) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.department = department;
    }

    // Getters and Setters
    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }
    
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    
    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseId, course.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credits=" + credits +
                ", department='" + department + '\'' +
                '}';
    }
} 