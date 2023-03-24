package cs5800.softwareEngineering.hw2.model.q2.airplane;

public class AirBusFactory implements AirplaneFactory {
    private static AirplaneFactory instance = null;

    private AirBusFactory() {

    }

    public static AirplaneFactory getInstance() {
        if (instance == null) {
            instance = new AirBusFactory();
        }
        return instance;
    }

    @Override
    public void build() {
        System.out.println("AirBus built an airplane.");
    }

    @Override
    public void repair() {
        System.out.println("AirBus repaired an airplane.");
    }

    @Override
    public void restore() {
        System.out.println("AirBus restored an airplane.");
    }
}
