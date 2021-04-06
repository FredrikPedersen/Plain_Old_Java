package com.fredrikpedersen.services;

import com.fredrikpedersen.exceptions.EmployeeAlreadyExistsException;
import com.fredrikpedersen.exceptions.EmployeeNotFoundException;
import com.fredrikpedersen.models.Employee;
import com.fredrikpedersen.repository.EmployeeRepository;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 06/04/2021 at 20:10
 */

@WebService
public class EmployeeServiceImpl implements EmployeeService {

    @Inject
    private EmployeeRepository employeeRepository;

    @WebMethod
    public Employee getEmployee(int id) throws EmployeeNotFoundException {
        return employeeRepository.getEmployee(id);
    }

    @WebMethod
    public Employee updateEmployee(int id, String name) throws EmployeeNotFoundException {
        return employeeRepository.updateEmployee(id, name);
    }

    @WebMethod
    public boolean deleteEmployee(int id) throws EmployeeNotFoundException {
        return employeeRepository.deleteEmployee(id);
    }

    @WebMethod
    public Employee addEmployee(int id, String name) throws EmployeeAlreadyExistsException {
        return employeeRepository.addEmployee(id, name);
    }
}

