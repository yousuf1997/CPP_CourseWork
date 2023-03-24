package cs5800.softwareEngineering.hw2.model.q2.car;

public class HondaFactory implements CarFactory {
    private static CarFactory instance = null;

    private HondaFactory() {

    }

    public static CarFactory getInstance() {
        if (instance == null) {
            instance = new HondaFactory();
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
