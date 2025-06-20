package com.sorting;

import com.sorting.model.Address;
import com.sorting.model.Course;
import com.sorting.model.Student;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StudentDataGenerator {
    private static final Random random = new Random();
    
    public List<Student> generateStudentData() {
        List<Student> students = new ArrayList<>();
        
        // Generate 100 random students
        for (int i = 0; i < 100; i++) {
            // Create random courses
            Course[] courses = new Course[random.nextInt(4) + 1];
            for (int j = 0; j < courses.length; j++) {
                courses[j] = new Course(
                    "CS" + (100 + random.nextInt(900)),
                    getRandomCourseName(),
                    2 + random.nextInt(4),
                    getRandomDepartment()
                );
            }
            
            // Create random address
            Address address = getRandomAddress();
            
            // Create student
            Student student = new Student(
                generateRandomName(5, 10),
                generateRandomName(5, 15),
                LocalDate.now().minusYears(18 + random.nextInt(10)),
                1.0 + random.nextDouble() * 3.0,
                address,
                courses,
                1000 + random.nextInt(9000)
            );
            
            students.add(student);
        }
        
        return students;
    }
    
    private String generateRandomName(int minLength, int maxLength) {
        int length = minLength + random.nextInt(maxLength - minLength + 1);
        StringBuilder name = new StringBuilder();
        name.append((char)('A' + random.nextInt(26)));
        for (int i = 1; i < length; i++) {
            name.append((char)('a' + random.nextInt(26)));
        }
        return name.toString();
    }
    
    private String getRandomCourseName() {
        String[] courses = {
            "Introduction to Programming",
            "Data Structures",
            "Algorithms",
            "Database Systems",
            "Web Development"
        };
        return courses[random.nextInt(courses.length)];
    }
    
    private String getRandomDepartment() {
        String[] departments = {
            "Computer Science",
            "Information Technology",
            "Software Engineering"
        };
        return departments[random.nextInt(departments.length)];
    }
    
    private Address getRandomAddress() {
        Address[] addresses = {
            new Address("Main St", "New York", "10001", "USA"),
            new Address("Broadway", "Chicago", "60601", "USA"),
            new Address("Market St", "San Francisco", "94105", "USA"),
            new Address("Oxford St", "London", "W1D 2", "UK"),
            new Address("Champs-Élysées", "Paris", "75008", "France")
        };
        return addresses[random.nextInt(addresses.length)];
    }
} 