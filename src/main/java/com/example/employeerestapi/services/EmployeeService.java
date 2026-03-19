package com.example.employeerestapi.services;

import com.example.employeerestapi.models.Employee;
import jakarta.ejb.Stateless;

import java.util.*;

@Stateless
public class EmployeeService {

    private static final Map<Integer, Employee> employeeMap = new HashMap<>();
    private static int currentId = 1;

    // Optional: seed some data
    static {
        Employee e1 = new Employee(0, "Ava", "Patel", "ava.patel@example.com");
        Employee e2 = new Employee(0, "Noah", "Singh", "noah.singh@example.com");
        createStatic(e1);
        createStatic(e2);
    }

    // Static helper used only for seeding
    private static Employee createStatic(Employee employee) {
        employee.setId(currentId++);
        employeeMap.put(employee.getId(), employee);
        return employee;
    }

    public List<Employee> getAll() {
        return new ArrayList<>(employeeMap.values());
    }

    public Employee getById(int id) {
        return employeeMap.get(id);
    }

    public Employee create(Employee employee) {
        employee.setId(currentId++);
        employeeMap.put(employee.getId(), employee);
        return employee;
    }

    public Employee update(int id, Employee employee) {
        if (!employeeMap.containsKey(id)) return null;
        employee.setId(id);
        employeeMap.put(id, employee);
        return employee;
    }

    public boolean delete(int id) {
        return employeeMap.remove(id) != null;
    }
}
