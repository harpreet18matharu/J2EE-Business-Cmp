
package com.example.studentapp.service;

import com.example.studentapp.entity.Student;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {

    private Map<Integer, Student> studentMap = new HashMap<>();

    public StudentServiceImpl() {
        studentMap.put(1, new Student(1, "John", 21, "Male", "john@gmail.com", "Toronto", "2003-01-01"));
        studentMap.put(2, new Student(2, "Sara", 22, "Female", "sara@gmail.com", "Brampton", "2002-05-10"));
        studentMap.put(3, new Student(3, "Alex", 20, "Other", "alex@gmail.com", "Mississauga", "2004-03-15"));
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(studentMap.values());
    }

    public void addStudent(Student student) {
        studentMap.put(student.getId(), student);
    }
}
