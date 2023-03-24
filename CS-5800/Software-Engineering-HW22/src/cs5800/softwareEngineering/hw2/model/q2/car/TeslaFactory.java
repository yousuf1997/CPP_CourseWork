package cs5800.softwareEngineering.hw2.model.q2.car;

public class TeslaFactory implements CarFactory {

    private static CarFactory instance = null;

    private TeslaFactory() {

    }

    public static CarFactory getInstance() {
        if (instance == null) {
            instance = new TeslaFactory();
        }
        return instance;
    }

    @Override
    public void build() {
        System.out.println("Tesla built a car.");
    }

    @Override
    public void repair() {
        System.out.println("Tesla repaired a car.");
    }

    @Override
    public void restore() {
        System.out.println("Tesla restored a car.");
    }
}
