package com.fredrikpedersen.services;

import com.fredrikpedersen.exceptions.EmployeeAlreadyExistsException;
import com.fredrikpedersen.exceptions.EmployeeNotFoundException;
import com.fredrikpedersen.models.Employee;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 06/04/2021 at 20:09
 */

@WebService
public interface EmployeeService {

    @WebMethod
    Employee getEmployee(int id) throws EmployeeNotFoundException;

    @WebMethod
    Employee updateEmployee(int id, String name) throws EmployeeNotFoundException;

    @WebMethod
    boolean deleteEmployee(int id) throws EmployeeNotFoundException;

    @WebMethod
    Employee addEmployee(int id, String name) throws EmployeeAlreadyExistsException;
}
