package com.fredrikpedersen;

import com.fredrikpedersen.services.EmployeeServiceImpl;

import javax.xml.ws.Endpoint;

/**
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 06/04/2021 at 19:51
 */

public class EmployeeServicePublisher {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/employeeservice",
                new EmployeeServiceImpl());
    }
}
