package com.fredrikpedersen.repository;

import com.fredrikpedersen.exceptions.EmployeeAlreadyExistsException;
import com.fredrikpedersen.exceptions.EmployeeNotFoundException;
import com.fredrikpedersen.models.Employee;

import java.util.List;

/**
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 06/04/2021 at 20:15
 */

public interface EmployeeRepository {

    List<Employee> getAllEmployees();

    Employee getEmployee(int id) throws EmployeeNotFoundException;

    Employee updateEmployee(int id, String name) throws EmployeeNotFoundException;

    boolean deleteEmployee(int id) throws EmployeeNotFoundException;

    Employee addEmployee(int id, String name) throws EmployeeAlreadyExistsException;

    int count();
}
