package cs5800.softwareEngineering.hw1.model.q2;

public class CruiseShip extends Ship {

    private Integer maxPassenger;

    public CruiseShip(String name, Integer builtYear, Integer maxPassenger) {
        super(name, builtYear);
        this.maxPassenger = maxPassenger;
    }

    public Integer getMaxPassenger() {
        return maxPassenger;
    }

    public void setMaxPassenger(Integer maxPassenger) {
        this.maxPassenger = maxPassenger;
    }

    @Override
    public void print() {
        System.out.println("[CruiseShip] Ship name is " + this.getName() +
                ", built in " + this.getBuiltYear() + " , and maximum passenger allowed is " + this.maxPassenger + ".");
    }
}
