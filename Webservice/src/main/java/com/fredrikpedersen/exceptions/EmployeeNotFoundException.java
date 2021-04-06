package com.fredrikpedersen.exceptions;

import javax.xml.ws.WebFault;

/**
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 06/04/2021 at 20:18
 */
@WebFault
public class EmployeeNotFoundException extends Exception {

    public EmployeeNotFoundException() {
        super("The specified employee does not exists");
    }

    public EmployeeNotFoundException(final String message) {
        super(message);
    }

}
