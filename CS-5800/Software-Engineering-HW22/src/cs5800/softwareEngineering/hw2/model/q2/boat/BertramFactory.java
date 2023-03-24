package cs5800.softwareEngineering.hw2.model.q2.boat;

public class BertramFactory implements BoatFactory {

    private static BoatFactory instance = null;

    private BertramFactory() {

    }

    public static BoatFactory getInstance() {
        if (instance == null) {
            instance = new BertramFactory();
        }
        return instance;
    }

    @Override
    public void build() {
        System.out.println("Bertram built a boat.");
    }

    @Override
    public void repair() {
        System.out.println("Bertram repaired a boat.");
    }

    @Override
    public void restore() {
        System.out.println("Bertram restored a boat.");
    }
}
