package cs5800.softwareEngineering.hw1.model.q2;

public class CargoShip extends Ship {
    private Integer cargoCapacity;

    public CargoShip(String name, Integer builtYear, Integer cargoCapacity) {
        super(name, builtYear);
        this.cargoCapacity = cargoCapacity;
    }

    public Integer getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(Integer cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    @Override
    public void print() {
        System.out.println("[CargoShip] Ship name is " + this.getName() +
                ", built in " + this.getBuiltYear() + " , and cargo capacity is " + this.cargoCapacity + " tons.");
    }
}
