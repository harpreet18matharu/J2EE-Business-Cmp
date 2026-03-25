
package com.example.studentapp.service;

import com.example.studentapp.entity.Student;
import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    void addStudent(Student student);
}
