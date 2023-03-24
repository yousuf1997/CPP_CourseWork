package cs5800.softwareEngineering.hw2.model.q2.boat;

public class SeaRayFactory implements BoatFactory {
    private static BoatFactory instance = null;

    private SeaRayFactory() {

    }

    public static BoatFactory getInstance() {
        if (instance == null) {
            instance = new SeaRayFactory();
        }
        return instance;
    }

    @Override
    public void build() {
        System.out.println("SeaRay built a boat.");
    }

    @Override
    public void repair() {
        System.out.println("SeaRay repaired a boat.");
    }

    @Override
    public void restore() {
        System.out.println("SeaRay restored a boat.");
    }
}
