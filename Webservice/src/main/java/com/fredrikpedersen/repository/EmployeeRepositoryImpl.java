package com.fredrikpedersen.repository;

import com.fredrikpedersen.exceptions.EmployeeAlreadyExistsException;
import com.fredrikpedersen.exceptions.EmployeeNotFoundException;
import com.fredrikpedersen.models.Employee;

import java.util.Arrays;
import java.util.List;

/**
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 06/04/2021 at 20:20
 */

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final List<Employee> employeeList = Arrays.asList(new Employee(1, "Jane"), new Employee(2, "Jack"), new Employee(3, "George"));

    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    public Employee getEmployee(final int id) throws EmployeeNotFoundException {
        return employeeList.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public Employee updateEmployee(final int id, final String name) throws EmployeeNotFoundException {
        return employeeList.stream()
                .filter(employee -> employee.getId() == id)
                .peek(employee -> {
                    employee.setId(id);
                    employee.setName(name);})
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public boolean deleteEmployee(final int id) throws EmployeeNotFoundException {
        if (employeeList.removeIf(employee -> employee.getId() == id)) {
            return true;
        }

        throw new EmployeeNotFoundException();
    }

    public Employee addEmployee(final int id, final String name) throws EmployeeAlreadyExistsException {
        for (final Employee employee : employeeList) {
            if (employee.getId() == id) {
                throw new EmployeeAlreadyExistsException();
            }
        }

        final Employee employee = new Employee(id, name);
        employeeList.add(employee);

        return employee;
    }

    public int count() {
        return employeeList.size();
    }
}
