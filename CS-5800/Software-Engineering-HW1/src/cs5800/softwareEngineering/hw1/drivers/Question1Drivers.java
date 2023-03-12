package cs5800.softwareEngineering.hw1.drivers;

import cs5800.softwareEngineering.hw1.model.q1.BaseEmployee;
import cs5800.softwareEngineering.hw1.model.q1.CommissionEmployee;
import cs5800.softwareEngineering.hw1.model.q1.HourlyEmployee;
import cs5800.softwareEngineering.hw1.model.q1.SalariedEmployee;

/**
 * This file consists drivers for Question 1
 */
public class Question1Drivers {
    public static void main(String[] args) {

        SalariedEmployee salariedEmployee = new SalariedEmployee("Joe", "Jones", "111-11-1111", 2500.0);
        SalariedEmployee salariedEmployee1 = new SalariedEmployee("Renwa","Chanel", "555-55-5555",1750.0);

        HourlyEmployee hourlyEmployee = new HourlyEmployee("Stephanie","Smith","222-22-2222",25.0,32);
        HourlyEmployee hourlyEmployee1 = new HourlyEmployee("Mary","Quinn","333-33-3333",19.0,47);

        CommissionEmployee commissionEmployee = new CommissionEmployee("Nicole","Dior","444-44-4444",15.0,50000.0);
        CommissionEmployee commissionEmployee1 = new CommissionEmployee("Mahnaz","Vaziri", "777-77-7777",22.0, 40000.0);

        BaseEmployee baseEmployee = new BaseEmployee("Mike","Davenport", "666-66-6666",95000.0);

    }
}
