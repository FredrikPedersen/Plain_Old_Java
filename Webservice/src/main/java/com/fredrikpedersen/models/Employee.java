package com.fredrikpedersen.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 06/04/2021 at 20:05
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private int id;
    private String name;
}
