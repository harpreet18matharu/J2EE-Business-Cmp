package com.example.service;

import jakarta.ejb.Singleton;
import com.example.model.Employee;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class EmployeeService {

    private final List<Employee> employees = new ArrayList<>();
    private long nextId = 1;

    public EmployeeService() {
        // sample data
        employees.add(new Employee(nextId++, "Harpreet", "IT"));
        employees.add(new Employee(nextId++, "John", "HR"));
    }

    public Employee create(Employee e) {
        e.setId(nextId++);
        employees.add(e);
        return e;
    }

    public List<Employee> findAll() {
        return employees;
    }

    public Employee findById(long id) {
        Optional<Employee> emp = employees.stream().filter(x -> x.getId() == id).findFirst();
        return emp.orElse(null);
    }

    public Employee update(long id, Employee updated) {
        Employee existing = findById(id);
        if (existing == null) return null;

        existing.setName(updated.getName());
        existing.setDepartment(updated.getDepartment());
        return existing;
    }

    public boolean delete(long id) {
        return employees.removeIf(e -> e.getId() == id);
    }
}
