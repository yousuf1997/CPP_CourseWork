package cs5800.softwareEngineering.hw2.model.q2.car;

public class PorscheFactory implements CarFactory {
    private static CarFactory instance = null;

    private PorscheFactory() {

    }

    public static CarFactory getInstance() {
        if (instance == null) {
            instance = new PorscheFactory();
        }
        return instance;
    }

    @Override
    public void build() {
        System.out.println("Porsche built a car.");
    }

    @Override
    public void repair() {
        System.out.println("Porsche repaired a car.");
    }

    @Override
    public void restore() {
        System.out.println("Porsche restored a car.");
    }
}
