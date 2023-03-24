package cs5800.softwareEngineering.hw2.model.q2.airplane;

public class BoeingFactory implements AirplaneFactory {

    private static AirplaneFactory instance = null;

    private BoeingFactory() {

    }

    public static AirplaneFactory getInstance() {
        if (instance == null) {
            instance = new BoeingFactory();
        }
        return instance;
    }

    @Override
    public void build() {
        System.out.println("Boeing built an airplane.");
    }

    @Override
    public void repair() {
        System.out.println("Boeing repaired an airplane.");
    }

    @Override
    public void restore() {
        System.out.println("Boeing restored an airplane.");
    }
}
