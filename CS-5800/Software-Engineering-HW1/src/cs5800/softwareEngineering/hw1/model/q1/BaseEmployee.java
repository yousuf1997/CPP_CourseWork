package cs5800.softwareEngineering.hw1.model.q1;

public class BaseEmployee extends Employee{

    private Double weeklySalary;
    public BaseEmployee(String firstName, String lastName, String socialSecurityNumber, Double weeklySalary) {
        super(firstName, lastName, socialSecurityNumber);
        this.weeklySalary = weeklySalary;
    }

    public Double getWeeklySalary() {
        return weeklySalary;
    }

    public void setWeeklySalary(Double weeklySalary) {
        this.weeklySalary = weeklySalary;
    }
}
