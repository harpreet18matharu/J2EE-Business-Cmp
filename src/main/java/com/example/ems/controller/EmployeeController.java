package com.example.ems.controller;

import com.example.ems.entity.Employee;
import com.example.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/")
    public String viewHome(Model model) {
        model.addAttribute("employeeList", service.getAllEmployees());
        return "index";
    }

    @GetMapping("/showAddForm")
    public String addForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "addEmployee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee emp) {
        service.saveEmployee(emp);
        return "redirect:/";
    }

    @GetMapping("/showUpdateForm/{id}")
    public String updateForm(@PathVariable int id, Model model) {
        model.addAttribute("employee", service.getEmployeeById(id));
        return "updateEmployee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String delete(@PathVariable int id) {
        service.deleteEmployee(id);
        return "redirect:/";
    }
}
