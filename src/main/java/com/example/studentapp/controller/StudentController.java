
package com.example.studentapp.controller;

import com.example.studentapp.entity.Student;
import com.example.studentapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping("/")
    public String viewStudents(Model model) {
        model.addAttribute("students", service.getAllStudents());
        return "index";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student) {
        service.addStudent(student);
        return "redirect:/";
    }
}
