package cs5800.softwareEngineering.hw1.drivers.q1;

import cs5800.softwareEngineering.hw1.model.q1.SalariedEmployee;

/**
 * This file consists drivers for Question 1
 */
public class Question1Drivers {
    public static void main(String[] args) {
        SalariedEmployee salariedEmployee = new SalariedEmployee("Joe",
                            "Jones", "111-11-1111", 2500.0);
        System.out.println(salariedEmployee.toString());
    }
}
