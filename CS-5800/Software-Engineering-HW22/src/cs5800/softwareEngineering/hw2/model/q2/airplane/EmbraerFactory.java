package cs5800.softwareEngineering.hw2.model.q2.airplane;

public class EmbraerFactory implements AirplaneFactory {

    private static AirplaneFactory instance = null;

    private EmbraerFactory() {

    }

    public static AirplaneFactory getInstance() {
        if (instance == null) {
            instance = new EmbraerFactory();
        }
        return instance;
    }

    @Override
    public void build() {
        System.out.println("Embraer built an airplane.");
    }

    @Override
    public void repair() {
        System.out.println("Embraer repaired an airplane.");
    }

    @Override
    public void restore() {
        System.out.println("Embraer restored an airplane.");
    }
}
