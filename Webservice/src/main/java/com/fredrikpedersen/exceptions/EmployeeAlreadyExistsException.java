package com.fredrikpedersen.exceptions;

import javax.xml.ws.WebFault;

/**
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 06/04/2021 at 20:18
 */

@WebFault
public class EmployeeAlreadyExistsException extends Exception {

    public EmployeeAlreadyExistsException() {
        super("This employee already exists");
    }

    public EmployeeAlreadyExistsException(final String message) {
        super(message);
    }
}
