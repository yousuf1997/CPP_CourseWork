package cs5800.softwareEngineering.hw1.model.q1;

public class SalariedEmployee extends Employee {
    private Double weeklySalary;

    public SalariedEmployee(String firstName, String lastName, String socialSecurityNumber, Double weeklySalary) {
        super(firstName, lastName, socialSecurityNumber);
        this.weeklySalary = weeklySalary;
    }

    public Double getWeeklySalary() {
        return weeklySalary;
    }

    public void setWeeklySalary(Double weeklySalary) {
        this.weeklySalary = weeklySalary;
    }

    @Override
    public String toString() {
        return "SalariedEmployee \n" +
                "First Name    : " + getFirstName() + "\n" +
                "Last Name     : " + getLastName() + "\n" +
                "SSN           : " + getSocialSecurityNumber() + "\n" +
                "Weekly Salary : $" + this.weeklySalary;
    }
}
