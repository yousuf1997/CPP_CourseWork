package cs5800.softwareEngineering.hw1.model.q1;

public class HourlyEmployee extends Employee {

    private Double wage;
    private Integer numberOfHoursWorked;

    public HourlyEmployee(String firstName, String lastName, String socialSecurityNumber, Double wage, Integer numberOfHoursWorked) {
        super(firstName, lastName, socialSecurityNumber);
        this.wage = wage;
        this.numberOfHoursWorked = numberOfHoursWorked;
    }

    public Double getWage() {
        return wage;
    }

    public void setWage(Double wage) {
        this.wage = wage;
    }

    public Integer getNumberOfHoursWorked() {
        return numberOfHoursWorked;
    }

    public void setNumberOfHoursWorked(Integer numberOfHoursWorked) {
        this.numberOfHoursWorked = numberOfHoursWorked;
    }
}
